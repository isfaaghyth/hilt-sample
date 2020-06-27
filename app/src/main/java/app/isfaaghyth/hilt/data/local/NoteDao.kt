package app.isfaaghyth.hilt.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import app.isfaaghyth.hilt.data.entity.Note

@Dao interface NoteDao {

    @Query("SELECT * FROM note ORDER BY id DESC")
    fun notes(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE id=:id LIMIT 1")
    fun noteById(id: Long): Note

    @Query("SELECT * FROM note WHERE id=:id")
    fun exist(id: Long): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg note: Note)

    @Query("DELETE FROM note WHERE id=:id")
    fun delete(id: Long)

    @Update
    fun update(note: Note)

}