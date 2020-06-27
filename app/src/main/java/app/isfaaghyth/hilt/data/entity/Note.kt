package app.isfaaghyth.hilt.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.System.currentTimeMillis as systemTime

@Entity(tableName = "note")
data class Note(
    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "note")
    val note: String = "",

    @ColumnInfo(name = "date")
    val date: Long = systemTime()
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

}