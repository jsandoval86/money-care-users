package com.moneycare.users.rest

import com.moneycare.users.shared.exceptions.TechnicalException
import com.moneycare.users.user.UserContextData
import com.moneycare.users.user.exception.UnAuthorizedException
import io.jsonwebtoken.Claims
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component


@Component
@Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
open class UserAuthenticated {
    private lateinit var userId: String
    private lateinit var tenantId: String
    private lateinit var role: String
    private lateinit var copropiedades: List<String>
    private lateinit var accessToken: String

    fun autenticate(claims: Claims) {
        try {
            setData(claims)
        } catch (e: TechnicalException) {
            throw UnAuthorizedException("unauthorized user", e)
        }
    }

    fun getUserContextData(): UserContextData {
        return UserContextData.create(this.userId, this.tenantId, this.role, this.copropiedades, this.accessToken)
    }

    private fun setData(claims: Claims) {
        setUserId(claims["id_user"] as String?)
        setTenantId(claims["id_tenant"] as String?)
        setRole(claims["role"] as String?)
        @Suppress("UNCHECKED_CAST")
        setCopropiedades(claims["copropiedades"] as List<String>?)
        setAccessToken(claims["access_token"] as String?)
    }

    private fun setUserId(userId: String?) {
        this.userId = userId ?: throw TechnicalException("not found user Id in token")
    }
    private fun setTenantId(tenantId: String?) {
        this.tenantId = tenantId ?: throw TechnicalException("not found tenant Id in token")
    }
    private fun setRole(role: String?) {
        this.role = role ?: throw TechnicalException("not found role in token")
    }
    private fun setCopropiedades(copropiedades: List<String>?) {
        this.copropiedades = copropiedades ?: throw TechnicalException("not found copropiedades in token")
    }
    private fun setAccessToken(accessToken: String?) {
        this.accessToken = accessToken ?: throw TechnicalException("not found acces token in token")
    }

}