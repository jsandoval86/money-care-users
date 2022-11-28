package com.moneycare.shared.message.outbox.model

abstract class Message  {
    abstract var version : Int
    abstract var messageType : String
    var metadata : MutableMap<String, Any> = mutableMapOf()

    abstract fun type() : String

}