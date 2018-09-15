package com.storiqa.storiqawallet.screen_main.my_wallet

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.storiqa.storiqawallet.R
import com.storiqa.storiqawallet.adapters.BillsAdapter
import com.storiqa.storiqawallet.constants.Extras
import com.storiqa.storiqawallet.databinding.FragmentMywalletBinding
import com.storiqa.storiqawallet.db.PreferencesHelper
import com.storiqa.storiqawallet.objects.Bill
import com.storiqa.storiqawallet.screen_main.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_mywallet.*


class MyWalletFragment : Fragment() {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)

        val binding : FragmentMywalletBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mywallet, container, false)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        refreshBillInfo()
        PreferencesHelper(context!!).setQuickLaunchFinished()
    }

    private fun refreshBillInfo() {
        rvBills?.apply {
            adapter = BillsAdapter(viewModel.bills.value!!) { positionOfClickedBill ->
                viewModel.selectedBillId.value = viewModel.bills.value!![positionOfClickedBill].id
            }
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    companion object {
        fun getInstance(bills : Array<Bill>) : MyWalletFragment {
            val myWalletFragment = MyWalletFragment()
            val bundle = Bundle()
            bundle.putSerializable(Extras().bill, bills)
            myWalletFragment.arguments = bundle
            return myWalletFragment
        }
    }
}