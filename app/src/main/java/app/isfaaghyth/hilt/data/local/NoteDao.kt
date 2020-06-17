package app.isfaaghyth.hilt.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import app.isfaaghyth.hilt.data.entity.Note

@Dao interface NoteDao {

    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getNotes(): LiveData<List<Note>>

    @Insert
    fun addNote(vararg note: Note)

    @Delete
    fun deleteNote(note: Note)

}