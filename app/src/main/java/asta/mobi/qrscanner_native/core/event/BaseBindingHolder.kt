package asta.mobi.qrscanner_native.core.event

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseBindingHolder <B : ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)