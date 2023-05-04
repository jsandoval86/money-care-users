package com.moneycare.users.shared.exceptions

open class DomainException : RuntimeException {
    constructor(s: String): super(s)
    constructor(s: String, e: Exception) : super(s, e)
}
