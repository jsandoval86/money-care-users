package com.moneycare.identity.client.response

class KeyCloakTokenResponse {
    var accessToken: String? = null
    var refreshToken: String? = null
    var expiresIn: Int? = null
    var refreshExpiresIn: Int? = null
    var tokenType: String? = null

    fun getTokenBearer(): String = "Bearer $accessToken"

}