package com.moneycare.users.shared.model

class Cellphone private
    constructor(
        private var countryCode: String,
        private var number: String
    ) {

    fun getFullCellphone(): String = countryCode.plus(number)


    companion object{
        fun of(value: String): Cellphone {
            return Cellphone("+57", value.substring(3))
        }
    }

}