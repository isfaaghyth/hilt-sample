package app.isfaaghyth.hilt.di

import android.content.Context
import androidx.room.Room
import app.isfaaghyth.hilt.data.NoteDatabase
import app.isfaaghyth.hilt.data.local.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module object DatabaseModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note.db"
        ).build()
    }

    @Provides
    fun provideNoteDao(db: NoteDatabase): NoteDao {
        return db.noteDao()
    }

}