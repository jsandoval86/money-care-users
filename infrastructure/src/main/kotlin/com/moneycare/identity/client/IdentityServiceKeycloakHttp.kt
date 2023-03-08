package com.moneycare.identity.client

import com.moneycare.identity.IdentityService
import com.moneycare.identity.client.mapper.KeyCloakCreateUserRequestMapper
import com.moneycare.identity.client.mapper.KeyCloakRefreshTokeRequestMapper
import com.moneycare.identity.client.mapper.KeyCloakTokenResponseMapper
import com.moneycare.identity.client.request.KeyCloakTokenRequest
import com.moneycare.identity.client.shared.GrantType
import com.moneycare.users.password.Password
import com.moneycare.users.token.UserToken
import com.moneycare.users.user.User
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(
    value=["keycloak.enabled"],
    havingValue = "true",
    matchIfMissing = true)
class IdentityServiceKeycloakHttp(
    private var keyCloakTokenResponseMapper: KeyCloakTokenResponseMapper,
    private var identityClient: IdentityKeycloakFeignClient,
    private var keyCloakCreateUserRequestMapper: KeyCloakCreateUserRequestMapper,
    private var keyCloakRefreshTokeRequestMapper: KeyCloakRefreshTokeRequestMapper
): IdentityService {

    private val log = LoggerFactory.getLogger(javaClass)

    @Value(value = "\${keycloak.client-id}")
    private lateinit var clientId: String

    @Value(value = "\${keycloak.client-secret}")
    private lateinit var clientSecret: String

    @Value(value = "\${keycloak.realm}")
    private lateinit var realm: String

    override fun createTokenByUserNameAndPassword(username: String, password: String): UserToken {
        val request = KeyCloakTokenRequest(clientId, clientSecret, GrantType.password.name, "openid", username, password)
        log.debug("call identity server service to create token")
        val keyCloakToken = identityClient.createToken(realm, request)
        log.debug("identity server response: $keyCloakToken")

        return keyCloakTokenResponseMapper.mapToDomain(keyCloakToken)
    }

    override fun createUser(user: User, password: Password) {
        val request = keyCloakCreateUserRequestMapper.mapToRequest(user, password.getText())
        log.debug("call identity server service to create user")
        identityClient.createUser(realm, request)
        log.debug("user created!")
    }

    override fun refreshTokenUser(refreshToken: String): UserToken {
        val request = keyCloakRefreshTokeRequestMapper.mapToRequest(clientId, clientSecret, GrantType.refresh_token.name, refreshToken)
        log.debug("call identity server service to refresh token user")
        val keyCloakToken = identityClient.refreshToken(realm, request)
        log.debug("token response: $keyCloakToken")
        return keyCloakTokenResponseMapper.mapToDomain(keyCloakToken)
    }

    override fun validateToken(token: String){
        log.debug("call identity server service to validate token user")
        val userInfoResponse = identityClient.validateToken(realm, token)
        log.debug("user info response: $userInfoResponse")
    }

}