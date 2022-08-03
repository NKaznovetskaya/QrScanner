package asta.mobi.qrscanner_native.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import asta.mobi.qrscanner_native.R
import asta.mobi.qrscanner_native.core.BaseFragment
import asta.mobi.qrscanner_native.databinding.FragmentMainNavBinding
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.flow.count
import timber.log.Timber

class MainNavFragment: BaseFragment<FragmentMainNavBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainNavBinding
        get() = FragmentMainNavBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navBottomHostFragment = childFragmentManager.findFragmentById(R.id.nav_fragment_container) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navBottomHostFragment.navController)
        binding.bottomNavigationView.selectedItemId = R.id.placeholder

        activity?.let {
            binding.fab.setOnClickListener { _ ->
                if (binding.bottomNavigationView.selectedItemId != R.id.placeholder) {
                    binding.bottomNavigationView.selectedItemId = R.id.placeholder
                    it.onBackPressed()
                }
            }
        }
    }
}