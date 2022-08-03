package asta.mobi.qrscanner_native.ui.main.history

import android.view.LayoutInflater
import android.view.ViewGroup
import asta.mobi.qrscanner_native.core.BaseFragment
import asta.mobi.qrscanner_native.databinding.FragmentHistoryBinding

class HistoryFragment: BaseFragment<FragmentHistoryBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHistoryBinding
        get() = FragmentHistoryBinding::inflate

}