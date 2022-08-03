package asta.mobi.qrscanner_native.ui.main.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import asta.mobi.qrscanner_native.core.BaseFragment
import asta.mobi.qrscanner_native.databinding.FragmentSettingsBinding

class SettingsFragment: BaseFragment<FragmentSettingsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding
        get() = FragmentSettingsBinding::inflate

}