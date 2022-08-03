package asta.mobi.qrscanner_native.ui.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import asta.mobi.qrscanner_native.R
import asta.mobi.qrscanner_native.core.BaseBindingActivity
import asta.mobi.qrscanner_native.databinding.ActivitySplashBinding
import asta.mobi.qrscanner_native.ui.main.MainActivity

class SplashActivity : BaseBindingActivity<ActivitySplashBinding>() {

    override val layoutId: Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()
        hideSystemUI()
    }

    private fun initObservers() {
        val run = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(run, 1000)
    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}