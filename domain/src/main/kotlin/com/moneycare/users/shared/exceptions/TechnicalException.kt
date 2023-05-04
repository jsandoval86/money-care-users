package com.moneycare.users.shared.exceptions

open class TechnicalException: RuntimeException {
    constructor(s: String): super(s)
    constructor(s: String, e: Exception) : super(s, e)

}