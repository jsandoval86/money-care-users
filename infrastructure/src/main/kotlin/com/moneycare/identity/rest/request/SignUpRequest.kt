package com.moneycare.identity.rest.request

import javax.validation.constraints.NotEmpty

class SignUpRequest {
    @NotEmpty
    lateinit var name: String
    @NotEmpty
    lateinit var lastName: String
    @NotEmpty
    lateinit var email: String
    @NotEmpty
    lateinit var cellphone: String
    @NotEmpty
    lateinit var password: String
}