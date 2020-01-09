package com.bravedroid.navigation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class ChooseRecipientFragmentTest {

    @Before
    fun setUp() {

    }

    @Test
    fun test_navigation_from_ChooseRecipientFragment_to_SpecifyAmountFragment() {
        val mockNavController = Mockito.mock(NavController::class.java)
        val chooseRecipientFragmentScenario =
            launchFragmentInContainer<ChooseRecipientFragment>(themeResId = R.style.AppTheme)

        chooseRecipientFragmentScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        onView(withId(R.id.input_recipient)).perform(typeText("Jim"))

        onView(withId(R.id.next_btn)).perform(click())

        Mockito.verify(mockNavController).navigate(
            ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment("Jim")
        )
    }
}
