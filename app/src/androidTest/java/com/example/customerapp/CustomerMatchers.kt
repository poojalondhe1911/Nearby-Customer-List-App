package com.example.customerapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import java.util.*


open class CustomerMatchers {

     fun clickChildViewWith(id: Int): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController?, view: View) {
                val v: View = view.findViewById(id)
                v.performClick()
            }
        }
    }

     fun isSorted(elementResID: Int): Matcher<View?>? {
        return object : TypeSafeMatcher<View?>() {
            private val customerNames =
                ArrayList<Int>()

            override fun matchesSafely(item: View?): Boolean {
                val recyclerView: RecyclerView = item as RecyclerView
                val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder> = recyclerView.adapter
                        as RecyclerView.Adapter<RecyclerView.ViewHolder>
                adapter.itemCount
                customerNames.clear()
                for (i in 0 until adapter.itemCount) {
                    val view: View = recyclerView.getChildAt(i)
                    val idTextView = view.findViewById<TextView>(elementResID)
                    val id = idTextView.text.toString()
                    customerNames.add(id.toInt())
                }
                val initial = customerNames
                initial.sort()
                return initial == customerNames
            }

            override fun describeTo(description: Description) {
                description.appendText("has items sorted alphabetically: $customerNames")
            }
        }
    }


}