package com.example.pix.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Так как в ТЗ нет указаний про работу с БД и никакой полезной логики с БД в рамках данного
 * приложения я не придумал, БД не используется.
 */

@Database(entities = [PictureDbo::class], version = 1)
abstract class PictureDatabase: RoomDatabase() {
    abstract fun getPictureDao(): PictureDao
}