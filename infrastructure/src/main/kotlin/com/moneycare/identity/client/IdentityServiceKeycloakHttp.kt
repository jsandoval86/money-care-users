package com.moneycare.identity.client

import com.moneycare.identity.IdentityService
import com.moneycare.identity.client.mapper.KeyCloakCreateUserRequestMapper
import com.moneycare.identity.client.mapper.KeyCloakTokenResponseMapper
import com.moneycare.identity.client.request.KeyCloakTokenRequest
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
    private var keyCloakCreateUserRequestMapper: KeyCloakCreateUserRequestMapper
): IdentityService {
    // TODO: feign client hanlder exceptions

    private val log = LoggerFactory.getLogger(javaClass)

    @Value(value = "\${keycloak.client-id}")
    private lateinit var clientId: String

    @Value(value = "\${keycloak.client-secret}")
    private lateinit var clientSecret: String

    override fun createTokenByUserNameAndPassword(username: String, password: String): UserToken {
        val request = KeyCloakTokenRequest(clientId, clientSecret, "password", "openid", username, password)
        log.debug("call identity server service to create token")
        val keyCloakToken = identityClient.createToken(request)
        log.debug("identity server response: $keyCloakToken")

        return keyCloakTokenResponseMapper.mapToDomain(keyCloakToken)
    }

    override fun createUser(user: User, password: String) {
        val request = keyCloakCreateUserRequestMapper.mapToRequest(user, password)
        log.debug("call identity server service to create user")
        identityClient.createUser(request)
        log.debug("user created!")
    }

}