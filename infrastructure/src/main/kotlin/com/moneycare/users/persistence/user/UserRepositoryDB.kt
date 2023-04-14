package com.moneycare.users.persistence.user

import com.moneycare.users.user.User
import com.moneycare.users.user.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryDB: UserRepository {
    override fun save(user: User) {
        TODO("Not yet implemented")
    }
}