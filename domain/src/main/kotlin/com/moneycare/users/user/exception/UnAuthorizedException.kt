package com.moneycare.users.user.exception

import com.moneycare.users.shared.exceptions.DomainException

class UnAuthorizedException(message: String) : DomainException(message)