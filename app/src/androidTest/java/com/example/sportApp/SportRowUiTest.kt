package com.example.sportApp

import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.sportApp.ui.components.sportRow.SportRow
import okhttp3.internal.wait
import org.junit.Rule
import org.junit.Test

class SportRowUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTestArrowUp() {
        composeTestRule.setContent {
            SportRow(
                sportName = "Tennis",
                showFavourites = false,
                isOpenArrow = true,
                setFavourite = {},
                arrowButtonAction = { newState -> }
            )
        }
        composeTestRule.onNodeWithTag("arrow_up",useUnmergedTree = true).assertExists().assertIsDisplayed()
    }

    @Test
    fun myTestArrowDown() {
        composeTestRule.setContent {
            SportRow(
                sportName = "Tennis",
                showFavourites = false,
                isOpenArrow = false,
                setFavourite = {},
                arrowButtonAction = { newState -> }
            )
        }
        composeTestRule.onNodeWithTag("arrow_down",useUnmergedTree = true).assertExists().assertIsDisplayed()
    }
}