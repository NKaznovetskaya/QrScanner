package asta.mobi.qrscanner_native.ui.main.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import asta.mobi.qrscanner_native.core.BaseFragment
import asta.mobi.qrscanner_native.databinding.FragmentFavoritesBinding

class FavoritesFragment: BaseFragment<FragmentFavoritesBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoritesBinding
        get() = FragmentFavoritesBinding::inflate

}