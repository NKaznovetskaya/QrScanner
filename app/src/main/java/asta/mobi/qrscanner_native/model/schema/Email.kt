package asta.mobi.qrscanner_native.model.schema

import asta.mobi.qrscanner_native.utils.appendIfNotNullOrBlank

class Email(
    private val email: String,
    private val subject: String,
    private val body: String
) : Schema {

    companion object {
        private const val MATMSG_SCHEMA_PREFIX = "MATMSG:"
        private const val MATMSG_EMAIL_PREFIX = "TO:"
        private const val MATMSG_SUBJECT_PREFIX = "SUB:"
        private const val MATMSG_BODY_PREFIX = "BODY:"
        private const val MATMSG_SEPARATOR = ";"
    }

    override fun toBarcodeText(): String {
        return StringBuilder()
            .append(MATMSG_SCHEMA_PREFIX)
            .appendIfNotNullOrBlank(MATMSG_EMAIL_PREFIX, email, MATMSG_SEPARATOR)
            .appendIfNotNullOrBlank(MATMSG_SUBJECT_PREFIX, subject, MATMSG_SEPARATOR)
            .appendIfNotNullOrBlank(MATMSG_BODY_PREFIX, body, MATMSG_SEPARATOR)
            .append(MATMSG_SEPARATOR)
            .toString()
    }
}