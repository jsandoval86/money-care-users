package com.moneycare.users

import com.moneycare.shared.model.Cellphone
import com.moneycare.shared.model.Email
import java.time.LocalDateTime

class User(
    var name: String,
    var lastName: String,
    var email: Email,
    var cellphone: Cellphone,
    var createdDate: LocalDateTime
)