package com.moneycare.identity.client

import com.moneycare.identity.IdentityService
import com.moneycare.identity.client.mapper.KeyCloakCreateUserRequestMapper
import com.moneycare.identity.client.mapper.KeyCloakRefreshTokeRequestMapper
import com.moneycare.identity.client.mapper.KeyCloakTokenResponseMapper
import com.moneycare.identity.client.request.KeyCloakTokenRequestGranTypeClientCredentials
import com.moneycare.identity.client.request.KeyCloakTokenRequestGrantTypePassword
import com.moneycare.identity.client.response.KeyCloakTokenResponse
import com.moneycare.identity.client.shared.GrantType
import com.moneycare.users.password.Password
import com.moneycare.users.shared.exceptions.TechnicalException
import com.moneycare.users.token.UserToken
import com.moneycare.users.user.User
import feign.Response
import org.apache.http.HttpStatus
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

    @Value(value = "\${keycloak.admin.realm}")
    private lateinit var adminRealm: String

    @Value(value = "\${keycloak.admin.client-secret}")
    private lateinit var adminClientSecret: String

    @Value(value = "\${keycloak.admin.client-id}")
    private lateinit var adminClientId: String


    override fun createTokenByUserNameAndPassword(username: String, password: String): UserToken {
        val request = KeyCloakTokenRequestGrantTypePassword(clientId, clientSecret, GrantType.password.name, "openid", username, password)
        val keyCloakToken = identityClient.createToken(realm, request)

        return keyCloakTokenResponseMapper.mapToDomain(keyCloakToken)
    }

    override fun createUser(user: User, password: Password): String {
        val adminToken = getAdminToken()

        val requestCreateUser = keyCloakCreateUserRequestMapper.mapToRequest(user, password.getText())
        val response: Response = identityClient.createUser(adminToken.getTokenBearer(), realm, requestCreateUser)

        if(response.status() != HttpStatus.SC_CREATED){
            throw TechnicalException("error creating user in identity server, status: ${response.status()} reason: ${response.reason()}")
        }

        val location: String = response.headers()[Headers.Location.name].toString()

        if(location.isNotEmpty()){
            return location.substringAfterLast("/").replace("]", "").replace("%5D", "")
        } else {
            throw TechnicalException("error creating user in keycloak, not found location header or id")
        }
    }

    override fun deleteUserById(identityId: String) {
        val adminToken = getAdminToken()
        identityClient.deleteUser(realm, identityId, adminToken.getTokenBearer())
    }

    private fun getAdminToken(): KeyCloakTokenResponse {
        val tokenAdminRequest = KeyCloakTokenRequestGranTypeClientCredentials(
            adminClientId,
            adminClientSecret,
            GrantType.client_credentials.name
        )
        val adminToken = identityClient.createToken(adminRealm, tokenAdminRequest);
        return adminToken
    }

    override fun refreshTokenUser(refreshToken: String): UserToken {
        val request = keyCloakRefreshTokeRequestMapper.mapToRequest(clientId, clientSecret, GrantType.refresh_token.name, refreshToken)
        val keyCloakToken = identityClient.refreshToken(realm, request)

        return keyCloakTokenResponseMapper.mapToDomain(keyCloakToken)
    }

    override fun validateToken(token: String){
        identityClient.validateToken(realm, token)
    }



}