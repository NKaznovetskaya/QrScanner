package asta.mobi.qrscanner_native.model.schema

class Sms (
    val phone: String? = null,
    val message: String? = null
) : Schema {

    companion object {
        private const val PREFIX = "smsto:"
        private const val SEPARATOR = ":"
    }

    override fun toBarcodeText(): String {
        return PREFIX +
                phone.orEmpty() +
                "$SEPARATOR${message.orEmpty()}"
    }
}