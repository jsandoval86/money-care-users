package com.moneycare.identity.rest.request.builders

import com.moneycare.identity.rest.request.LoginRequest

class LoginRequestTestBuilder {

    lateinit var username : String
    lateinit var password : String

    fun defaul(): LoginRequestTestBuilder {
        this.username = "username@email.com"
        this.password = "thepassword"
        return this
    }

    fun build(): LoginRequest {
        return LoginRequest().apply {
            this.username = this@LoginRequestTestBuilder.username
            this.password = this@LoginRequestTestBuilder.password
        }
    }
}