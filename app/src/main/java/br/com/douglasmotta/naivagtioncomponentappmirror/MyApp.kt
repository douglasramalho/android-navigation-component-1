package br.com.douglasmotta.naivagtioncomponentappmirror

import android.app.Application
import br.com.douglasmotta.naivagtioncomponentappmirror.di.ApplicationComponent
import br.com.douglasmotta.naivagtioncomponentappmirror.di.DaggerApplicationComponent

class MyApp : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.factory().create(this)
    }
}