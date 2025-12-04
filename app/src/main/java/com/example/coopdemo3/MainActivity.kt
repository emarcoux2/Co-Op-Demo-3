package com.example.coopdemo3

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coopdemo3.navigation.AppNav
import com.example.coopdemo3.ui.theme.CoopDemo3Theme
import com.example.coopdemo3.viewmodels.ScannerViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // registerForActivityResult is an Android Jetpack API that lets us perform an action
        // that returns a result
        val permissionLauncher = registerForActivityResult(
            // handles showing permission dialog to the user
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            // MainActivity doesn't do anything here;
            // the UI composables will react based on permission state
        }
        // calls the permission launcher we just registered
        // and passes the specific permission to request from the Manifest
        permissionLauncher.launch(Manifest.permission.CAMERA)

        enableEdgeToEdge()
        setContent {
            val scannerViewModel: ScannerViewModel = viewModel()
            CoopDemo3Theme {
                AppNav(scannerViewModel = scannerViewModel)
            }
        }
    }
}

// sources used to create this project:

// https://medium.com/@harmanpreet.khera/building-text-recognition-apps-with-ml-kit-and-jetpack-compose-fda4b730c865
// https://proandroiddev.com/building-smart-android-apps-mastering-googles-ml-kit-for-barcode-and-text-recognition-4c7e23ede96f
// https://hackernoon.com/building-letterlens-an-ocr-powered-android-app-with-kotlin-ml-kit-and-ktor
// https://developer.android.com/jetpack/androidx/releases/camera
// https://medium.com/androiddevelopers/getting-started-with-camerax-in-jetpack-compose-781c722ca0c4
// https://medium.com/@appdevinsights/build-a-text-recognition-app-using-ml-kit-and-jetpack-compose-39d09a74e0db
// https://medium.com/@appdevinsights/build-a-modern-qr-code-scanner-app-in-android-with-jetpack-compose-camerax-ml-kit-068ba40f6fcf
// https://proandroiddev.com/integrating-google-ml-kit-for-barcode-scanning-in-jetpack-compose-android-apps-5deda28377c9

