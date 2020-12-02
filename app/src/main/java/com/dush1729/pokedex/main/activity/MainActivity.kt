package com.dush1729.pokedex.main.activity

import android.os.Bundle
import com.dush1729.commons.BaseActivity
import com.dush1729.pokedex.R
import com.dush1729.pokedex.base.BaseApplication
import com.dush1729.pokedex.main.contract.IMainPresenter
import com.dush1729.pokedex.main.contract.IMainView
import com.dush1729.pokedex.main.contract.MainState
import com.dush1729.pokedex.main.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : BaseActivity<IMainView, MainState, IMainPresenter>(), IMainView {
    @Inject
    override lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BaseApplication.baseInstance?.getMainComponent()?.inject(this)
        presenter.attachView(this)
    }
}