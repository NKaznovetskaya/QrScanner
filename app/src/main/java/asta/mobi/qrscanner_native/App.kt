package asta.mobi.qrscanner_native

import android.app.Application
import asta.mobi.qrscanner_native.di.prefModule
import asta.mobi.qrscanner_native.di.repositoryModule
import asta.mobi.qrscanner_native.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    prefModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}