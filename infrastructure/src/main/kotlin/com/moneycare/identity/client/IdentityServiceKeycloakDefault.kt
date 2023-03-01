package com.moneycare.identity.client

import com.moneycare.identity.IdentityService
import com.moneycare.users.password.Password
import com.moneycare.users.token.UserToken
import com.moneycare.users.user.User
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(
    value=["keycloak.enabled"],
    havingValue = "false",
    matchIfMissing = false)
class IdentityServiceKeycloakDefault : IdentityService {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun createTokenByUserNameAndPassword(username: String, password: String): UserToken {
        log.warn("requesting create token, but identity service is disabled")
        return UserToken.random()
    }

    override fun createUser(user: User, password: Password) {
        log.warn("requesting create user, but identity service is disabled")
    }

    override fun refreshTokenUser(refreshToken: String): UserToken {
        log.warn("requesting refresh token, but identity service is disabled")
        return UserToken.random()
    }
}