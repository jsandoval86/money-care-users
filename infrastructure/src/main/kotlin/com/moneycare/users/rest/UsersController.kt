package com.moneycare.users.rest

import com.moneycare.permissions.PermissionChecker
import com.moneycare.permissions.Permissions
import com.moneycare.users.user.User
import com.moneycare.users.user.UserContextData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(URLUsersV1.URL_BASE)
class UsersController {

    @Autowired
    private lateinit var userAuthenticated: UserAuthenticated

    @GetMapping
    fun getUsers()  {
        // consulta(userAuthenticated.tenant, filtros)
        // PermissionChecker.check(userAuthenticated.getUserContextData().role, Permissions.read_user)
        // call use case
    }

}