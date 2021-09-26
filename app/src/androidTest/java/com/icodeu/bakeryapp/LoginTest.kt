package com.icodeu.bakeryapp


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf

import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class LoginTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun successfulyLogin(){


        val textInputEditText4 = onView(
            allOf(
                withId(R.id.edtEmail),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.emailOutline),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText4.perform(replaceText("rohman@gmail.com"))


        val textInputEditText6 = onView(
            allOf(
                withId(R.id.edtPassword),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.passwordoutline),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText6.perform(replaceText("password"))

        val materialButton2 = onView(
            allOf(
                withId(R.id.btnLogin), withText("Login"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    4
                )
            )
        )
        materialButton2.perform(click())
    }
    @Test
    fun errorLoginBecauseInvalidCredentials() {
        val textInputEditText = onView(
            allOf(
                withId(R.id.edtEmail),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.emailOutline),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText.perform(replaceText("rohman@mail.com"))

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.edtPassword),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.passwordoutline),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText2.perform(replaceText("invalidpsswd"))


        val materialButton = onView(
            allOf(
                withId(R.id.btnLogin),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    4
                )
            )
        )
        materialButton.perform(click())

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
