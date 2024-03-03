package com.example.lunchilicious

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LunchiliciousViewModel: ViewModel() {

    // Make button persistent at bottom of screen
    // Have button text stored here
    // On items screen = Place Order
    // On order screen = Continue Shopping

    private val _checkbox1Val: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkbox1Val: LiveData<Boolean> = _checkbox1Val
    private val _checkbox2Val: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkbox2Val: LiveData<Boolean> = _checkbox2Val
    private val _checkbox3Val: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkbox3Val: LiveData<Boolean> = _checkbox3Val
    private val _checkbox4Val: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkbox4Val: LiveData<Boolean> = _checkbox4Val
    private val _checkbox5Val: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkbox5Val: LiveData<Boolean> = _checkbox5Val
    private val _checkbox6Val: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkbox6Val: LiveData<Boolean> = _checkbox6Val
    private val _checkbox7Val: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkbox7Val: LiveData<Boolean> = _checkbox7Val
    private val _checkbox8Val: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkbox8Val: LiveData<Boolean> = _checkbox8Val
    private val _checkbox9Val: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkbox9Val: LiveData<Boolean> = _checkbox9Val
    private val _checkbox10Val: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkbox10Val: LiveData<Boolean> = _checkbox10Val

    fun onCheckedChange1(value:Boolean) {
        _checkbox1Val.value = value
    }
    fun onCheckedChange2(value:Boolean) {
        _checkbox2Val.value = value
    }
    fun onCheckedChange3(value:Boolean) {
        _checkbox3Val.value = value
    }
    fun onCheckedChange4(value:Boolean) {
        _checkbox4Val.value = value
    }
    fun onCheckedChange5(value:Boolean) {
        _checkbox5Val.value = value
    }
    fun onCheckedChange6(value:Boolean) {
        _checkbox6Val.value = value
    }
    fun onCheckedChange7(value:Boolean) {
        _checkbox7Val.value = value
    }
    fun onCheckedChange8(value:Boolean) {
        _checkbox8Val.value = value
    }
    fun onCheckedChange9(value:Boolean) {
        _checkbox9Val.value = value
    }
    fun onCheckedChange10(value:Boolean) {
        _checkbox10Val.value = value
    }
}

