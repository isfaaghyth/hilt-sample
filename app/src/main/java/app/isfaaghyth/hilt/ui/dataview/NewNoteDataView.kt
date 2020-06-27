package app.isfaaghyth.hilt.ui.dataview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewNoteDataView(
    val title: String = "",
    val note: String = ""
) : Parcelable