package asta.mobi.qrscanner_native.ui.main.create.list

import asta.mobi.qrscanner_native.R
import asta.mobi.qrscanner_native.core.event.BaseAdapter
import asta.mobi.qrscanner_native.core.event.BaseBindingHolder
import asta.mobi.qrscanner_native.databinding.ItemBarcodeTypeBinding
import asta.mobi.qrscanner_native.model.Barcode
import asta.mobi.qrscanner_native.ui.main.create.CreateBarcodeGiffCallBack

class CreateBarcodeAdapter ( onClick: (Barcode) -> Unit): BaseAdapter<Barcode, ItemBarcodeTypeBinding>(onClick, CreateBarcodeGiffCallBack) {
    override val layoutId: Int = R.layout.item_barcode_type

    override fun onBindViewHolder(holder: BaseBindingHolder<ItemBarcodeTypeBinding>, position: Int) {
        super.onBindViewHolder(holder, position)

        val item = getItem(holder.adapterPosition)
        holder.binding.typeBarcodeTv.text = holder.binding.root.context.getString(item.nameResId)
        holder.binding.iconTypeBarcodeIv.setImageResource(item.imgResId)




    }
}