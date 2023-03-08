package com.moneycare.identity.rest.mock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class KeycloakMock(private val port: Int) : WireMockServer(port) {

    fun starServer() {
        start()

        stubFor(
            WireMock.post(WireMock.urlPathMatching("/([a-zA-Z0-9-]*)/auth"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(ClassPathResource("mock/token-response.json").file.readText())
                )
        )

        stubFor(
            WireMock.post(WireMock.urlPathMatching("/([a-zA-Z0-9-]*)/users"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                )
        )

        stubFor(
            WireMock.get(WireMock.urlPathMatching("/([a-zA-Z0-9-]*)/userinfo"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(HttpStatus.UNAUTHORIZED.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                )
        )
    }

}