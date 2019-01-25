package com.storiqa.storiqawallet.ui.main.send

import com.storiqa.storiqawallet.BR
import com.storiqa.storiqawallet.R
import com.storiqa.storiqawallet.databinding.FragmentSendBinding
import com.storiqa.storiqawallet.ui.base.BaseFragment

class SendFragment : BaseFragment<FragmentSendBinding, SendViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_send

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModelClass(): Class<SendViewModel> = SendViewModel::class.java

}