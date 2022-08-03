package asta.mobi.qrscanner_native.ui.main.create

import androidx.recyclerview.widget.DiffUtil
import asta.mobi.qrscanner_native.model.Barcode
import asta.mobi.qrscanner_native.model.QrCode

val CreateBarcodeGiffCallBack = object : DiffUtil.ItemCallback<Barcode>() {
    override fun areItemsTheSame(oldItem: Barcode, newItem: Barcode): Boolean {
        return oldItem.type == newItem.type
    }

    override fun areContentsTheSame(oldItem: Barcode, newItem: Barcode): Boolean {
        return oldItem == newItem
    }
}