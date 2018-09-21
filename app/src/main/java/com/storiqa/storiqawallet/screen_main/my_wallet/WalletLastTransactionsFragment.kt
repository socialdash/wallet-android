package com.storiqa.storiqawallet.screen_main.my_wallet

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.storiqa.storiqawallet.R
import com.storiqa.storiqawallet.adapters.BillsPagerAdapter
import com.storiqa.storiqawallet.adapters.TransactionAdapter
import com.storiqa.storiqawallet.constants.Extras
import com.storiqa.storiqawallet.enums.Screen
import com.storiqa.storiqawallet.objects.Bill
import com.storiqa.storiqawallet.objects.BillPagerHelper
import com.storiqa.storiqawallet.objects.Transaction
import com.storiqa.storiqawallet.screen_main.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_wallet_transactions.*
import kotlinx.android.synthetic.main.fragment_wallet_transactions.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.dip


class WalletLastTransactionsFragment : Fragment() {

    lateinit var viewModel : MainActivityViewModel
    lateinit var bills : Array<Bill>
    private val maxAmountOfTransactions = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)
        bills = arguments?.getSerializable(Extras().bill) as Array<Bill>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_wallet_transactions, container, false)

        BillPagerHelper(childFragmentManager) { pageNumber ->
            viewModel.updateTransactionList(bills[pageNumber].id, maxAmountOfTransactions)
            viewModel.selectedBillId = bills[pageNumber].id
        }.setPager(view.vpBills, view.pageIndicator, bills, arguments?.getString(Extras().idOfSelectedBill)!!)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnBack.onClick { viewModel.goBack() }
        tvViewAll.onClick {
            fragmentManager?.let {
                val walletAllTransactionsFragment = WalletAllTransactionsFragment()
                it.beginTransaction().replace(R.id.flWallet, walletAllTransactionsFragment)
                        .addToBackStack("")
                        .commit() }
        }

        btnSend.onClick { viewModel.selectScreen(Screen.SEND) }
        btnChange.onClick { viewModel.selectScreen(Screen.EXCHANGE) }
        btnDeposit.onClick { viewModel.selectScreen(Screen.DEPOSIT) }
    }

    override fun onResume() {
        super.onResume()
        viewModel.transactions.observe(this, Observer<Array<Transaction>> { newTransactions ->
            rvTransactions.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = TransactionAdapter(newTransactions!!, {})
            }
        })
        viewModel.updateTransactionList(bills[0].id, maxAmountOfTransactions)
    }

    override fun onPause() {
        viewModel.transactions.removeObserver {  }
        super.onPause()
    }

    companion object {
        fun getInstance(idOfSelectedBill : String, bills : Array<Bill>) : WalletLastTransactionsFragment {
            val walletTransactionsFragment = WalletLastTransactionsFragment()
            val bundle = Bundle()
            bundle.putSerializable(Extras().bill, bills)
            bundle.putString(Extras().idOfSelectedBill, idOfSelectedBill)
            walletTransactionsFragment.arguments = bundle
            return walletTransactionsFragment
        }
    }

}