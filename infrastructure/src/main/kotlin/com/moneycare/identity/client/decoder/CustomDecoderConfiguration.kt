package com.moneycare.identity.client.decoder

import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class CustomDecoderConfiguration {
    @Bean
    open fun errorDecoder(): ErrorDecoder {
        val defaultErrorDecoder = ErrorDecoder.Default()
        return CustomDecoder(defaultErrorDecoder)
    }

}