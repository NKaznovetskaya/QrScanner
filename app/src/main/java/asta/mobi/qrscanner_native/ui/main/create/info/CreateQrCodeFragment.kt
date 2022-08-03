package asta.mobi.qrscanner_native.ui.main.create.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import asta.mobi.qrscanner_native.R
import asta.mobi.qrscanner_native.core.BaseFragment
import asta.mobi.qrscanner_native.databinding.FragmentCreateQrCodeBinding
import asta.mobi.qrscanner_native.model.QrCodeInfo
import asta.mobi.qrscanner_native.model.QrCodeType
import asta.mobi.qrscanner_native.model.WifiType
import asta.mobi.qrscanner_native.ui.main.MainActivity
import asta.mobi.qrscanner_native.utils.DateTimeUtils

class CreateQrCodeFragment: BaseFragment<FragmentCreateQrCodeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreateQrCodeBinding
        get() = FragmentCreateQrCodeBinding::inflate

    private val qrCode by lazy { CreateQrCodeFragmentArgs.fromBundle(requireArguments()).qrCode }
    private var wifiType: WifiType? = WifiType.WPA_WPA2
    private var beginTime: Long? = null
    private var endTime: Long? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.beginTimeEventTiEt.setOnClickListener {
            DateTimeUtils.showDatePiker(requireContext()) { timestapm, timeStr ->
                binding.beginTimeEventTiEt.setText(timeStr)
                beginTime = timestapm
            }
        }
        binding.endTimeEventTiEt.setOnClickListener {
            DateTimeUtils.showDatePiker(requireContext()) { timestapm, timeStr ->
                binding.endTimeEventTiEt.setText(timeStr)
                endTime = timestapm
            }
        }

        binding.doneBtn.setOnClickListener {
            val qrCodeInfo = makeQrCodeInfo()
            (requireActivity() as MainActivity).navigate(CreateQrCodeFragmentDirections
                .actionCreateQrCodeFragment2ToGeneratedQrCodeFragment(qrCodeInfo))

        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            wifiType = when(id) {
                binding.radioWpa.id -> WifiType.WPA_WPA2
                binding.radioWep.id -> WifiType.WEP
                binding.radioNone.id -> WifiType.NONE
                else -> null
            }
        }


        fieldsVisibility()
    }

    private fun fieldsVisibility() {
        when (qrCode.type) {
            QrCodeType.SMS -> {
                binding.emailTil.isVisible = true
                binding.emailTiEt.isVisible = true
                binding.typeMassageEt.isVisible = true
                binding.iconIv.setImageResource(R.drawable.ic_sms)
                binding.titleTv.setText(R.string.sms)
                binding.iconIv.isVisible = true
                binding.titleTv.isVisible = true
            }
            QrCodeType.EMAIL -> {
                binding.emailTil.isVisible = true
                binding.emailTiEt.isVisible = true
                binding.iconIv.isVisible = true
                binding.titleTv.isVisible = true
                binding.iconIv.setImageResource(R.drawable.ic_email)
                binding.titleTv.setText(R.string.email)
                binding.subjectTil.isVisible = true
                binding.subjectTiEt.isVisible = true
                binding.typeMassageEt.isVisible = true
            }
            QrCodeType.GEO -> {
                binding.iconIv.setImageResource(R.drawable.ic_location)
                binding.titleTv.setText(R.string.geo)
                binding.iconIv.isVisible = true
                binding.titleTv.isVisible = true
                binding.latitudeTil.isVisible = true
                binding.latitudeTiEt.isVisible = true
                binding.longitudeTiEt.isVisible = true
                binding.longitudeTil.isVisible = true
                binding.queryTiEt.isVisible = true
                binding.queryTil.isVisible = true
            }
            QrCodeType.CALENDAR_EVENT -> {
                binding.iconIv.setImageResource(R.drawable.ic_event)
                binding.titleTv.setText(R.string.calendar_event)
                binding.iconIv.isVisible = true
                binding.titleTv.isVisible = true
                binding.titleEventTil.isVisible = true
                binding.titleEventTiEt.isVisible = true
                binding.locationEventTiEt.isVisible = true
                binding.locationEventTil.isVisible = true
                binding.descriptionEventTiEt.isVisible = true
                binding.descriptionEventTil.isVisible = true
                binding.beginTimeEventTiEt.isVisible = true
                binding.beginTimeEventTil.isVisible = true
                binding.endTimeEventTiEt.isVisible = true
                binding.endTimeEventTil.isVisible = true
            }
            QrCodeType.CONTACT_INFO -> {
                binding.iconIv.setImageResource(R.drawable.ic_contacts)
                binding.titleTv.setText(R.string.contact_info)
                binding.iconIv.isVisible = true
                binding.titleTv.isVisible = true
                binding.fullNameTiEt.isVisible = true
                binding.fullNameTil.isVisible = true
                binding.addressTiEt.isVisible = true
                binding.addressTil.isVisible = true
                binding.phoneContactTiEt.isVisible = true
                binding.phoneContactTil.isVisible = true
                binding.emailContactTiEt.isVisible = true
                binding.emailContactTil.isVisible = true
                binding.importContactBtn.isVisible = true
            }
            QrCodeType.PHONE -> {
                binding.iconIv.setImageResource(R.drawable.ic_phone)
                binding.titleTv.setText(R.string.phone)
                binding.iconIv.isVisible = true
                binding.titleTv.isVisible = true
                binding.phoneNumberCv.isVisible = true
                binding.phoneNumberEt.isVisible = true
                binding.importPhoneNumberBtn.isVisible = true
            }
            QrCodeType.TEXT -> {
                binding.iconIv.setImageResource(R.drawable.ic_text)
                binding.titleTv.setText(R.string.text)
                binding.iconIv.isVisible = true
                binding.titleTv.isVisible = true
                binding.textCv.isVisible = true
                binding.textEt.isVisible = true
            }
            QrCodeType.WI_FI -> {
                binding.iconIv.setImageResource(R.drawable.ic_wifi)
                binding.titleTv.setText(R.string.wi_fi)
                binding.iconIv.isVisible = true
                binding.titleTv.isVisible = true
                binding.ssidTiEt.isVisible = true
                binding.ssidTil.isVisible = true
                binding.passwordTiEt.isVisible = true
                binding.passwordTil.isVisible = true
                binding.radioGroup.isVisible = true
                binding.radioWpa.isVisible = true
                binding.radioWep.isVisible = true
                binding.radioNone.isVisible = true
                binding.wifiCl.isVisible = true

            }
            QrCodeType.URL -> {
                binding.iconIv.setImageResource(R.drawable.ic_browser)
                binding.titleTv.setText(R.string.url)
                binding.iconIv.isVisible = true
                binding.titleTv.isVisible = true
                binding.urlTiEt.isVisible = true
                binding.urlTil.isVisible = true
            }
        }
    }
    private fun makeQrCodeInfo(): QrCodeInfo {
        return when(qrCode.type) {
            QrCodeType.EMAIL -> {
                QrCodeInfo(
                    email = binding.emailTiEt.text.toString(),
                    emailSubject = binding.subjectTiEt.text.toString(),
                    message = binding.typeMassageEt.text.toString(),
                    qrCode = qrCode
                )
            }
            QrCodeType.SMS -> {
                QrCodeInfo(
                    phoneNumber = binding.emailTiEt.text.toString(),
                    message = binding.typeMassageEt.text.toString(),
                    qrCode = qrCode
                )
            }
            QrCodeType.GEO -> {
                QrCodeInfo(
                    lnt = binding.longitudeTiEt.text.toString(),
                    lat = binding.latitudeTiEt.text.toString(),
                    query = binding.queryTiEt.text.toString(),
                    qrCode = qrCode
                )
            }
            QrCodeType.CALENDAR_EVENT -> {
                QrCodeInfo(
                    title = binding.titleEventTiEt.text.toString(),
                    location = binding.locationEventTiEt.text.toString(),
                    description = binding.descriptionEventTiEt.text.toString(),
                    beginTime = beginTime,
                    endTime = endTime,
                    qrCode = qrCode
                )
            }
            QrCodeType.CONTACT_INFO -> {
                QrCodeInfo(
                    fullName = binding.fullNameTiEt.text.toString(),
                    address = binding.addressTiEt.text.toString(),
                    phoneNumber = binding.phoneContactTiEt.text.toString(),
                    email = binding.emailContactTiEt.text.toString(),
                    qrCode = qrCode
                )
            }
            QrCodeType.PHONE -> {
                QrCodeInfo(
                    phoneNumber = binding.phoneNumberEt.text.toString(),
                    qrCode = qrCode
                )

            }
            QrCodeType.TEXT -> {
                QrCodeInfo(
                    message = binding.textEt.text.toString(),
                    qrCode = qrCode
                )

            }
            QrCodeType.WI_FI -> {
                QrCodeInfo(
                    ssid = binding.ssidTiEt.text.toString(),
                    password = binding.passwordTiEt.text.toString(),
                    wifiType = wifiType,
                    qrCode = qrCode
                )

            }
            QrCodeType.URL -> {
                QrCodeInfo(
                    url = binding.urlTiEt.text.toString(),
                    qrCode = qrCode,
                )

            }
        }
    }

}