package com.moneycare.identity.client.request

open class KeyCloakTokenRequestBase(
    @feign.form.FormProperty("client_id")
    var clientId: String,
    @feign.form.FormProperty("client_secret")
    var clientSecret: String,
    @feign.form.FormProperty("grant_type")
    var grantType: String,
)