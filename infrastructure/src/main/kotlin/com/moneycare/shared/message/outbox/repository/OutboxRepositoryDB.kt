package com.moneycare.shared.message.outbox.repository

import com.moneycare.shared.messages.outbox.repository.OutboxRepository
import com.moneycare.users.shared.message.outbox.model.Message
import org.springframework.stereotype.Repository


@Repository
class OutboxRepositoryDB : OutboxRepository {
    override fun save(message: Message) {
        TODO("Not yet implemented")
    }

    override fun save(messages: List<Message>) {
        TODO("Not yet implemented")
    }

}