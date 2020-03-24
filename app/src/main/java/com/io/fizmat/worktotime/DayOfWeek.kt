package com.io.fizmat.worktotime

import java.util.*

object DayOfWeek {

    var day:Int = 0

    fun dayofWeek()
    {
        day = GregorianCalendar().get(Calendar.DAY_OF_WEEK)

        if (day == 1) day = 8
        day-=2
    }
}