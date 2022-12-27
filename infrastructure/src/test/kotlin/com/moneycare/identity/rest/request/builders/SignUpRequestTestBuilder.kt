package com.moneycare.identity.rest.request.builders

import com.moneycare.identity.rest.request.SignUpRequest

class SignUpRequestTestBuilder {
    lateinit var name: String
    lateinit var lastName: String
    lateinit var email: String
    lateinit var cellphone: String
    lateinit var password: String

    fun default(): SignUpRequestTestBuilder {
        this.name = "first user"
        this.lastName = "lastName first user"
        this.email = "email@email.com"
        this.cellphone = "1010101"
        this.password = "qoaliwkseudjryfhtg"
        return this
    }

    fun build(): SignUpRequest{
        return SignUpRequest().apply {
            this.name = this@SignUpRequestTestBuilder.name
            this.lastName = this@SignUpRequestTestBuilder.lastName
            this.email = this@SignUpRequestTestBuilder.email
            this.cellphone = this@SignUpRequestTestBuilder.cellphone
            this.password = this@SignUpRequestTestBuilder.password
        }
    }

}