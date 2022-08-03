package asta.mobi.qrscanner_native.model.schema

class PhoneNumber(private val phone: String) : Schema {

    companion object {
        private const val PREFIX = "tel:"

    }

    override fun toBarcodeText(): String = "$PREFIX$phone"
}