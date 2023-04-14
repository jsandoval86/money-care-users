package com.moneycare.identity.client

import com.moneycare.identity.client.decoder.CustomDecoderConfiguration
import com.moneycare.identity.client.request.KeyCloakCreateUserRequest
import com.moneycare.identity.client.request.KeyCloakTokenRefreshRequest
import com.moneycare.identity.client.request.KeyCloakTokenRequestGranTypeClientCredentials
import com.moneycare.identity.client.request.KeyCloakTokenRequestGrantTypePassword
import com.moneycare.identity.client.response.KeyCloakTokenResponse
import com.moneycare.identity.client.response.KeyCloakUserInfoResponse
import feign.Response
import feign.form.spring.SpringFormEncoder
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.beans.factory.ObjectFactory
import feign.codec.Encoder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader


@FeignClient(name = "key-cloak", url = "\${keycloak.host}", configuration = [CustomDecoderConfiguration::class])
interface IdentityKeycloakFeignClient {

    @PostMapping("\${keycloak.urls.token}", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createToken(
        @PathVariable realm: String,
        request: KeyCloakTokenRequestGrantTypePassword
    ): KeyCloakTokenResponse

    @PostMapping("\${keycloak.urls.token}", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createToken(
        @PathVariable realm: String,
        request: KeyCloakTokenRequestGranTypeClientCredentials
    ): KeyCloakTokenResponse

    @PostMapping("\${keycloak.urls.users}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(
        @RequestHeader("Authorization") token: String,
        @PathVariable realm: String,
        request: KeyCloakCreateUserRequest
    ): Response

    @PostMapping("\${keycloak.urls.token}", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun refreshToken(
        @PathVariable realm: String,
        request: KeyCloakTokenRefreshRequest
    ): KeyCloakTokenResponse

    @GetMapping("\${keycloak.urls.user-info}", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun validateToken(
        @PathVariable realm: String,
        @RequestHeader("Authorization") token: String
    ): KeyCloakUserInfoResponse

    @DeleteMapping("\${keycloak.urls.users}/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteUser(
        @PathVariable realm: String,
        @PathVariable id: String,
        @RequestHeader("Authorization") token: String
    )


    @org.springframework.context.annotation.Configuration
    open class Configuration {
        @Bean
        open fun feignFormEncoder(converters: ObjectFactory<HttpMessageConverters?>?): Encoder {
            return SpringFormEncoder(SpringEncoder(converters))
        }
    }
}