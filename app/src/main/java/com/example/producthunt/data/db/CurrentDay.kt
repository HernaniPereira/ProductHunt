package com.example.producthunt.data.db

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object CurrentDay {
    fun currentDay(): String {

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           val current = LocalDate.now()

           val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
           val formatted = current.format(formatter)
           Log.e("data1:",formatted.toString() )
           return formatted.toString()
        } else {
           val pattern = "yyyy-MM-dd"
           val simpleDateFormat = SimpleDateFormat(pattern)
           val date = simpleDateFormat.format(Date())
           Log.e("data: ", date)
           return (date)
        }

    }
}