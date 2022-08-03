package asta.mobi.qrscanner_native.core

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import asta.mobi.qrscanner_native.R

abstract class BaseBindingActivity<Binding : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: Binding

    private var toast: Toast? = null
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.colorWhite
                )
            )
        )
    }

    fun showMessage(@StringRes messageId: Int?) {
        messageId?.let { showMessage(getString(messageId)) }
    }

    fun showMessage(message: String?) {
        if (message.isNullOrBlank()) {
            return
        }
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast?.show()
    }

    fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
        this.observe(owner, Observer {
            it?.let(observer)
        })
    }
}