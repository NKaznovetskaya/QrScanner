package asta.mobi.qrscanner_native.ui.main.create.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import asta.mobi.qrscanner_native.R
import asta.mobi.qrscanner_native.core.BaseFragment
import asta.mobi.qrscanner_native.databinding.FragmentCreateQrBinding
import asta.mobi.qrscanner_native.model.*
import asta.mobi.qrscanner_native.ui.main.MainActivity
import asta.mobi.qrscanner_native.ui.main.MainNavFragmentDirections

class CreateCodeListFragment: BaseFragment<FragmentCreateQrBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreateQrBinding
        get() = FragmentCreateQrBinding::inflate

    private val createAdapter = CreateAdapter { qrCodeItem ->
        (requireActivity() as MainActivity).navigate(MainNavFragmentDirections
            .actionCreateQrFragmentToCreateQrCodeFragment2(qrCodeItem))
    }

    private val createBarcodeAdapter = CreateBarcodeAdapter { barcodeItem ->
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.qrTypeRv.adapter = createAdapter
        createAdapter.submitList(createQrCodeItem())

        binding.barcodeTypeRv.adapter = createBarcodeAdapter
        createBarcodeAdapter.submitList(createBarcodeItem())

    }

    private fun createQrCodeItem() = listOf(QrCode(R.drawable.ic_email,R.string.email, QrCodeType.EMAIL),
        QrCode(R.drawable.ic_sms, R.string.sms,QrCodeType.SMS),
        QrCode(R.drawable.ic_location,R.string.geo,QrCodeType.GEO),
        QrCode(R.drawable.ic_event,R.string.calendar_event,QrCodeType.CALENDAR_EVENT),
        QrCode(R.drawable.ic_contacts,R.string.contact_info,QrCodeType.CONTACT_INFO),
        QrCode(R.drawable.ic_phone,R.string.phone,QrCodeType.PHONE),
        QrCode(R.drawable.ic_text,R.string.text,QrCodeType.TEXT),
        QrCode(R.drawable.ic_wifi,R.string.wi_fi,QrCodeType.WI_FI),
        QrCode(R.drawable.ic_browser,R.string.url,QrCodeType.URL)
    )

    private fun createBarcodeItem() = listOf(Barcode(R.drawable.ic_aztec, R.string.aztec,  BarcodeType.AZTEC),
        Barcode(R.drawable.ic_bars_code, R.string.codabar,  BarcodeType.CODABAR),
        Barcode(R.drawable.ic_bars_code, R.string.code_39,  BarcodeType.CODE_39),
        Barcode(R.drawable.ic_bars_code, R.string.code_93,  BarcodeType.CODE_93),
        Barcode(R.drawable.ic_bars_code, R.string.code_128,  BarcodeType.CODE_128),
        Barcode(R.drawable.ic_data_matrix, R.string.data_matrix,  BarcodeType.DATA_MATRIX),
        Barcode(R.drawable.ic_bars_code, R.string.ean_8,  BarcodeType.EAN_8),
        Barcode(R.drawable.ic_bars_code, R.string.ean_13,  BarcodeType.EAN_13),
        Barcode(R.drawable.ic_bars_code, R.string.itf,  BarcodeType.ITF),
        Barcode(R.drawable.ic_pdf, R.string.pdf_417,  BarcodeType.PDF_417),
        Barcode(R.drawable.ic_bars_code, R.string.upc_a,  BarcodeType.UPC_A),
        Barcode(R.drawable.ic_bars_code, R.string.upc_e,  BarcodeType.UPC_E),

    )

}