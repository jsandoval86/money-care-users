package com.moneycare.users.shared.model

import com.moneycare.users.shared.message.outbox.model.Message

open class Aggregate {

    val messages: MutableList<Message> = mutableListOf()

}