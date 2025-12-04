package com.example.coopdemo3.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coopdemo3.model.ScannedItem
import com.example.coopdemo3.viewmodels.ScannerViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: ScannerViewModel,
    onScanQr: () -> Unit,
    onScanText: () -> Unit
) {
    val items = viewModel.items
    val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Scanner Demo") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            Row(Modifier.fillMaxWidth()) {
                Button(onClick = onScanQr, modifier = Modifier.weight(1f)) { Text("Scan QR Code") }
                Spacer(Modifier.width(8.dp))
                Button(onClick = onScanText, modifier = Modifier.weight(1f)) { Text("Scan Text") }
            }

            Spacer(Modifier.height(16.dp))

            Text("Scanned Items:")

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items) { it: ScannedItem ->
                    Column(modifier = Modifier.padding(vertical = 12.dp)) {
                        Text(text = "Type: ${it.type}")
                        Text(text = "Result: ${it.content}")
                        Text(text = "Time: ${fmt.format(Date(it.timestamp))}")
                    }
                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                }

                if (items.isNotEmpty()) {
                    item {
                        Button(
                            onClick = { viewModel.clear() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        ) {
                            Text("Clear List")
                        }
                    }
                }
            }
        }
    }
}