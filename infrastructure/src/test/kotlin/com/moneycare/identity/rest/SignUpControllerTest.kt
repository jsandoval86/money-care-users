package com.moneycare.identity.rest

import com.moneycare.identity.rest.mock.KeycloakMock
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignUpControllerTest : RestTest() {

    private val url = "/log-in"
    private val keycloakMock = KeycloakMock(8082)



}