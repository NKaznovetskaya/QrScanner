package asta.mobi.qrscanner_native.ui.main.create

import androidx.recyclerview.widget.DiffUtil
import asta.mobi.qrscanner_native.model.QrCode

val CreateDiffCallBack = object : DiffUtil.ItemCallback<QrCode>() {
    override fun areItemsTheSame(oldItem: QrCode, newItem: QrCode): Boolean {
        return oldItem.type == newItem.type
    }

    override fun areContentsTheSame(oldItem: QrCode, newItem: QrCode): Boolean {
        return oldItem == newItem
    }
}