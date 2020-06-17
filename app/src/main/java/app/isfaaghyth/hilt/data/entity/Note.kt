package app.isfaaghyth.hilt.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.isfaaghyth.hilt.util.DateFormatter

@Entity(tableName = "note")
data class Note(
    val title: String = "",
    val note: String = "",
    val date: Long = System.currentTimeMillis()
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun getDateFormatted(): String {
        return DateFormatter.format(date)
    }

}