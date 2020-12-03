package com.dush1729.pokedex.main.injection.module

import com.dush1729.pokedex.main.contract.IMainPresenter
import com.dush1729.pokedex.main.injection.MainScope
import com.dush1729.pokedex.main.presenter.MainPresenter
import com.dush1729.repository.main.IMainRepository
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    @MainScope
    fun provideMainPresenter(mainRepository: IMainRepository): IMainPresenter {
        return MainPresenter(mainRepository)
    }
}