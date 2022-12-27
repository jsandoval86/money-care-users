package com.moneycare.identity.rest.mappers

import com.moneycare.identity.rest.response.UserResponse
import com.moneycare.users.user.User
import org.springframework.stereotype.Component

@Component
class UserResponseMapper {

    fun mapToResponse(user: User): UserResponse {
        return  UserResponse(
            user.getId().toString(),
            user.getName(),
            user.getLastName(),
            user.getEmail().getValue(),
            user.getCellphone().getFullCellphone(),
            user.getCreatedDate())
    }
}