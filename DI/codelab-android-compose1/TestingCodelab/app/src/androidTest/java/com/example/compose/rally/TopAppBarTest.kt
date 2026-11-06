package com.example.compose.rally

import androidx.compose.material.Text
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(RallyActivity::class.java)

    @Test
    fun myTest() {
        composeTestRule.setContent {
            Text("You can set any Compose content!")
        }
    }

    @Test
    fun rallyTopAppBarTest() {
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = RallyScreen.entries,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }
        composeTestRule
            .onNodeWithText(RallyScreen.Accounts.name.uppercase(), useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.entries
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertExists()
    }
}
