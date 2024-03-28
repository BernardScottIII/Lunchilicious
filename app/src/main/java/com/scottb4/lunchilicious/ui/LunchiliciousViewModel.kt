package com.scottb4.lunchilicious.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.scottb4.lunchilicious.Repository

class LunchiliciousViewModel: ViewModel() {

    // Create a mutableStateListOf() with x number of false values, not 10
    private var _checkboxValueList = mutableStateListOf<Boolean>()

    private fun createCheckboxValueList(): SnapshotStateList<Boolean> {
        val length = Repository().getItems().size

        for (i in 0..length) {
            _checkboxValueList.add(false)
        }

        return _checkboxValueList
    }

    val checkboxValueList = createCheckboxValueList()
}