package asta.mobi.qrscanner_native.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QrCode(
    val imgResId: Int,
    val nameResId: Int,
    val type: QrCodeType
): Parcelable
