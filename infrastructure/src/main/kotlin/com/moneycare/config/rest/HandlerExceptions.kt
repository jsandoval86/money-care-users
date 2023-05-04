package com.moneycare.config.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.moneycare.config.rest.exception.NoFoundAuthorizationHeader
import com.moneycare.users.user.exception.UnAuthorizedException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class HandlerExceptions {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(UnAuthorizedException::class)
    fun handle(e: UnAuthorizedException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(status = HttpStatus.UNAUTHORIZED, error = "user unauthorized")
        log.debug(objectMapper.writeValueAsString(error), e)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(NoFoundAuthorizationHeader::class)
    fun handle(e: NoFoundAuthorizationHeader): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(status = HttpStatus.UNAUTHORIZED, error = "user unauthorized")
        log.debug(objectMapper.writeValueAsString(error), e)
        return ResponseEntity(error, error.status)
    }
}