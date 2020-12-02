package com.dush1729.pokedex.main.injection

import com.dush1729.pokedex.main.activity.MainActivity
import com.dush1729.pokedex.main.injection.module.MainModule
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}