package com.moneycare.users.user

import com.moneycare.users.shared.model.Cellphone
import com.moneycare.users.shared.model.Email
import java.time.LocalDateTime

class User(
    var name: String,
    var lastName: String,
    var email: Email,
    var cellphone: Cellphone,
    var createdDate: LocalDateTime
)