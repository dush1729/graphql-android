package com.dush1729.repository

import android.app.Application
import com.dush1729.repository.injection.DaggerRepositoryComponent
import com.dush1729.repository.injection.RepositoryComponent
import com.dush1729.repository.injection.RepositoryModule

class RepositoryLibrary {
    fun initialize(application: Application) {
        repositoryModule = RepositoryModule(application)
        getRepositoriesComponent()
    }

    companion object {
        private var repositoryModule: RepositoryModule? = null
        private var repositoryComponent: RepositoryComponent? = null
        val instance: RepositoryLibrary by lazy {
            RepositoryLibrary()
        }

        fun getRepositoriesComponent(): RepositoryComponent? {
            if (repositoryComponent == null) {
                repositoryComponent = DaggerRepositoryComponent.builder().repositoryModule(
                    repositoryModule
                ).build()
            }
            return repositoryComponent
        }
    }
}