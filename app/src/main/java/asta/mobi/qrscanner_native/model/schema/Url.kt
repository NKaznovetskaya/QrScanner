package asta.mobi.qrscanner_native.model.schema

class Url(
    private val url: String? = null
): Schema {

    companion object{
        private const val HTTP_PREFIX = "http://"
    }

    override fun toBarcodeText(): String = url.toString()
}