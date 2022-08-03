package asta.mobi.qrscanner_native.core

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import asta.mobi.qrscanner_native.utils.SingleLiveEvent

abstract class BaseFragment<B: ViewBinding> : Fragment() {

    abstract val bindingInflater: ((LayoutInflater, ViewGroup?, Boolean) -> (B))
    private var _binding: B? = null
    val binding: B
        get() {
            if (_binding == null) {
                throw NullPointerException("Binding not found!!!")
            }
            return _binding!!
        }

    private var toast: Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return _binding?.root
    }

    fun showMessage(@StringRes messageId: Int?) {
        messageId?.let { showMessage(getString(messageId)) }
    }

    fun showMessage(message: String?) {
        if (message.isNullOrBlank()) {
            return
        }
        toast?.cancel()
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast?.show()
    }


    fun hideKeyboard(activity: Activity?) {
        if (activity != null) {
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view = activity.currentFocus
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun hideKeyboardDialogs(activity: Activity?, view: View) {
        if (activity != null) {
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    inline operator fun SingleLiveEvent<Void>.invoke(crossinline block: () -> Unit) {
        this.observe(this@BaseFragment, Observer { block() })
    }

    inline fun <T> LiveData<T>.observeNotNull(crossinline action: (T) -> Unit) {
        this.observe(this@BaseFragment, Observer {
            if (it != null) action(it)
        })
    }

    inline fun <T> LiveData<T>.observe(crossinline action: (T?) -> Unit) {
        this.observe(this@BaseFragment, Observer {
            action(it)
        })
    }

    inline fun <T> LiveData<T>.observeWithContext(crossinline action: Context.(T) -> Unit) =
        this.observe(this@BaseFragment, Observer { value ->
            if (value != null) context?.action(value)
        })
}