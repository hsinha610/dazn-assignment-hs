package com.hsinha610.daznassignmenths.ui

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp

fun FragmentComposeView(context: Context, Ui: @Composable () -> Unit): ComposeView {

    return ComposeView(context).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            CustomAppTheme {
                Ui()
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.size(40.dp).align(Alignment.Center))
    }
}

@Composable
fun ErrorScreen(error: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(error, modifier = Modifier.align(Alignment.Center))
    }
}