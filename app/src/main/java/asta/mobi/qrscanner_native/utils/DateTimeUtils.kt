package asta.mobi.qrscanner_native.utils

import android.app.DatePickerDialog
import android.content.Context
import java.util.*

object DateTimeUtils {

    fun showDatePiker(context: Context, dateCallback: ((Long, String) -> (Unit))) {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(context, { _, mYear, mMonth, mDayOfMonth ->
                val calendar = GregorianCalendar(mYear, mMonth, mDayOfMonth)
                dateCallback(calendar.timeInMillis, "$mDayOfMonth/$mMonth/$mYear")
            }, year, month, day)
        datePickerDialog.show()
    }
}