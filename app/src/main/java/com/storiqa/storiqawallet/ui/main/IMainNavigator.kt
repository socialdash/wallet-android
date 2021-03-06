package com.storiqa.storiqawallet.ui.main

import android.os.Bundle
import android.view.View
import com.storiqa.storiqawallet.ui.base.navigator.IBaseNavigator

interface IMainNavigator : IBaseNavigator {

    fun showLoginFragment()

    fun showWalletFragment()

    fun showSendFragment()

    fun showExchangeFragment()

    fun showReceiveFragment()

    fun showMenuFragment()

    fun showAccountFragment(bundle: Bundle, element: View, transition: String)

    fun showTransactionsFragment(accountAddress: String)

    fun showTransactionDetailsFragment(address: String, transactionId: String)

    fun showSendConfirmationDialog(address: String, amount: String, fee: String, total: String, onConfirm: () -> Unit)

    fun showExchangeConfirmationDialog(
            remittanceAccount: String,
            remittanceAmount: String,
            collectionAccount: String,
            collectionAmount: String,
            onConfirm: () -> Unit
    )

}