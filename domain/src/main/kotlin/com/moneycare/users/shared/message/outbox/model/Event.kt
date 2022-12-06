package com.moneycare.users.shared.message.outbox.model

import java.time.LocalDateTime

abstract class Event : Message() {
    override var messageType = "event"
    val date : LocalDateTime = LocalDateTime.now()
}