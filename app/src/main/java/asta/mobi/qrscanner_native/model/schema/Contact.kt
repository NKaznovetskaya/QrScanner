package asta.mobi.qrscanner_native.model.schema

import ezvcard.Ezvcard
import ezvcard.VCard
import ezvcard.VCardVersion
import ezvcard.property.Email
import ezvcard.property.StructuredName
import ezvcard.property.Telephone


data class Contact(
    val fullName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val address: String? = null,
) : Schema {

    override fun toBarcodeText(): String {
        val vCard = VCard()

        vCard.structuredName = StructuredName().apply {
            given = fullName?.split(" ")?.get(0) ?: ""
            family = fullName?.split(" ")?.get(1) ?: ""
        }

        if (email.isNullOrBlank().not()) {
            vCard.addEmail(Email(email))
        }

        if (phone.isNullOrBlank().not()) {
            vCard.addTelephoneNumber(Telephone(phone))
        }


        return Ezvcard
            .write(vCard)
            .version(VCardVersion.V4_0)
            .prodId(false)
            .go()
            .trimEnd('\n', '\r', ' ')
    }
}