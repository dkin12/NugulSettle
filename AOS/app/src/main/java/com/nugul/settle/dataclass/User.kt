package com.nugul.settle.dataclass

data class LoginRequest(
    val userIdx: String,
    val userPassword: String
)

data class UserResponse(
    val id: Long,
    val userIdx: String,
    val userEmail: String?,
    val userName: String,
    val userCreateDate: String,
    val userUpdateDate: String,
    val userIsDeleted: Boolean,
    val userDeletedDate: String?,
    // TODO(나중에 제거할 것 )
    val userPassword: String
)