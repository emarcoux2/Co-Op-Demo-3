package com.example.coopdemo3.model

enum class ScanType {
    QR,
    TEXT
}

data class ScannedItem(
    // the type of scan
    // must be one of the types defined in the enum
    val type: ScanType,

    // holds text that was scanned
    val content: String,

    // stores when the scan happened
    // sets the current time in milliseconds by default
    val timestamp: Long = System.currentTimeMillis()
)