package com.hsinha610.daznassignmenths

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hsinha610.daznassignmenths.data.models.DataList
import com.hsinha610.daznassignmenths.data.models.DataListItem
import com.hsinha610.daznassignmenths.ui.Screens.HomeScreen
import com.hsinha610.daznassignmenths.ui.base.CustomAppTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        // Start the app
        val list = mutableListOf<DataListItem>()
        list.add(
            DataListItem(
                title = "Title",
                url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
            )
        )
        composeTestRule.setContent {
            CustomAppTheme {
                HomeScreen(dataList = DataList(list),null)
            }
        }

        composeTestRule.onNodeWithText("Title").assertIsDisplayed()

        composeTestRule.onNodeWithText("Title").assertHasClickAction()

        composeTestRule.onNodeWithText("Title").performClick()

    }
}