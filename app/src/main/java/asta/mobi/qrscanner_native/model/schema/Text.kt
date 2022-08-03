package asta.mobi.qrscanner_native.model.schema

class Text(private val text: String? = null): Schema {

    override fun toBarcodeText(): String {
        return text ?: ""
    }
}