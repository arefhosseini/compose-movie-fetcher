package com.fearefull.composemoviefetcher.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        // TODO: models should added here
               ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    // TODO: daos should added here
}