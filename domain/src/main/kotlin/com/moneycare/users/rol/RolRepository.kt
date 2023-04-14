package com.moneycare.users.rol

import com.moneycare.users.user.User

interface RolRepository {
    fun addRoleByUser(user: User, roles: List<Rol>)
}