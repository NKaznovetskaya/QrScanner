package asta.mobi.qrscanner_native.core

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import asta.mobi.digitalcleaningdev.core.event.Event
import asta.mobi.qrscanner_native.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    protected val disposables: CompositeDisposable = CompositeDisposable()
    val messageTextData = MutableLiveData<String?>()
    val messageResData = SingleLiveEvent<Int?>()
    val openLogin = MutableLiveData<Void>()
    val keyboard = MutableLiveData<Void?>()
    val progress = MutableLiveData<Event<EventProgress>>()
    private var initProgress = true

    override fun onCleared() {
        disposables.clear()
    }

    fun deactivateProgress() {
        initProgress = false
    }

    fun activateProgress() {
        initProgress = true
    }

    fun showProgress() {
        if (initProgress) progress.postValue(Event(EventProgress.SHOW))
    }

    fun hideProgress() {
        if (initProgress) progress.postValue(Event(EventProgress.HIDE))
    }

    fun showMessage(message: String?) {
        messageTextData.value = message
    }

    fun showMessage(@StringRes messageId: Int) {
        messageResData.value = messageId
    }

    fun hideKeyboard() {
        keyboard.postValue(null)
    }

//    fun Throwable.handleResponseErrors() {
//        val message: String?
//        when {
//            this is SocketTimeoutException -> {
//                showMessage(R.string.slow_internet)
//                openLogin.postValue(null)
//                return
//            }
//            this is UnknownHostException -> {
//                showMessage(R.string.no_internet_connection)
//                return
//            }
//
//            else -> message = this.message?.capitalize()
//        }
//        Timber.e(this)
//        showMessage(message)
//    }

    protected infix operator fun CompositeDisposable.plus(d: Disposable) = this.add(d)


//    fun List<Error>?.handleServerErrors() {
//        this?.forEach {
//            when (it.id) {
//                //TODO add all error messages
//                else -> showMessage(it.message)
//            }
//        }
//    }

    enum class EventProgress {
        SHOW,
        HIDE
    }
}