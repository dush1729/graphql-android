package com.dush1729.repository.injection

import com.dush1729.repository.main.IMainRepository
import com.dush1729.repository.main.injection.MainDataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class, MainDataModule::class]
)

interface RepositoryComponent {
    fun mainRepository(): IMainRepository
}