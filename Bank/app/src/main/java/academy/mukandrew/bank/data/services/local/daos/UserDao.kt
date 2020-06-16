package academy.mukandrew.bank.data.services.local.daos

import academy.mukandrew.bank.data.services.local.entities.USER_TABLE_NAME
import academy.mukandrew.bank.data.services.local.entities.UserEntity
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao : BaseDao<UserEntity> {
    @Query("SELECT * FROM $USER_TABLE_NAME")
    suspend fun selectUsers(): List<UserEntity?>

    @Query("DELETE FROM $USER_TABLE_NAME")
    suspend fun deleteAll()
}