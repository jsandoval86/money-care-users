package com.moneycare.users.user.exception

import com.moneycare.users.shared.exceptions.DomainException

class UnAuthorizedException: DomainException {
    constructor(s: String): super(s)
    constructor(s: String, e: Exception) : super(s, e)
}