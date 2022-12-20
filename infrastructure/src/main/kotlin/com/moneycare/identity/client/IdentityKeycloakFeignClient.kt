package com.moneycare.identity.client

import com.moneycare.identity.client.request.KeyCloakCreateUserRequest
import com.moneycare.identity.client.request.KeyCloakTokenRequest
import com.moneycare.identity.client.response.KeyCloakTokenResponse
import feign.form.spring.SpringFormEncoder
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.beans.factory.ObjectFactory
import feign.codec.Encoder

@FeignClient(name = "keyCloak", url = "\${keycloak.host}")
interface IdentityKeycloakFeignClient {

    @PostMapping("\${keycloak.urls.token}", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createToken(request: KeyCloakTokenRequest): KeyCloakTokenResponse

    @PostMapping("\${keycloak.urls.users}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(request: KeyCloakCreateUserRequest)

    open class Configuration {
        @Bean
        open fun feignFormEncoder(converters: ObjectFactory<HttpMessageConverters?>?): Encoder {
            return SpringFormEncoder(SpringEncoder(converters))
        }
    }
}