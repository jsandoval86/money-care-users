package com.moneycare.users.shared.model

class Cellphone private
    constructor(
        private var countryCode: String,
        private var number: String
    ) {

    fun getFullCellphone(): String = countryCode.plus(number)

    companion object{
        fun of(value: String): Cellphone {
            return if (value.startsWith("+")){
                Cellphone(value.substring(0, 3), value.substring(3))
            } else {
                Cellphone("+57", value.substring(0))
            }
        }
    }

}