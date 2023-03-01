package com.moneycare.users.password

import com.moneycare.users.shared.exceptions.DomainException

class Password private constructor(
    val word: String
) {

    companion object{
        fun of(word: String): Password{
            assert(word)
            return Password(word)
        }

        private fun assert(word: String) {
            if(word.trim().isEmpty()) {
                throw DomainException("password cannot be empty")
            }
            assertInvalidCharacters(word)
        }

        private fun assertInvalidCharacters(word: String) {
            if ( word.contains("@")){
                throw DomainException("password cannot contains symbol @")
            }

            if ( word.contains("%")) {
                throw DomainException("password cannot contains symbol %")
            }
        }
    }

    fun getText(): String {
        return word
    }

}