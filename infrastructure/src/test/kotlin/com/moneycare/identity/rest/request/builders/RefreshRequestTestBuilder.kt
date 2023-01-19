package com.moneycare.identity.rest.request.builders

import com.moneycare.identity.rest.request.RefreshRequest
import java.util.UUID

class RefreshRequestTestBuilder {

    fun build(): RefreshRequest = RefreshRequest().apply {
        this.token = UUID.randomUUID().toString()
    }
}