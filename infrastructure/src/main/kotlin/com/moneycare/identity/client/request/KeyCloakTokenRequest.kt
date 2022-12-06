package com.moneycare.identity.client.request

class KeyCloakTokenRequest(
    @feign.form.FormProperty("client_id")
    var clientId: String,
    @feign.form.FormProperty("client_secret")
    var clientSecret: String,
    @feign.form.FormProperty("grant_type")
    var grantType: String,
    var scope: String,
    var username: String,
    var password: String
)