package app.isfaaghyth.hilt.data

import androidx.room.Database
import androidx.room.RoomDatabase
import app.isfaaghyth.hilt.data.entity.Note
import app.isfaaghyth.hilt.data.local.NoteDao

@Database(
    entities = [Note::class],
    exportSchema = false,
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}