package com.moneycare.config.rest.exception

import com.moneycare.users.shared.exceptions.TechnicalException

class NoFoundAuthorizationHeader: TechnicalException {
    constructor(s: String): super(s)
    constructor(s: String, e: Exception) : super(s, e)

}