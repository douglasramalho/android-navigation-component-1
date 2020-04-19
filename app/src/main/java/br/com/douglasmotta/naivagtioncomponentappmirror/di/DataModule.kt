package br.com.douglasmotta.naivagtioncomponentappmirror.di

import br.com.douglasmotta.naivagtioncomponentappmirror.data.DefaultRepository
import br.com.douglasmotta.naivagtioncomponentappmirror.data.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(repository: DefaultRepository): Repository
}