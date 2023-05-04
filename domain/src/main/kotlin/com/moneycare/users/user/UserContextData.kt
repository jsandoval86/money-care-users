package com.moneycare.users.user

class UserContextData private constructor(
    var userId: String,
    var tenantId: String,
    var role: String,
    var copropiedades: List<String>,
    var accessToken: String,
) {

    companion object{
        fun create(userId: String, tenantId: String, role: String, copropiedades: List<String>, accessToken: String): UserContextData {
            return UserContextData(userId, tenantId, role, copropiedades, accessToken)
        }
    }

}