package com.icodeu.bakeryapp


import android.view.View
import android.view.ViewGroup
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest

import org.hamcrest.Description
import org.hamcrest.Matcher

import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class LoginTest {
/*
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loginTest() {
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
        textInputEditText.perform(scrollTo(), replaceText("rohman@mail.com"), closeSoftKeyboard())

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
        textInputEditText2.perform(scrollTo(), replaceText("invalidpsswd"), closeSoftKeyboard())

        pressBack()

        val materialButton = onView(
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
        materialButton.perform(scrollTo(), click())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.edtEmail), withText("rohman@mail.com"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.emailOutline),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText3.perform(scrollTo(), click())

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.edtEmail), withText("rohman@mail.com"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.emailOutline),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText4.perform(scrollTo(), replaceText("rohman@gmail.com"))

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.edtEmail), withText("rohman@gmail.com"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.emailOutline),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(closeSoftKeyboard())

        val textInputEditText6 = onView(
            allOf(
                withId(R.id.edtPassword), withText("invalidpsswd"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.passwordoutline),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText6.perform(scrollTo(), replaceText("password"))

        val textInputEditText7 = onView(
            allOf(
                withId(R.id.edtPassword), withText("password"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.passwordoutline),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText7.perform(closeSoftKeyboard())

        pressBack()

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
        materialButton2.perform(scrollTo(), click())*/
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
//}
