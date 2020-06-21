package app.isfaaghyth.hilt.ui.dataview

import app.isfaaghyth.hilt.base.BaseDataView
import app.isfaaghyth.hilt.ui.factory.ItemTypeFactory

class EmptyStateDataView: BaseDataView() {

    override fun type(typeFactory: ItemTypeFactory): Int {
        return typeFactory.type(this)
    }

}