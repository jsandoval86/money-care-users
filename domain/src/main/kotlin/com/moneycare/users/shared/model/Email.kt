package com.moneycare.users.shared.model

import com.moneycare.users.shared.exceptions.TechnicalException

class Email private
    constructor(private var value: String){

    fun getValue(): String =  value

    companion object{
        fun of(value: String): Email {
            asserts(value)
            return Email(value)
        }

        private fun asserts(value: String) {
           if ( !value.contains("@")) throw TechnicalException("email must contains @ symbol")
        }
    }

}