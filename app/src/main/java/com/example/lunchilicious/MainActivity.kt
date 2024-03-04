package com.example.lunchilicious

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunchilicious.ui.theme.LunchiliciousTheme

object LunchiliciousScreen {
    const val OrderScreen = "OrderScreen"
    const val ConfirmationScreen = "ConfirmationScreen"
}

class LunchiliciousViewModel: ViewModel() {

    var checkboxValues by mutableStateOf(arrayOf(false, false, false, false, false, false, false, false, false, false))

    var buttonText by mutableStateOf("Place Order")

    fun updateCheckbox(idx:Int) {
        var tempArray = checkboxValues.copyOf()
        tempArray[idx] = !tempArray[idx]
        checkboxValues = tempArray
//        Log.i("CBVs-UDCB", "checkboxValues[idx] = ${checkboxValues[idx]}")
//        var cbvals = ""
//        checkboxValues.forEach { cbvals += it }
//        Log.i("CBVs-UDCB", "lunchiliciousViewModel.checkboxValues = $cbvals")
    }

    fun updateButtonText() {
        buttonText = if (buttonText == "Place Order") {
            "Continue Shopping"
        } else {
            "Place Order"
        }
    }
}

class MainActivity : ComponentActivity() {
    private val lunchiliciousViewModel = LunchiliciousViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lunchiliciousViewModel: LunchiliciousViewModel by viewModels()
        setContent {
            LunchiliciousTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(lunchiliciousViewModel)
                }
            }
        }
    }

    @Composable
    fun AppNavigator(lunchiliciousViewModel: LunchiliciousViewModel = LunchiliciousViewModel()) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = LunchiliciousScreen.OrderScreen
        ) {
            composable(LunchiliciousScreen.OrderScreen) {
                OrderScreen(
                    lunchiliciousViewModel = lunchiliciousViewModel,
                    navigateToConfirmationScreen = {
                        navController.navigate(LunchiliciousScreen.ConfirmationScreen)
                    }
                )
            }
            composable(LunchiliciousScreen.ConfirmationScreen) {
                ConfirmationScreen(
                    lunchiliciousViewModel = lunchiliciousViewModel,
                    navigateToConfirmationScreen = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
@Composable
fun OrderScreen (
    navigateToConfirmationScreen: () -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = LunchiliciousViewModel()
) {
    val state = rememberLazyListState()
    var cbvals = ""
    lunchiliciousViewModel.checkboxValues.forEach { cbvals += it }
    Log.i("CBVs-ORDR", "lunchiliciousViewModel.checkboxValues = $cbvals")

    LazyColumn (
        state = state,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 0.dp,
                top = 0.dp,
                end = 0.dp,
                bottom = 72.dp
            )
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
                checked = lunchiliciousViewModel.checkboxValues[0],
                onCheckedChange = {
                    lunchiliciousViewModel.updateCheckbox(0)
                    Log.i("CBVs-UDST", "lunchiliciousViewModel.cbState = ${lunchiliciousViewModel.checkboxValues[0]}")
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
                checked = lunchiliciousViewModel.checkboxValues[1],
                onCheckedChange = {
                    lunchiliciousViewModel.updateCheckbox(1)
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
                checked = lunchiliciousViewModel.checkboxValues[2],
                onCheckedChange = {
                    lunchiliciousViewModel.updateCheckbox(2)
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
                checked = lunchiliciousViewModel.checkboxValues[3],
                onCheckedChange = {
                    lunchiliciousViewModel.updateCheckbox(3)
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
                checked = lunchiliciousViewModel.checkboxValues[4],
                onCheckedChange = {
                    lunchiliciousViewModel.updateCheckbox(4)
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
                checked = lunchiliciousViewModel.checkboxValues[5],
                onCheckedChange = {
                    lunchiliciousViewModel.updateCheckbox(5)
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
                checked = lunchiliciousViewModel.checkboxValues[6],
                onCheckedChange = {
                    lunchiliciousViewModel.updateCheckbox(6)
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
                checked = lunchiliciousViewModel.checkboxValues[7],
                onCheckedChange = {
                    lunchiliciousViewModel.updateCheckbox(7)
                }
            )
        }
        item {
            StatelessMenuItem(
                id = 9,
                type = "Hoagie",
                name = "Beer-Battered Cod Sandwich",
                description = "Cod and tartar sauce on Brioche Bun",
                unitPrice = 8.95
            )
            Checkbox(
                checked = lunchiliciousViewModel.checkboxValues[8],
                onCheckedChange = {
                    lunchiliciousViewModel.updateCheckbox(8)
                }
            )
        }
        item {
            StatelessMenuItem(
                id = 10,
                type = "Pizza",
                name = "Margherita Pizza",
                description = "Fresh tomatoes, basil and mozzarella on neopolitan crust",
                unitPrice = 14.95
            )
            Checkbox(
                checked = lunchiliciousViewModel.checkboxValues[9],
                onCheckedChange = {
                    lunchiliciousViewModel.updateCheckbox(9)
                }
            )
        }
    }
    Row (
        verticalAlignment = Alignment.Bottom
    ){
        ElevatedButton(
            enabled = true,
            shape = CircleShape,
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            onClick = {
                navigateToConfirmationScreen()
                lunchiliciousViewModel.updateButtonText()
            }
        ) {
            Text(text = lunchiliciousViewModel.buttonText)
        }
    }
}

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

@Composable
fun ConfirmationScreen (
    navigateToConfirmationScreen: () -> Unit,
    lunchiliciousViewModel: LunchiliciousViewModel = LunchiliciousViewModel(),
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 0.dp,
                top = 0.dp,
                end = 0.dp,
                bottom = 72.dp
            )
    ){
        Text(text = "Order Summary")
        if (lunchiliciousViewModel.checkboxValues[0]) {
            Row {
                Text(text = "BLT Hoagie x 1")
            }
        }
    }
    Row (
        verticalAlignment = Alignment.Bottom
    ){
        ElevatedButton(
            enabled = true,
            shape = CircleShape,
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            onClick = {
                navigateToConfirmationScreen()
                lunchiliciousViewModel.updateButtonText()
            }
        ) {
            Text(text = lunchiliciousViewModel.buttonText)
        }
    }
}
