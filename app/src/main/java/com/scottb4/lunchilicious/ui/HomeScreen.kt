package com.scottb4.lunchilicious.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.scottb4.lunchilicious.R

@Composable
fun HomeScreen(
    lunchiliciousUiState: LunchiliciousUiState,
    navigateToConfirmationScreen: () -> Unit,
    navigateToNewItemScreen: () -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory),
) {
    when (lunchiliciousUiState) {
        is LunchiliciousUiState.Success -> OrderScreen(
            navigateToConfirmationScreen = navigateToConfirmationScreen,
            navigateToNewItemScreen = navigateToNewItemScreen,
            lunchiliciousViewModel = lunchiliciousViewModel,
            menu = lunchiliciousUiState.menuItems
        )
        is LunchiliciousUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
        is LunchiliciousUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}