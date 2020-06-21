package app.isfaaghyth.hilt.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.isfaaghyth.hilt.util.DateFormatter
import app.isfaaghyth.hilt.util.DateFormatter.dateFormat
import java.lang.System.currentTimeMillis as systemTime

@Entity(tableName = "note")
data class Note(
    val title: String = "",
    val note: String = "",
    val date: String = dateFormat(systemTime())
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}