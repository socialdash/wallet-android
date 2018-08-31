package com.storiqa.storiqawallet.login_screen

import com.storiqa.storiqawallet.constants.JsonConstants
import com.storiqa.storiqawallet.network.StoriqaApi
import com.storiqa.storiqawallet.network.network_requests.GetStoriqaTokenFromFirebaseTokenRequest
import com.storiqa.storiqawallet.network.network_requests.GetTokenByEmailRequest
import com.storiqa.storiqawallet.network.network_responses.ErrorInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray

class LoginModelImp : LoginModel {

    override fun signInWithEmailAndPassword(email: String, password: String, success: () -> Unit, failure: (errors : List<ErrorInfo>) -> Unit) {
        StoriqaApi.Factory().getInstance().getTokenByEmailAndPassword(GetTokenByEmailRequest(email, password))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.errors != null) {
                        failure(it.errors!!)
                    } else {
                        success()
                    }
                }, {
                    failure(arrayListOf())
                })
    }

    override fun getErrors(jsonArray: JSONArray) : String {
        var result = ""
        for(i in 0 until jsonArray.length()) {
            result += jsonArray.getJSONObject(i).getString(JsonConstants().message) + "\n"
        }
        return result
    }

    override fun getStoriqaToken(userToken: String, provider: String, success: (storiqaToken: String) -> Unit, failure: () -> Unit) {
        StoriqaApi.Factory().getInstance().getStoriqaTokenFromFirebaseToken(GetStoriqaTokenFromFirebaseTokenRequest(userToken, provider))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.errors != null) {
                        failure()
                    }
                    if(it.data != null) {
                        success(it.data.getJWTByProvider.token)
                    }
                }, {
                    failure()
                })
    }
}