package asta.mobi.qrscanner_native.utils

import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


object PermissionsUtils {

    private fun singlePermission(fragment: Fragment, permission: String, isGranted: (() -> (Unit))) {
        Dexter.withContext(fragment.context)
            .withPermission(permission)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) { isGranted() }

                override fun onPermissionDenied(response: PermissionDeniedResponse) { }

                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) { }
            }).check()
    }

    fun checkCameraPermission(fragment: Fragment, isGranted: (() -> (Unit))) {
        singlePermission(fragment, android.Manifest.permission.CAMERA, isGranted)
    }

}