@Composable
fun LunchiliciousScreen (
    lunchiliciousViewModel: LunchiliciousViewModel = LunchiliciousViewModel(),
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()
    val checkbox1State: Boolean by lunchiliciousViewModel.checkbox1Val.observeAsState(false)
    val checkbox2State: Boolean by lunchiliciousViewModel.checkbox2Val.observeAsState(false)
    val checkbox3State: Boolean by lunchiliciousViewModel.checkbox3Val.observeAsState(false)
    val checkbox4State: Boolean by lunchiliciousViewModel.checkbox4Val.observeAsState(false)
    val checkbox5State: Boolean by lunchiliciousViewModel.checkbox5Val.observeAsState(false)
    val checkbox6State: Boolean by lunchiliciousViewModel.checkbox6Val.observeAsState(false)
    val checkbox7State: Boolean by lunchiliciousViewModel.checkbox7Val.observeAsState(false)
    val checkbox8State: Boolean by lunchiliciousViewModel.checkbox8Val.observeAsState(false)
    val checkbox9State: Boolean by lunchiliciousViewModel.checkbox9Val.observeAsState(false)
    val checkbox10State: Boolean by lunchiliciousViewModel.checkbox10Val.observeAsState(false)

    LazyColumn (
        state = state,
        modifier = modifier
            .fillMaxWidth()
    ) {// Make the argument data.size
        item {
            StatelessMenuItem(
                id = 1,
                type = "Hoagie",
                name = "BLT Hoagie",
                description = "Cold, Onion, lettuce, tomato",
                unitPrice = 6.95
            )
            Checkbox(
                checked = checkbox1State,
                onCheckedChange = {
                    lunchiliciousViewModel.onCheckedChange1(it)
                }
            )
        }
        item {
            StatelessMenuItem(
                id = 2,
                type = "Hoagie",
                name = "Cheese Hoagie",
                description = "Cheese, mayos, lettuce, tomato",
                unitPrice = 6.95
            )
            Checkbox(
                checked = checkbox2State,
                onCheckedChange = {
                    lunchiliciousViewModel.onCheckedChange2(it)
                }
            )
        }
        item {
            StatelessMenuItem(
                id = 3,
                type = "Pizza",
                name = "Plain Pizza",
                description = "cheese and tomato",
                unitPrice = 9.50
            )
            Checkbox(
                checked = checkbox3State,
                onCheckedChange = {
                    lunchiliciousViewModel.onCheckedChange3(it)
                }
            )
        }
        item {
            StatelessMenuItem(
                id = 4,
                type = "Side",
                name = "Fries",
                description = "large hot fries",
                unitPrice = 2.95
            )
            Checkbox(
                checked = checkbox4State,
                onCheckedChange = {
                    lunchiliciousViewModel.onCheckedChange4(it)
                }
            )
        }
        item {
            StatelessMenuItem(
                id = 5,
                type = "Side",
                name = "Gravy Fries",
                description = "Fries with gravy on top",
                unitPrice = 3.95
            )
            Checkbox(
                checked = checkbox5State,
                onCheckedChange = {
                    lunchiliciousViewModel.onCheckedChange5(it)
                }
            )
        }
        item {
            StatelessMenuItem(
                id = 6,
                type = "Entree",
                name = "Raspberry Chicken",
                description = "Fried chicken topped with raspberry sauce and pineapple salsa",
                unitPrice = 10.00
            )
            Checkbox(
                checked = checkbox6State,
                onCheckedChange = {
                    lunchiliciousViewModel.onCheckedChange6(it)
                }
            )
        }
        item {
            StatelessMenuItem(
                id = 7,
                type = "Entree",
                name = "Dragon & Phoenix",
                description = "Shrimp w. garlic sauce & chunk chicken fried in spicy sauce",
                unitPrice = 12.00
            )
            Checkbox(
                checked = checkbox7State,
                onCheckedChange = {
                    lunchiliciousViewModel.onCheckedChange7(it)
                }
            )
        }
        item {
            StatelessMenuItem(
                id = 8,
                type = "Burrito",
                name = "Everymeat Burrito",
                description = "Chicken, beef, pork, lobster, shrimp, fish, duck, lamb, turkey, bison, cornish game hen, goose, pheasant, qual, rabbit, squab, venison, boar, alligator, antelope, caribou, elk, ostrich, turtle, rattlesnake, and kangaroo",
                unitPrice = 49.95
            )
            Checkbox(
                checked = checkbox8State,
                onCheckedChange = {
                    lunchiliciousViewModel.onCheckedChange8(it)
                }
            )
        }
        item {
            StatelessMenuItem(
                id = 9,
                type = "Hoagie",
                name = "Beer-Battered Cod Sandwich",
                description = "Cod and tartar sauce on Brioche Bun",
                unitPrice = 6.95
            )
            Checkbox(
                checked = checkbox9State,
                onCheckedChange = {
                    lunchiliciousViewModel.onCheckedChange9(it)
                }
            )
        }
        item {
            StatelessMenuItem(
                id = 10,
                type = "Pizza",
                name = "Margherita Pizza",
                description = "Fresh tomatoes, basil and mozzarella on neopolitan crust",
                unitPrice = 6.95
            )
            Checkbox(
                checked = checkbox10State,
                onCheckedChange = {
                    lunchiliciousViewModel.onCheckedChange10(it)
                }
            )
        }
    }
//    ElevatedButton(
//        onClick = { /* TODO */ }) {
//        Text(text = "Place Order")
//    }
}

//@Composable
//fun StatefulMenuItem(
//    id: Int,
//    type: String,
//    name: String,
//    description: String,
//    unitPrice: Double,
//    modifier: Modifier = Modifier
//) {
//    StatelessMenuItem(
//        id = id,
//        type = type,
//        name = name,
//        description = description,
//        unitPrice = unitPrice
//    )
//
//}

@Composable
fun StatelessMenuItem(
    id: Int,
    type: String,
    name: String,
    description: String,
    unitPrice: Double,
    modifier: Modifier = Modifier
) {

    Text(text = "id = $id")
    Text(text = "type = $type")
    Text(text = "name = $name")
    Text(text = "description = $description")
    Text(text = "unitPrice = $unitPrice")
}