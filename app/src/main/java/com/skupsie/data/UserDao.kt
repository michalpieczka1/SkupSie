package com.skupsie.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user:User)

    @Update
    suspend fun update(user:User)

    @Delete
    suspend fun delete(user:User)

    @Query("UPDATE users SET name= :newName WHERE id= :userId")
    fun updateUser(newName:String,userId:Int)
    @Query("SELECT * from users WHERE id=:id")
    fun getUserById(id:Int): Flow<User?>

    @Query("SELECT * from users WHERE email=:email")
    fun getUserByEmail(email:String): Flow<User?>

    @Query("SELECT * from users WHERE email=:email AND password=:password")
    fun getUserByEmailAndPassword(email: String,password:String): Flow<User?>

    @Query("SELECT isPremium from users WHERE id=:id")
    fun getUserPremiumStatus(id: Int): Flow<UserPremiumStatus?>

}