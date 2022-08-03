package asta.mobi.qrscanner_native.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import asta.mobi.qrscanner_native.R
import asta.mobi.qrscanner_native.core.BaseBindingActivity
import asta.mobi.qrscanner_native.databinding.ActivityMainBinding

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = Navigation.findNavController(this, R.id.main_fragment_container)

    }

    fun navigate(destination: NavDirections) {
        navController.currentDestination?.getAction(destination.actionId)?.let { action ->
            navController.navigate(action.destinationId, destination.arguments)
        }
    }
}