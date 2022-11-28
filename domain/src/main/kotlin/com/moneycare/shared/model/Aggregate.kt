package com.moneycare.shared.model

import com.moneycare.shared.message.outbox.model.Message

open class Aggregate {

    val messages: MutableList<Message> = mutableListOf()

}