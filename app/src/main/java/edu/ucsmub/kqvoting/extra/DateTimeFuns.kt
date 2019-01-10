package edu.ucsmub.kqvoting.extra

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun dateToMilliSec(date: String, pattern: String): Long {
    val sdf = SimpleDateFormat(pattern)
    val date1 = sdf.parse(date)
    return date1.time
}

fun dateToString(date: Date, pattern: String): String{
    val sdf = SimpleDateFormat(pattern)
    return sdf.format(date)
}

@SuppressLint("SimpleDateFormat")
fun milliToDate(millisecond: Long, pattern: String): String {
    val sdf = SimpleDateFormat(pattern)
    return sdf.format(Date(millisecond))
}