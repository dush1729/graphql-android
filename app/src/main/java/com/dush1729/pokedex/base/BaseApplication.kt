package com.dush1729.pokedex.base

import android.app.Application
import com.dush1729.pokedex.base.injection.AppComponent
import com.dush1729.pokedex.base.injection.DaggerAppComponent
import com.dush1729.pokedex.main.injection.MainComponent
import com.dush1729.pokedex.main.injection.module.MainModule
import com.dush1729.repository.RepositoryLibrary

class BaseApplication : Application() {
    private var appComponent: AppComponent? = null
    private var mainComponent: MainComponent? = null

    companion object {
        var baseInstance: BaseApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        baseInstance = this
        RepositoryLibrary.instance.initialize(this)
    }

    private fun getAppComponent(): AppComponent? {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                .repositoryComponent(RepositoryLibrary.getRepositoriesComponent()).build()
        }
        return appComponent
    }

    fun getMainComponent(): MainComponent? {
        if (mainComponent == null) {
            mainComponent = getAppComponent()?.newMainComponent(MainModule())
        }
        return mainComponent
    }
}