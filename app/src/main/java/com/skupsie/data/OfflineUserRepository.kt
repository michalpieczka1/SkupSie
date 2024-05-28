package com.skupsie.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineUserRepository(private val userDao: UserDao) : UserRepository {

    override fun getUserById(id: Int): Flow<User?> = userDao.getUserById(id)
    override fun getUserByEmail(email: String): Flow<User?> = userDao.getUserByEmail(email)
    override fun getUserByEmailAndPassword(email: String, password: String): Flow<User?> = userDao.getUserByEmailAndPassword(email,password)

    override fun getUserPremiumStatus(id: Int): Flow<UserPremiumStatus?> = userDao.getUserPremiumStatus(id)

    override fun isUserInDatabaseById(id: Int): Flow<Boolean> {
        return userDao.getUserById(id)
            .map { user -> user != null }
    }
    override fun isUserInDatabaseByEmail(email: String): Flow<Boolean> {
        return userDao.getUserByEmail(email)
            .map { user -> user != null }
    }
    override fun isUserInDatabaseByEmailAndPassword(email: String, password: String): Flow<Boolean> {
        return userDao.getUserByEmailAndPassword(email, password)
            .map { user -> user != null }
    }

    fun isUserPremium(id:Int): Flow<Boolean> {
        return userDao.getUserPremiumStatus(id)
            .map { userPremiumStatus ->
                userPremiumStatus?.isPremium ?: false
            }
    }


    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun updateUser(user: User) = userDao.update(user)
    override suspend fun updateUserName(newName: String, userId: Int) = userDao.updateUser(newName, userId)
}