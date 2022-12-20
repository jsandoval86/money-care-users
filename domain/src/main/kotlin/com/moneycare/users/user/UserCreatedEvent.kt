package com.moneycare.users.user

import com.moneycare.users.shared.message.outbox.model.Event
import java.util.UUID

class UserCreatedEvent(
    private val idUser: UUID
): Event() {

    override var version = 1
    override fun type(): String {
        return "moneycare.users.$version.$messageType.user.created"
    }

}