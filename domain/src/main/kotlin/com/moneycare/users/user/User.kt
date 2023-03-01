package com.moneycare.users.user

import com.moneycare.users.rol.Rol
import com.moneycare.users.shared.model.Aggregate
import com.moneycare.users.shared.model.Cellphone
import com.moneycare.users.shared.model.Email
import java.time.LocalDateTime
import java.util.UUID

class User private constructor(
    private var id: UUID,
    private var name: String,
    private var lastName: String,
    private var email: Email,
    private var cellphone: Cellphone,
    private var createdDate: LocalDateTime,
    private var rol: Rol
) : Aggregate() {

    companion object {
        fun createPro(name: String, lastName: String, email: String, cellphone: String) : User {
            val e = Email.of(email)
            val c = Cellphone.of(cellphone)
            val newUser = User(UUID.randomUUID(), name, lastName, e, c, LocalDateTime.now(), Rol.pro)
            newUser.create()
            return newUser
        }
    }

    private fun create() {
        messages.add(
          UserCreatedEvent(this.id)
        )
    }


    fun getId(): UUID = this.id
    fun getName(): String = this.name
    fun getLastName(): String = this.lastName
    fun getEmail(): Email = this.email
    fun getCellphone(): Cellphone = this.cellphone
    fun getCreatedDate(): LocalDateTime = this.createdDate
    fun getRol(): Rol = this.rol
        

}