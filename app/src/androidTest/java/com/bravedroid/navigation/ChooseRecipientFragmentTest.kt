package com.bravedroid.navigation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito
import org.mockito.Mockito.verify

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

        onView(withId(R.id.next_btn_choose_recipient)).perform(click())


        verify(mockNavController).navigate(
//            we can always use argsThat
//            argThat<NavDirections> {
//                it.arguments.getString("name") == "Jim"
//            },
//            we can use it because it is in kotlin as it is a data class, so equals is overridden
//            we have used eq() because we needed a matchers later, we cannot mix matcher with real args https://stackoverflow.com/questions/24468456/invalid-use-of-argument-matchers/24468549
            eq(
                ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment(
                    "Jim"
                )
            ),
//            other matcher (but useless)
//            any<NavDirections>(),
            //            we can always use argsThat
            argThat<NavOptions> {
                it.enterAnim == (R.anim.slide_in_from_right_to_left)
                        && it.exitAnim == (R.anim.slide_out_from_right_to_left)
                        && it.popEnterAnim == (R.anim.slide_in_from_left_to_right)
                        && it.popExitAnim == (R.anim.slide_out_from_left_to_right)
            }
//        we cannot use it because NavOptions is writen in java and has no equals overriden
//            NavOptions.Builder()
//                .setEnterAnim(R.anim.slide_in_from_right_to_left)
//                .setExitAnim(R.anim.slide_out_from_right_to_left)
//                .setPopEnterAnim(R.anim.slide_in_from_left_to_right)
//                .setPopExitAnim(R.anim.slide_out_from_left_to_right).build()
        )
    }
}
