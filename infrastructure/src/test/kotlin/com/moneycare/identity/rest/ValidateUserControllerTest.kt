package com.moneycare.identity.rest

import com.moneycare.identity.rest.mock.KeycloakMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.UUID

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ValidateUserControllerTest : RestTest() {

    private val keycloakMock = KeycloakMock(8082)

    @BeforeAll
    fun beforeAll() {
        keycloakMock.starServer()
    }

    @AfterAll
    fun afterAll() {
        keycloakMock.stop()
    }

    @Test
    fun unAuthorizedUser() {
        mockMvc.perform(
            MockMvcRequestBuilders.get(URLIdentityV1.VALIDATE_TOKEN)
                .header("Authorization", "Bearer ${UUID.randomUUID()}")
        ).andExpect(MockMvcResultMatchers.status().isUnauthorized)
    }

}