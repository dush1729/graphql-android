package com.dush1729.pokedex.base.injection

import com.dush1729.commons.injection.PerActivity
import com.dush1729.pokedex.base.injection.module.AppModule
import com.dush1729.pokedex.main.injection.MainComponent
import com.dush1729.pokedex.main.injection.module.MainModule
import com.dush1729.repository.injection.RepositoryComponent
import dagger.Component

@PerActivity
@Component(
    modules = [AppModule::class],
    dependencies = [RepositoryComponent::class]
)

interface AppComponent {
    fun newMainComponent(mainModule: MainModule): MainComponent
}