package com.moneycare.permissions

class PermissionChecker {

    companion object {
        val map: MutableMap<String, List<Permissions>> = mutableMapOf()

        init {
            map[Role.super_admin.name] = listOf(
                Permissions.create_user,
                Permissions.update_user,
                Permissions.create_tenant
            )
        }

        fun check(role: Role, permission: Permissions): Boolean {
            return map[role.name]?.contains(permission) ?: false
        }
    }

}