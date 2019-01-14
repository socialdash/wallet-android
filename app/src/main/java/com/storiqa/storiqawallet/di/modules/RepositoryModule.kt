package com.storiqa.storiqawallet.di.modules

import com.storiqa.storiqawallet.data.IAppDataStorage
import com.storiqa.storiqawallet.data.db.dao.AccountDao
import com.storiqa.storiqawallet.data.db.dao.UserDao
import com.storiqa.storiqawallet.data.repository.AccountRepository
import com.storiqa.storiqawallet.data.repository.IAccountRepository
import com.storiqa.storiqawallet.data.repository.IUserRepository
import com.storiqa.storiqawallet.data.repository.UserRepository
import com.storiqa.storiqawallet.di.scopes.PerApplication
import com.storiqa.storiqawallet.network.WalletApi
import com.storiqa.storiqawallet.utils.SignUtil
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @PerApplication
    internal fun provideUserRepository(userDao: UserDao,
                                       walletApi: WalletApi,
                                       appDataStorage: IAppDataStorage,
                                       signUtil: SignUtil): IUserRepository {

        return UserRepository(userDao, walletApi, appDataStorage, signUtil)
    }

    @Provides
    @PerApplication
    internal fun provideAccountRepository(userDao: UserDao,
                                          accountDao: AccountDao,
                                          walletApi: WalletApi,
                                          appDataStorage: IAppDataStorage,
                                          signUtil: SignUtil): IAccountRepository {

        return AccountRepository(userDao, accountDao, walletApi, appDataStorage, signUtil)
    }

}