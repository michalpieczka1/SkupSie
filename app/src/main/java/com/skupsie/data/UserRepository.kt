package com.skupsie.data

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(id:Int): Flow<User?>

    suspend fun insertUser(user:User)

    suspend fun deleteUser(user:User)

    suspend fun updateUser(user:User)
}