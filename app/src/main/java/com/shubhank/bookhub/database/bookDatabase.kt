package com.shubhank.bookhub.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [BookEntity::class], version = 1)
abstract class bookDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao
}