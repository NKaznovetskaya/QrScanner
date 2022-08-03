package asta.mobi.qrscanner_native.core.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter <T, Binding : ViewDataBinding>(
    var onClick: ((T) -> Unit)? = null,
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseBindingHolder<Binding>>(diffCallback) {

    protected abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingHolder<Binding> =
        BaseBindingHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        )


    override fun onBindViewHolder(holder: BaseBindingHolder<Binding>, position: Int) {
        onClick?.let {
            holder.binding.root.setOnClickListener {
                it(getItem(holder.adapterPosition))
            }
        }
    }
}