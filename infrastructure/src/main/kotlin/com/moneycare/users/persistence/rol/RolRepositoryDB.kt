package com.moneycare.users.persistence.rol

import com.moneycare.users.rol.Rol
import com.moneycare.users.rol.RolRepository
import com.moneycare.users.user.User
import org.springframework.stereotype.Repository

@Repository
class RolRepositoryDB: RolRepository {
    override fun addRoleByUser(user: User, roles: List<Rol>) {
        TODO("Not yet implemented")
    }
}