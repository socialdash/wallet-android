package com.storiqa.storiqawallet.screen_splash

import com.arellomobile.mvp.MvpView

interface SplashView : MvpView {
    fun startShowButtonsAnimation()
    fun startResizeLogoAnimation()
    fun startLoginScreen()
    fun startMoveLogoUpAnimation()
    fun startRegisterScreen()
}