package app.isfaaghyth.hilt.base

import app.isfaaghyth.hilt.ui.factory.ItemTypeFactory

abstract class BaseDataView {
    abstract fun type(typeFactory: ItemTypeFactory): Int
}