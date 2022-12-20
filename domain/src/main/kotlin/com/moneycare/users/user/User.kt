package com.moneycare.users.user

import com.moneycare.users.shared.model.Aggregate
import com.moneycare.users.shared.model.Cellphone
import com.moneycare.users.shared.model.Email
import java.time.LocalDateTime
import java.util.UUID

class User private constructor(
    private var uuid: UUID,
    private var name: String,
    private var lastName: String,
    private var email: Email,
    private var cellphone: Cellphone,
    private var createdDate: LocalDateTime
) : Aggregate() {

    companion object {
        fun create(name: String, lastName: String, email: Email, cellphone: Cellphone) : User {
            val newUser = User(UUID.randomUUID(), name, lastName, email, cellphone, LocalDateTime.now())
            newUser.create()
            return newUser
        }
    }

    private fun create() {
        messages.add(
          UserCreatedEvent(this.uuid)
        )
    }


    fun getUuid(): UUID = this.uuid
    fun getName(): String = this.name
    fun getLastName(): String = this.lastName
    fun getEmail(): Email = this.email
    fun getCellphone(): Cellphone = this.cellphone
    fun getCreatedDate(): LocalDateTime = this.createdDate
        

}