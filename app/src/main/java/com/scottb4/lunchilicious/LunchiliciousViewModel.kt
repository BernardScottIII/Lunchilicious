package com.scottb4.lunchilicious

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel


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

    private var _buttonText by mutableStateOf("Place Order")
    val buttonText = _buttonText

    fun updateButtonText() {
        _buttonText = if (_buttonText == "Place Order") {
            "Continue Shopping"
        } else {
            "Place Order"
        }
    }
}