package asta.mobi.qrscanner_native.utils

import java.text.DateFormat
import java.util.*

fun DateFormat.formatOrNull(time: Long?): String? {
    return try {
        format(Date(time!!))
    } catch (ex: Exception) {
        null
    }
}