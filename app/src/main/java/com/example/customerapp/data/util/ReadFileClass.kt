package com.example.customerapp.data.util

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader

class ReadFileClass (private val context: Context){

    fun readFile(): List<String> {

        val jsonStringList: MutableList<String> = mutableListOf()
        try {
            val inputStream = context.assets.open("customer.txt")
            val inputStreamReader = InputStreamReader(inputStream)
            val sb = StringBuilder()
            var line: String?
            val br = BufferedReader(inputStreamReader).apply {
                line = readLine()
            }
            while (br.readLine() != null) {
                sb.run { append(line) }
                line = br.readLine()
                jsonStringList.run { add(line?:"") }
            }
            br.close()
            Log.d(TAG,sb.toString())
        } catch (e:Exception){
            Log.d(TAG, e.toString())
        }
        return jsonStringList
    }
}