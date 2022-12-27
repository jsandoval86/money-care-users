package com.moneycare.identity.rest

import com.moneycare.identity.rest.mock.KeycloakMock
import com.moneycare.identity.rest.request.builders.SignUpRequestTestBuilder
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
class SignUpControllerTest : RestTest() {

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
    fun signup() {
        val signupRequest = SignUpRequestTestBuilder().default().build()

        mockMvc.perform(
            MockMvcRequestBuilders.post(URLIdentity.SIGN_UP)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest))
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(signupRequest.name)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.last_name", Matchers.equalTo(signupRequest.lastName)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.equalTo(signupRequest.email)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cellphone", Matchers.equalTo("+57".plus(signupRequest.cellphone))))
            .andExpect(MockMvcResultMatchers.jsonPath("$.created_date", Matchers.notNullValue()))
    }

}