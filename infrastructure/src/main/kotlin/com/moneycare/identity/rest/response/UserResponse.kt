package com.moneycare.identity.rest.response

import java.time.LocalDateTime

data class UserResponse(
    var id: String,
    var name: String,
    var lastName: String,
    var email: String,
    var cellphone: String,
    var createdDate: LocalDateTime
)