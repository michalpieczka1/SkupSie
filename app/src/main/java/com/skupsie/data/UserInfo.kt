package com.skupsie.data

data class UserInfo(
    val id:Int = 0,
    val name:String = "",
    val email:String = "",
    val password:String = "",
    val isPremium:Boolean = false
)
