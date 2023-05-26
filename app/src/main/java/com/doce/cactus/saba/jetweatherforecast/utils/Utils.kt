package com.doce.cactus.saba.jetweatherforecast.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(timestamp: Long): String{
    val sdf = SimpleDateFormat("EEE, MMM, d", Locale.getDefault())
    val date = Date(timestamp * 1000)
    return sdf.format(date)
}

fun formatDateTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("hh:mm:aa", Locale.getDefault())
    val date = Date(timestamp * 1000)
    return sdf.format(date)
}

fun formatDecimals(item: Double): String {
    return " %.0f".format(item)
}