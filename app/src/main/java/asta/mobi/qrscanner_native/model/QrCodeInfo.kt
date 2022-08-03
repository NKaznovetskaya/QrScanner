package asta.mobi.qrscanner_native.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QrCodeInfo(
    val email: String? = null,
    val emailSubject: String? = null,
    val message: String? = null,
    val phoneNumber: String? = null,
    val lat: String? = null,
    val lnt: String? = null,
    val query: String? = null,

    val title: String? = null,
    val location: String? = null,
    val description: String? = null,
    val beginTime: Long? = null,
    val endTime: Long? = null,

    val fullName: String? = null,
    val address: String? = null,

    val ssid: String? = null,
    val password: String? = null,
    val wifiType: WifiType? = null,

    val url: String? = null,

    val qrCode: QrCode
): Parcelable
