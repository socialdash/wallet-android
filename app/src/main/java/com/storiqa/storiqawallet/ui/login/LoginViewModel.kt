package com.storiqa.storiqawallet.ui.login

import android.annotation.SuppressLint
import android.databinding.ObservableField
import com.storiqa.storiqawallet.App
import com.storiqa.storiqawallet.R
import com.storiqa.storiqawallet.common.addOnPropertyChanged
import com.storiqa.storiqawallet.network.WalletApi
import com.storiqa.storiqawallet.network.common.RequestHeaders
import com.storiqa.storiqawallet.network.errors.ErrorCode
import com.storiqa.storiqawallet.network.errors.ErrorHandler
import com.storiqa.storiqawallet.network.requests.LoginRequest
import com.storiqa.storiqawallet.network.responses.TokenResponse
import com.storiqa.storiqawallet.ui.base.BaseViewModel
import com.storiqa.storiqawallet.utils.getDeviceId
import com.storiqa.storiqawallet.utils.getSign
import com.storiqa.storiqawallet.utils.getTimestamp
import com.storiqa.storiqawallet.utils.isEmailValid
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel
@Inject
constructor(navigator: ILoginNavigator,
            private val errorHandler: ErrorHandler,
            private val walletApi: WalletApi) : BaseViewModel<ILoginNavigator>() {

    val emailError = ObservableField<String>("")
    val passwordError = ObservableField<String>("")
    val email = ObservableField<String>("")
    val password = ObservableField<String>("")

    init {
        setNavigator(navigator)
        email.addOnPropertyChanged { emailError.set("") }
        password.addOnPropertyChanged { passwordError.set("") }
    }

    fun onSignInButtonClicked() {
        hideKeyboard()
        passwordError.set("")
        emailError.set("")
        if (isEmailValid(email.get()!!)) {
            requestLogIn()
            showLoadingDialog()
        } else
            emailError.set(App.getStringFromResources(R.string.error_email_not_valid))
    }

    fun onRegistrationButtonClicked() {
        getNavigator()?.openRegistrationActivity()
    }

    fun onPasswordRecoveryButtonClicked() {
        getNavigator()?.openPasswordRecoveryActivity()
    }

    @SuppressLint("CheckResult")
    private fun requestLogIn() {
        val timestamp = getTimestamp()

        val deviceId = getDeviceId()
        val deviceOs = "25"
        val sign = getSign(timestamp, deviceId)!!

        val requestHeaders = RequestHeaders(timestamp, deviceId, sign)
        val loginRequest = LoginRequest(email.get()!!, password.get()!!, deviceOs, deviceId)

        /*loginNetworkProvider.requestLogIn(requestHeaders, loginRequest,
                { onSuccess(it) }, { handleUnprocessableEntity(it) })*/
        val observableField: Observable<TokenResponse> =
                walletApi
                        .login(timestamp, deviceId, sign, loginRequest)


        observableField
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess(it)
                }, {
                    errorHandler.handleError(it as Exception, { handleUnprocessableEntity(it) })
                })

    }

    private fun onSuccess(token: TokenResponse?) {

        getNavigator()?.openMainActivity()//change
        hideLoadingDialog()
    }

    private fun handleUnprocessableEntity(error: ErrorCode) {
        when (error) {
            ErrorCode.EMAIL_NOT_VALID ->
                emailError.set(App.getStringFromResources(error.title))
            ErrorCode.EMAIL_NOT_EXIST ->
                emailError.set(App.getStringFromResources(error.title))
            ErrorCode.WRONG_PASSWORD ->
                passwordError.set(App.getStringFromResources(error.title))
            ErrorCode.SERVER_ERROR -> {//TODO show dialog
            }
            ErrorCode.DEVICE_NOT_ATTACHED -> {//TODO request for attach
            }
            ErrorCode.NO_INTERNET -> {//TODO show dialog
            }
            ErrorCode.UNKNOWN_ERROR -> {//TODO show dialog
            }
        }
        hideLoadingDialog()
    }

}