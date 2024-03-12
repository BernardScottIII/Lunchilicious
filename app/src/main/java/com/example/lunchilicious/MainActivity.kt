package com.example.lunchilicious

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunchilicious.ui.theme.LunchiliciousTheme

data class MenuItem(
    val id: Int,
    val type: String,
    val name: String,
    val description: String,
    val price: Double
)

class Repository {
    private val items: Array<MenuItem> = arrayOf(
        MenuItem(1, "Hoagie", "BLT Hoagie", "Cold, Onion, lettuce, tomato", 6.95),
        MenuItem(2, "Hoagie", "Cheese Hoagie", "Cheese, mayos, lettuce, tomato", 6.95),
        MenuItem(3, "Pizza", "Plain Pizza", "cheese and tomato", 9.50),
        MenuItem(4, "Side", "Fries", "large hot fries", 2.95),
        MenuItem(5, "Side", "Gravy Fries", "Fries with gravy on top", 3.95),
        MenuItem(6, "Entree", "Raspberry Chicken", "Fried chicken topped with raspberry sauce and pineapple salsa", 10.00),
        MenuItem(7, "Entree", "Dragon & Phoenix", "Shrimp w. garlic sauce & chunk chicken fried in spicy sauce", 12.00),
        MenuItem(8, "Burrito", "Everymeat Burrito", "Chicken, beef, pork, lobster, shrimp, fish, duck, lamb, turkey, bison, cornish game hen, goose, pheasant, qual, rabbit, squab, venison, boar, alligator, antelope, caribou, elk, ostrich, turtle, rattlesnake, and kangaroo", 49.95),
        MenuItem(9, "Hoagie", "Beer-Battered Cod Sandwich", "Fried cod filet and tartar sauce on brioche bun", 8.95),
        MenuItem(10, "Pizza", "Margherita Pizza", "Fresh tomatoes, basil and mozzarella on neopolitan crust", 14.95)
    )

    fun getItems(): Array<MenuItem> { return items }
}

class LunchiliciousViewModel: ViewModel() {

    private var _checkboxValueList = mutableStateListOf(false, false, false, false, false, false, false, false, false, false)
    val checkboxValueList = _checkboxValueList

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

class MainActivity : ComponentActivity() {
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

    object LunchiliciousScreen {
        const val OrderScreen = "OrderScreen"
        const val ConfirmationScreen = "ConfirmationScreen"
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
    val menu = Repository().getItems()

    LazyColumn (
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 0.dp,
                top = 0.dp,
                end = 0.dp,
                bottom = 72.dp
            )
    ) {
        menu.forEach { menuItem ->
            item {
                StatelessMenuItem(
                    id = menuItem.id,
                    type = menuItem.type,
                    name = menuItem.name,
                    description = menuItem.description,
                    unitPrice = menuItem.price
                )
                Checkbox(
                    checked = lunchiliciousViewModel.checkboxValueList[menuItem.id-1],
                    onCheckedChange = {
                        lunchiliciousViewModel.checkboxValueList[menuItem.id-1] = !lunchiliciousViewModel.checkboxValueList[menuItem.id-1]
                    }
                )
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

@Composable
fun StatelessMenuItem(
    id: Int,
    type: String,
    name: String,
    description: String,
    unitPrice: Double
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
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = LunchiliciousViewModel()
) {
    val menu = Repository().getItems()
    var orderTotal = 0.0

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
        menu.forEach { menuItem ->
            if (lunchiliciousViewModel.checkboxValueList[menuItem.id-1]) {
                Text(text = "${menuItem.name} x 1")
                orderTotal += menuItem.price
            }
        }
        Row (
            verticalAlignment = Alignment.Bottom
        ){
            Text(text = "Order Total: $orderTotal")
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
