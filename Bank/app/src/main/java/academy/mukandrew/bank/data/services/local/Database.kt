package academy.mukandrew.bank.data.services.local

import academy.mukandrew.bank.data.services.local.daos.UserDao
import academy.mukandrew.bank.data.services.local.entities.UserEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val dbName = "bankDatabase"
        const val dbPassword = "DBPassword"
    }
}