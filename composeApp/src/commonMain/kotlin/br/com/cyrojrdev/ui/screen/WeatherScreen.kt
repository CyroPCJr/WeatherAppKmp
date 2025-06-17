package br.com.cyrojrdev.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.cyrojrdev.ui.screen.components.WeatherAppTopBar
import br.com.cyrojrdev.ui.theme.WeatherAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    WeatherAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { WeatherAppTopBar(scrollBehavior = scrollBehavior) },
            containerColor = MaterialTheme.colorScheme.background,
        ) { innerPadding ->
            // AppNavigationHost(contentPaddingValues = innerPadding)
        }
//        Scaffold(
//            modifier = Modifier.fillMaxSize(),
//            topBar = {
//                WeatherAppTopBar(
//                    titleRes = R.string.app_name,
//                    scrollBehavior = scrollBehavior,
//                )
//            },
//            bottomBar = {
//                BottomNavBar(
//                    selectedItemIndex = selectedTabIndex,
//                    onItemSelected = { selectedTabIndex = it },
//                    navController = navController,
//                )
//            },
//            containerColor = MaterialTheme.colorScheme.background,
//        ) { innerPadding ->
//            AppNavigationHost(
//                navController = navController,
//                onChangeIndexNavBarNavItem = { selectedTabIndex = it },
//                contentPaddingValues = innerPadding,
//            )
//        }
    }
}

@Preview
@Composable
fun WeatherScreenPreview() {
    WeatherScreen()
}
