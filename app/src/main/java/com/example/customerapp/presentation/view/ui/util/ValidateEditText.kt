package com.example.customerapp.presentation.view.ui.util

import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import java.util.regex.Matcher
import java.util.regex.Pattern


class ValidateEditText : InputFilter {
        override fun filter(
            source: CharSequence, start: Int, end: Int, dest: Spanned,
            dstart: Int, dend: Int
        ): CharSequence? {
            for (i in start until end) {
                val checkMe = source[i].toString()
                val pattern: Pattern =
                    Pattern.compile("^-?(?:\\d+|\\d{1,3}(?:,\\d{3})+)(?:(\\.|,)\\d+)?\$")
                val matcher: Matcher = pattern.matcher(checkMe)
                val valid: Boolean = matcher.matches()
                if (!valid) {
                    Log.d("", "invalid")
                    return ""
                }
            }
            return null
        }
}