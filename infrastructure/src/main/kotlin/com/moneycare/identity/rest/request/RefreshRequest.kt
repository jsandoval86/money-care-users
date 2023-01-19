package com.moneycare.identity.rest.request

import javax.validation.constraints.NotEmpty

class RefreshRequest {
    @NotEmpty
    lateinit var token: String
}
