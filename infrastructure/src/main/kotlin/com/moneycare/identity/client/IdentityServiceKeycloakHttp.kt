package com.moneycare.identity.client

import com.moneycare.identity.IdentityService
import com.moneycare.identity.client.mapper.KeyCloakTokenResponseMapper
import com.moneycare.identity.client.request.KeyCloakTokenRequest
import com.moneycare.users.token.UserToken
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class IdentityServiceKeycloakHttp(
    private var keyCloakTokenResponseMapper: KeyCloakTokenResponseMapper,
    private var identityClient: IdentityKeycloakFeignClient
): IdentityService {


    private val log = LoggerFactory.getLogger(javaClass)

    @Value(value = "\${keycloak.client-id}")
    private lateinit var clientId: String

    @Value(value = "\${keycloak.client-secret}")
    private lateinit var clientSecret: String

    @Value(value = "\${keycloak.urls.host}")
    private lateinit var host: String

    @Value(value = "\${keycloak.urls.token}")
    private lateinit var tokenUrl: String


    override fun createTokenByUserNameAndPassword(username: String, password: String): UserToken {
        val request  = KeyCloakTokenRequest(clientId, clientSecret, "password", "openid", username, password)
        return keyCloakTokenResponseMapper.mapToDomain(identityClient.createToken(request))
    }

}