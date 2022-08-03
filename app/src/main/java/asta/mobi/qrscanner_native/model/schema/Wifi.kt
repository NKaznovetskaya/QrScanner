package asta.mobi.qrscanner_native.model.schema

import asta.mobi.qrscanner_native.model.WifiType
import asta.mobi.qrscanner_native.utils.appendIfNotNullOrBlank

class Wifi(
    private val ssid: String? = null,
    private val password: String? = null,
    private val wifiType: WifiType
): Schema {

    companion object{
        private const val SCHEMA_PREFIX = "WIFI:"
        private const val ENCRYPTION_PREFIX = "T:"
        private const val NAME_PREFIX = "S:"
        private const val PASSWORD_PREFIX = "P:"
        private const val SEPARATOR = ";"
    }

    override fun toBarcodeText(): String {
        val encryption = when (wifiType) {
            WifiType.WPA_WPA2 -> "WPA"
            WifiType.WEP -> "WEP"
            WifiType.NONE -> "nopass"
        }

        return StringBuilder()
            .append(SCHEMA_PREFIX)
            .appendIfNotNullOrBlank(ENCRYPTION_PREFIX, encryption, SEPARATOR)
            .appendIfNotNullOrBlank(NAME_PREFIX, ssid, SEPARATOR)
            .appendIfNotNullOrBlank(PASSWORD_PREFIX, password, SEPARATOR)
            .append(SEPARATOR)
            .toString()
    }
}