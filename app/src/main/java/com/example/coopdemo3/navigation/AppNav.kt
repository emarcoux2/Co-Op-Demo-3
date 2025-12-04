package com.example.coopdemo3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coopdemo3.ui.home.HomeScreen
import com.example.coopdemo3.ui.scanner.ScannerScreen
import com.example.coopdemo3.viewmodels.ScannerViewModel

@Composable
fun AppNav(scannerViewModel: ScannerViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                viewModel = scannerViewModel,
                onScanQr = { navController.navigate("scanner/qr") },
                onScanText = { navController.navigate("scanner/text") }
            )
        }

        // backStackEntry is the object containing the route state
        // and any arguments passed to this destination
        composable("scanner/{mode}") { backStackEntry ->
            val mode = backStackEntry.arguments?.getString("mode") ?: "qr"
            ScannerScreen(
                // decides which scanning mode to start in
                initialMode = if (mode == "qr") ScanMode.QR else ScanMode.TEXT,
                onScanned = { type, value ->
                    // saves the scanned item to a list
                    scannerViewModel.addItem(type, value)

                    // navigates back to the previous screen by
                    // "popping the destination off the back stack"
                    navController.popBackStack()
                },
                // the user cancels scanning
                onCancel = { navController.popBackStack() }
            )
        }
    }
}