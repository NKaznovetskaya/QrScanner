package asta.mobi.qrscanner_native.model.schema

import asta.mobi.qrscanner_native.utils.appendIfNotNullOrBlank
import asta.mobi.qrscanner_native.utils.formatOrNull
import java.text.SimpleDateFormat
import java.util.*

class Event (
    private val organizer: String? = null,
    private val description: String? = null,
    private val location: String? = null,
    private val startDate: Long? = null,
    private val endDate: Long? = null,
) : Schema {

    companion object {
        private const val SCHEMA_PREFIX = "BEGIN:VEVENT"
        private const val SCHEMA_SUFFIX = "END:VEVENT"
        private const val PARAMETERS_SEPARATOR = "\n"
        private const val ORGANIZER_PREFIX = "ORGANIZER:"
        private const val DESCRIPTION_PREFIX = "DESCRIPTION:"
        private const val LOCATION_PREFIX = "LOCATION:"
        private const val START_PREFIX = "DTSTART:"
        private const val END_PREFIX = "DTEND:"

        private val BARCODE_TEXT_DATE_FORMATTER by lazy {
            SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'", Locale.US).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
        }

        private val FORMATTED_TEXT_DATE_FORMATTER by lazy {
            SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH)
        }

    }


    override fun toBarcodeText(): String {
        val startDate = BARCODE_TEXT_DATE_FORMATTER.formatOrNull(startDate)
        val endDate = BARCODE_TEXT_DATE_FORMATTER.formatOrNull(endDate)

        return StringBuilder()
            .append(SCHEMA_PREFIX)
            .append(PARAMETERS_SEPARATOR)
            .appendIfNotNullOrBlank(ORGANIZER_PREFIX, organizer, PARAMETERS_SEPARATOR)
            .appendIfNotNullOrBlank(DESCRIPTION_PREFIX, description, PARAMETERS_SEPARATOR)
            .appendIfNotNullOrBlank(START_PREFIX, startDate, PARAMETERS_SEPARATOR)
            .appendIfNotNullOrBlank(END_PREFIX, endDate, PARAMETERS_SEPARATOR)
            .appendIfNotNullOrBlank(LOCATION_PREFIX, location, PARAMETERS_SEPARATOR)
            .append(SCHEMA_SUFFIX)
            .toString()
    }
}