package br.com.douglasmotta.naivagtioncomponentappmirror.data

import android.content.Context
import android.util.Log
import javax.inject.Inject

class DefaultRepository @Inject constructor(val context: Context) : Repository {

    init {
        Log.d("DefaultRepository", context.toString())
    }

    override fun doLogin() {
        Log.d("DefaultRepository", "Login done")
    }
}