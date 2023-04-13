package com.moneycare.identity.client.request

class KeyCloakTokenRequestGrantTypePassword(
    @feign.form.FormProperty("client_id")
    var clientId: String,
    @feign.form.FormProperty("client_secret")
    var clientSecret: String,
    @feign.form.FormProperty("grant_type")
    var grantType: String,
    @feign.form.FormProperty("scope")
    var scope: String,
    @feign.form.FormProperty("username")
    var username: String,
    @feign.form.FormProperty("password")
    var password: String
)