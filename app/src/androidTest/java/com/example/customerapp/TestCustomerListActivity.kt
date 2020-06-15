package com.example.customerapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.customerapp.presentation.view.ui.customerlist.CustomerListActivity
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.RootMatchers.withDecorView as withDecorView1


@RunWith(AndroidJUnit4::class)
@LargeTest
class TestCustomerListActivity {
    @get:Rule
    val activityRule = ActivityTestRule(CustomerListActivity::class.java)

    @Test
    fun testCustomersIn150KM(){
        onView(withId(R.id.spinnerCustomer)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.spinnerCustomer)).check(matches(withSpinnerText(containsString("100"))))
        onView(withText("Filter")).perform(click())
        onView(withId(R.id.recyclerViewCustomer))
            .check(matches(hasDescendant(withText("Eoin Ahearn"))))
        onView(withId(R.id.recyclerViewCustomer)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withText("Invite Sent")).inRoot(withDecorView1(not(activityRule.activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testCustomersIn200KM(){
        onView(withId(R.id.spinnerCustomer)).perform(click())
        onData(anything()).atPosition(2).perform(click())
        onView(withId(R.id.spinnerCustomer)).check(matches(withSpinnerText(containsString("200"))))
        onView(withText("Filter")).perform(click())
        onView(withId(R.id.recyclerViewCustomer))
            .check(matches(hasDescendant(withText("Bob Larkin"))))
        onView(withId(R.id.recyclerViewCustomer)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withText("Invite Sent")).inRoot(withDecorView1(not(activityRule.activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkIfAlphabeticallySorted(){
        onView(withId(R.id.recyclerViewCustomer)).check(matches(CustomerMatchers().isSortedAlphabetically(R.id.txtTitle)))
    }

}