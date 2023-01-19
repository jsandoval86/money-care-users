package com.moneycare.identity.rest

import com.moneycare.identity.rest.mock.KeycloakMock
import com.moneycare.identity.rest.request.builders.LoginRequestTestBuilder
import com.moneycare.identity.rest.request.builders.RefreshRequestTestBuilder
import org.hamcrest.Matchers
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthenticationControllerTest : RestTest() {

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
    fun authenticate() {
        val loginRequest = LoginRequestTestBuilder().defaul()
            .build()

        mockMvc.perform(
            MockMvcRequestBuilders.post(URLIdentityV1.LOGIN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.access_token", Matchers.equalTo("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJucFljYnlYWTFBNm93Rk85M0d3Vkx")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.token_type", Matchers.equalTo("Bearer")))
    }

    @Test
    fun refreshToken() {
        val refreshTokenRequest = RefreshRequestTestBuilder().build()

        mockMvc.perform(
            MockMvcRequestBuilders.post(URLIdentityV1.REFRESH_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(refreshTokenRequest))
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.access_token", Matchers.equalTo("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJucFljYnlYWTFBNm93Rk85M0d3Vkx")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.token_type", Matchers.equalTo("Bearer")))
    }

    // TODO: test external api errors

}