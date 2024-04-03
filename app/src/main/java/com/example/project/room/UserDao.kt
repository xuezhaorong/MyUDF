package com.example.project.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM users")
    fun queryAll(): List<User>

    @Query("SELECT * FROM users WHERE userId IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM users WHERE userId in (:userIds)")
    fun queryByUserIds(userIds: List<Int>): Flow<List<User>>

    @Query("SELECT * FROM users WHERE userId = :userId")
    fun queryByUserId(userId: Int): Flow<User?>

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

//    @Insert
//    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("DELETE FROM users WHERE userId = :uid")
    fun deleteByUid(uid: Int)

    @Query("DELETE FROM users")
    fun deleteAll()
}