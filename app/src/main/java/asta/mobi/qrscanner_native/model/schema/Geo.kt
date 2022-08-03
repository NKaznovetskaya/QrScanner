package asta.mobi.qrscanner_native.model.schema

class Geo : Schema {

    companion object {
        private const val PREFIX = "geo:"
        private const val SEPARATOR = ","
    }

    private val uri: String

    private constructor(uri: String) {
        this.uri = uri
    }

    constructor(latitude: String, longitude: String, query: String? = null) {
        uri = if (query.isNullOrEmpty()) {
            "$PREFIX$latitude$SEPARATOR$longitude"
        } else {
            "$PREFIX$latitude$SEPARATOR$longitude$SEPARATOR$query"
        }
    }

    override fun toBarcodeText(): String = uri
}