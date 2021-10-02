package uz.texnopos.myapplicationdebtbooks

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import uz.texnopos.myapplicationdebtbooks.di.dataModule
import uz.texnopos.myapplicationdebtbooks.di.helperModule
import uz.texnopos.myapplicationdebtbooks.di.viewModelModule

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        val modules = listOf(dataModule, helperModule, viewModelModule)
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            koin.loadModules(modules)
        }
    }

}