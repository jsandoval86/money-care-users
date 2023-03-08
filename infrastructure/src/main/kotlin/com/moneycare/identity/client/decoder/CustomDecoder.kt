package com.moneycare.identity.client.decoder

import com.moneycare.users.user.exception.UnAuthorizedException
import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import java.lang.Exception

class CustomDecoder(
    private val defaultErrorDecoder : ErrorDecoder.Default
): ErrorDecoder {

    override fun decode(methodKey: String?, response: Response?): Exception {
       if ( response?.status() == HttpStatus.UNAUTHORIZED.value()) {
           return UnAuthorizedException("user unauthorized")
       }

        return defaultErrorDecoder.decode(methodKey, response)
    }

}