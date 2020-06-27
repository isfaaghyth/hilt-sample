package app.isfaaghyth.hilt.ui.dataview

sealed class DetailViewState {
    object Add: DetailViewState()
    object Detail: DetailViewState()
    object None: DetailViewState()
}