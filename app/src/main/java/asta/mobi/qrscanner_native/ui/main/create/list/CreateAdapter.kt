package asta.mobi.qrscanner_native.ui.main.create.list

import asta.mobi.qrscanner_native.R
import asta.mobi.qrscanner_native.core.event.BaseAdapter
import asta.mobi.qrscanner_native.core.event.BaseBindingHolder
import asta.mobi.qrscanner_native.databinding.ItemQrCodeTypeBinding
import asta.mobi.qrscanner_native.model.QrCode
import asta.mobi.qrscanner_native.ui.main.create.CreateDiffCallBack


class CreateAdapter( onClick: (QrCode) -> Unit): BaseAdapter<QrCode, ItemQrCodeTypeBinding>(onClick, CreateDiffCallBack) {
    override val layoutId: Int = R.layout.item_qr_code_type

    override fun onBindViewHolder(holder: BaseBindingHolder<ItemQrCodeTypeBinding>, position: Int) {
        super.onBindViewHolder(holder, position)

        val item = getItem(holder.adapterPosition)
        holder.binding.typeQrTv.text = holder.binding.root.context.getString(item.nameResId)
        holder.binding.iconType.setImageResource(item.imgResId)




    }
}