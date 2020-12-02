package com.dush1729.repository.injection

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)

interface RepositoryComponent