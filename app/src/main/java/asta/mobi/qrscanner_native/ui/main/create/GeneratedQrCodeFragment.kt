package asta.mobi.qrscanner_native.ui.main.create

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import asta.mobi.qrscanner_native.core.BaseFragment
import asta.mobi.qrscanner_native.databinding.FragmentGeneratedQrCodeBinding
import asta.mobi.qrscanner_native.model.QrCodeType
import asta.mobi.qrscanner_native.model.schema.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter


class GeneratedQrCodeFragment : BaseFragment<FragmentGeneratedQrCodeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGeneratedQrCodeBinding
        get() = FragmentGeneratedQrCodeBinding::inflate

    private val qrCodeInfo by lazy { GeneratedQrCodeFragmentArgs.fromBundle(requireArguments()).qrCodeInfo }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
        val btm = QRCodeWriter().encode(
            makeQrCodeSchema().toBarcodeText(),
            BarcodeFormat.QR_CODE, 400, 400)

        try {
            val height: Int = btm.height
            val width: Int = btm.width
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (btm.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            binding.generatedQrCodeIv.setImageBitmap(bmp)
        } catch(e: WriterException){
            e.printStackTrace()
        }

    }

    private fun makeQrCodeSchema(): Schema {
        return when(qrCodeInfo.qrCode.type) {
            QrCodeType.EMAIL -> Email(qrCodeInfo.email!!, qrCodeInfo.emailSubject!!, qrCodeInfo.message!!)
            QrCodeType.CALENDAR_EVENT -> Event(
                description = qrCodeInfo.description, organizer = qrCodeInfo.title,
                location = qrCodeInfo.location, startDate = qrCodeInfo.beginTime,
                endDate = qrCodeInfo.endTime)
            QrCodeType.SMS -> Sms(phone = qrCodeInfo.phoneNumber, message = qrCodeInfo.message)
            QrCodeType.GEO -> Geo(latitude = qrCodeInfo.lat!!, longitude = qrCodeInfo.lnt!!, query = qrCodeInfo.query)
            QrCodeType.CONTACT_INFO -> Contact(fullName = qrCodeInfo.fullName, address = qrCodeInfo.address,
                phone = qrCodeInfo.phoneNumber, email = qrCodeInfo.email)
            QrCodeType.PHONE -> PhoneNumber(phone = qrCodeInfo.phoneNumber!!)
            QrCodeType.TEXT -> Text(text = qrCodeInfo.message)
            QrCodeType.WI_FI -> Wifi(ssid = qrCodeInfo.ssid, password = qrCodeInfo.password, wifiType = qrCodeInfo.wifiType!!)
            QrCodeType.URL -> Url(url = qrCodeInfo.url)
        }
    }

}