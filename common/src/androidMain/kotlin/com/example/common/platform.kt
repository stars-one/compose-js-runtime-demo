package com.example.common

import androidx.compose.runtime.Composable

public actual fun getPlatformName(): String {
    return "android"
}

@Composable
public fun UIShow() {
    App()
}