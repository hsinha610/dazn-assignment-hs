package com.hsinha610.daznassignmenths.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy

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