package br.com.douglasmotta.naivagtioncomponentappmirror.di

import dagger.Module
import dagger.Provides

/**
 * Created by Douglas Motta on 08/05/2020
 */
@Module
class StringModule {

    @Provides
    fun provideString(): String {
        return "Hello World!"
    }
}