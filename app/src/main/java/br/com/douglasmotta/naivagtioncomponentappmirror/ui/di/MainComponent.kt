package br.com.douglasmotta.naivagtioncomponentappmirror.ui.di

import br.com.douglasmotta.naivagtioncomponentappmirror.MainActivity
import br.com.douglasmotta.naivagtioncomponentappmirror.di.MainScope
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.login.LoginFragment
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.profile.ProfileFragment
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.registration.choosecredentials.ChooseCredentialsFragment
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.registration.profiledata.ProfileDataFragment
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.start.StartFragment
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: ProfileDataFragment)
    fun inject(fragment: ChooseCredentialsFragment)
}