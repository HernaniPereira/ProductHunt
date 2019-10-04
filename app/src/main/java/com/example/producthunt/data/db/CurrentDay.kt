package com.example.producthunt.data.db

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object CurrentDay {
    @RequiresApi(Build.VERSION_CODES.O)
    fun currentDay(): String {
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        return formatted.toString()
    }
}