package asta.mobi.qrscanner_native.ui.main.qrscaner

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import asta.mobi.qrscanner_native.R
import asta.mobi.qrscanner_native.core.BaseFragment
import asta.mobi.qrscanner_native.databinding.FragmentQrScannerBinding
import asta.mobi.qrscanner_native.ui.main.MainActivity
import asta.mobi.qrscanner_native.utils.PermissionsUtils
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.io.IOException

class QrScannerFragment : BaseFragment<FragmentQrScannerBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQrScannerBinding
        get() = FragmentQrScannerBinding::inflate

    private lateinit var cameraSource: CameraSource
    private lateinit var barcodeDetector: BarcodeDetector

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PermissionsUtils.checkCameraPermission(this) {
            setupControls()
        }

        context?.let { ctx ->
            val aniSlide: Animation =
                AnimationUtils.loadAnimation(ctx, R.anim.scanner_animation)
            binding.barcodeLine.startAnimation(aniSlide)
        }

        binding.qrCodeInfoView.root.isVisible = false

        binding.qrCodeInfoView.cancelIv.setOnClickListener {
            cameraSource.start(binding.cameraSurfaceView.holder)
            binding.qrCodeInfoView.root.isVisible = false
        }
    }



    private fun setupControls() {
        context?.let { ctx ->
            activity?.let { activity ->
                barcodeDetector = BarcodeDetector.Builder(ctx)
                    .setBarcodeFormats(Barcode.ALL_FORMATS).build()

                cameraSource = CameraSource.Builder(ctx, barcodeDetector)
                    .setRequestedPreviewSize(1920, 1080)
                    .setAutoFocusEnabled(true)
                    .build()

                binding.cameraSurfaceView.holder.addCallback(object : SurfaceHolder.Callback {
                    @SuppressLint("MissingPermission")
                    override fun surfaceCreated(holder: SurfaceHolder) {
                        try {
                            //Start preview after 1s delay
                            cameraSource.start(holder)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }

                    @SuppressLint("MissingPermission")
                    override fun surfaceChanged(
                        holder: SurfaceHolder,
                        format: Int,
                        width: Int,
                        height: Int
                    ) {
                        try {
                            cameraSource.start(holder)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }

                    override fun surfaceDestroyed(holder: SurfaceHolder) {
                        cameraSource.stop()
                    }
                })


                barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
                    override fun release() {
                        Toast.makeText(ctx, "Scanner has been closed", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                        val barcodes = detections.detectedItems
                        if (barcodes.size() == 1) {
                            val barcode = barcodes.valueAt(0)
                            activity.runOnUiThread {
                                cameraSource.stop()
                                setQrCodeInfo(barcode)
                            }
                        } else {
                            Toast.makeText(ctx, "value- else", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }

    }

    override fun onDestroyView() {
        cameraSource.stop()
        super.onDestroyView()
    }

    fun setQrCodeInfo(barcode: Barcode) {
        with(binding.qrCodeInfoView) {
            root.isVisible = true
            val codeTypeResId = when (barcode.format) {
                Barcode.CODE_128, Barcode.CODE_39, Barcode.CODE_93, Barcode.CODABAR,
                Barcode.ITF, Barcode.EAN_8, Barcode.EAN_13, Barcode.UPC_A,
                Barcode.UPC_E  -> R.string.barcode
                Barcode.DATA_MATRIX, Barcode.QR_CODE, Barcode.PDF417, Barcode.AZTEC-> R.string.qr_code
                else -> R.string.code
            }
            val codeType = getString(codeTypeResId)
            formatTypeTv.text = codeType

            typeTv.text = barcode.valueFormat.toString()
            qrValueTv.text = barcode.rawValue
        }
    }
}