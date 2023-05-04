package com.moneycare.config.rest

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import java.util.UUID


// TODO: convert in component
    // sign token
    // get claims in signed token
class JWTHelper {
    companion object{
        fun generateJwtToken(): String {
            val claims: MutableMap<String, Any> = mutableMapOf()
            claims["id_user"]       = UUID.randomUUID()
            claims["id_tenant"]     = UUID.randomUUID()
            claims["role"]          = "super-admin"
            //claims["copropiedades"] = listOf(UUID.randomUUID(), UUID.randomUUID())
            claims["access_token"]  = "${UUID.randomUUID()}${UUID.randomUUID()}${UUID.randomUUID()}${UUID.randomUUID()}"

            return Jwts.builder()
                .setClaims(claims)
                .compact()
        }


        fun getClaims(token: String): Map<String, Any> {
            return Jwts.parser().parse(token).body as Claims
        }

    }

}