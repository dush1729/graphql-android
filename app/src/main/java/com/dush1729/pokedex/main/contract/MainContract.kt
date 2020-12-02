package com.dush1729.pokedex.main.contract

import com.dush1729.commons.BasePresenter
import com.dush1729.commons.BaseState
import com.dush1729.commons.BaseView

interface IMainView : BaseView

sealed class MainState : BaseState

interface IMainPresenter : BasePresenter<IMainView, MainState>