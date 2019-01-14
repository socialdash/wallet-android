package com.storiqa.storiqawallet.data.repository

import android.annotation.SuppressLint
import com.storiqa.storiqawallet.data.IAppDataStorage
import com.storiqa.storiqawallet.data.db.dao.UserDao
import com.storiqa.storiqawallet.data.db.entity.User
import com.storiqa.storiqawallet.network.WalletApi
import com.storiqa.storiqawallet.utils.SignUtil
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class UserRepository(private val userDao: UserDao,
                     private val walletApi: WalletApi,
                     private val appDataStorage: IAppDataStorage,
                     private val signUtil: SignUtil) : IUserRepository {

    override fun getUser(email: String): Flowable<User> {
        return userDao.loadUserByEmail(email).subscribeOn(Schedulers.io()).distinct()
    }

    @SuppressLint("CheckResult")
    override fun updateUser(errorHandler: (Exception) -> Unit) {
        val token = appDataStorage.token

        val email = appDataStorage.currentUserEmail
        val signHeader = signUtil.createSignHeader(email)

        val userInfo = walletApi
                .getUserInfo(signHeader.timestamp, signHeader.deviceId,
                        signHeader.signature, "Bearer $token")

        userInfo.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ userDao.insert(User(it)) }, { errorHandler(it as Exception) })
    }

}