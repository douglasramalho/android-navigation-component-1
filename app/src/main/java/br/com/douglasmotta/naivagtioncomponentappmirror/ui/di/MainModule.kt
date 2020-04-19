package br.com.douglasmotta.naivagtioncomponentappmirror.ui.di

import androidx.lifecycle.ViewModel
import br.com.douglasmotta.naivagtioncomponentappmirror.di.MainScope
import br.com.douglasmotta.naivagtioncomponentappmirror.di.ViewModelKey
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.login.LoginViewModel
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel
}