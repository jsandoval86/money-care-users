package com.moneycare.config.rest

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.validator.internal.engine.path.PathImpl
import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.util.function.Consumer
import javax.validation.ConstraintViolation

class ErrorResponse(
    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val error: String? = null,
) {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var errors: MutableList<ApiError> = ArrayList()

    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private val timestamp: LocalDateTime = LocalDateTime.now()

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var debugMessage: String? = null

    fun addValidationError(field: String?, value: String?, message: String?) {
        errors.add(ApiError(field, value, message))
    }

    fun addValidationErrors(constraintViolations: Set<ConstraintViolation<*>>) {
        errors = ArrayList()
        constraintViolations.forEach(
            Consumer { cv: ConstraintViolation<*> ->
                this.addValidationError(
                    cv
                )
            }
        )
    }

    private fun addValidationError(cv: ConstraintViolation<*>) {
        errors.add(ApiError((cv.propertyPath as PathImpl).leafNode.asString(), cv.invalidValue, cv.message))
    }

    class ApiError(val field: String?, val value: Any?, val message: String?)

}