package com.scottb4.lunchilicious.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.scottb4.lunchilicious.LunchiliciousApplication
import com.scottb4.lunchilicious.Repository
import com.scottb4.lunchilicious.data.MenuItem
import com.scottb4.lunchilicious.domain.MenuItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MenuItemViewModel(
    private val menuItemRepository: MenuItemRepository
): ViewModel() {
    fun createMenuItems() {
        viewModelScope.launch {
            Repository.getItems().forEach { menuItem ->
                menuItemRepository.insertMenuItem(menuItem = menuItem)
            }
        }
    }

    fun getAllMenuItems(): Flow<List<MenuItem>> {
        return menuItemRepository.getAllMenuItemsStream()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
                            LunchiliciousApplication).menuItemRepository
                MenuItemViewModel(menuItemRepository = myRepository)
            }
        }
    }
}