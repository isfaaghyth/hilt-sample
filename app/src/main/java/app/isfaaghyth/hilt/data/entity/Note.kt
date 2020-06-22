package app.isfaaghyth.hilt.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.System.currentTimeMillis as systemTime

@Entity(tableName = "note")
data class Note(
    val title: String = "",
    val note: String = "",
    val date: Long = systemTime()
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}