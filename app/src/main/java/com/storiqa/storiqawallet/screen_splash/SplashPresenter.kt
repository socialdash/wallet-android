package com.storiqa.storiqawallet.screen_splash

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class SplashPresenter : MvpPresenter<SplashView>() {
    val model = SplashModelImp()

    fun initScreen() {
        viewState.startShowButtonsAnimation()
        viewState.startResizeLogoAnimation()
        viewState.startMoveLogoUpAnimation()
    }

    fun onGetStartedButtonClicked() {
        viewState.startRegisterScreen()
    }

    fun onSignInButtonClicked() {
        viewState.startLoginScreen()
    }


}