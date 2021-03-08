package com.openbank.data.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun getCurrentTimestamp(): String? {
        return SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.US).format(
            Calendar
                .getInstance().time
        )
    }
}