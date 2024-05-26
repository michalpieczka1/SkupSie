package com.skupsie.data

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserById(id:Int): Flow<User?>
    fun getUserByEmail(email:String): Flow<User?>

    fun getUserByEmailAndPassword(email:String,password:String):Flow<User?>
    fun getUserPremiumStatus(id:Int): Flow<UserPremiumStatus?>
    fun isUserInDatabaseById(id:Int): Flow<Boolean>
    fun isUserInDatabaseByEmail(email: String): Flow<Boolean>
    fun isUserInDatabaseByEmailAndPassword(email: String,password: String):Flow<Boolean>

    suspend fun insertUser(user:User)

    suspend fun deleteUser(user:User)

    suspend fun updateUser(user:User)
}