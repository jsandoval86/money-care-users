package com.moneycare.users.user

interface UserRepository {
    fun save(user: User)
}