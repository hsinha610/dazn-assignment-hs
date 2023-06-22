package com.hsinha610.daznassignmenths

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.hsinha610.daznassignmenths.data.models.DataList
import com.hsinha610.daznassignmenths.data.models.DataListItem
import com.hsinha610.daznassignmenths.ui.screens.DetailScreen
import com.hsinha610.daznassignmenths.ui.base.CustomAppTheme
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        // Start the app

        composeTestRule.setContent {
            CustomAppTheme {
                DetailScreen(dataList = DataList(getDummyDataList()), 1)
            }
        }

        composeTestRule.onNodeWithText("Title2").assertIsDisplayed()

        composeTestRule.onNodeWithText("Explanation2").assertIsDisplayed()

        composeTestRule.onNodeWithText("2019/12/12").assertIsDisplayed()

    }

    private fun getDummyDataList(): List<DataListItem> {
        val list = mutableListOf<DataListItem>()
        list.add(
            DataListItem(
                title = "Title1",
                url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
                explanation = "Explanation1",
                date = "2019/11/12"
            )
        )
        list.add(
            DataListItem(
                title = "Title2",
                url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
                explanation = "Explanation2",
                date = "2019/12/12"
            )
        )
        list.add(
            DataListItem(
                title = "Title3",
                url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
                explanation = "Explanation3",
                date = "2019/10/12"
            )
        )
        return list
    }

}