package com.storiqa.storiqawallet.ui.pincode

import android.os.Bundle
import androidx.lifecycle.Observer
import com.storiqa.storiqawallet.BR
import com.storiqa.storiqawallet.R
import com.storiqa.storiqawallet.databinding.ActivityPinCodeBinding
import com.storiqa.storiqawallet.ui.base.BaseActivity


class PinCodeActivity : BaseActivity<ActivityPinCodeBinding, PinCodeViewModel, IPinCodeNavigator>() {

    override fun getLayoutId(): Int = R.layout.activity_pin_code

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModelClass(): Class<PinCodeViewModel> = PinCodeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.action == "com.storiqa.storiqawallet.SETUP_PIN") {
            setupActionBar(binding.toolbar, " ", true)
            viewModel.state = PinCodeViewModel.PinCodeState.SET_UP
        } else {
            viewModel.state = PinCodeViewModel.PinCodeState.ENTER
        }

        viewModel.showPinError.observe(this, Observer {
            //todo show wrong pin error
            binding.pinIndicator.startErrorAnimation()
            //Toast.makeText(this, "Wrong pin", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        if (viewModel.state == PinCodeViewModel.PinCodeState.CONFIRM)
            viewModel.state = PinCodeViewModel.PinCodeState.SET_UP
        else if (viewModel.state == PinCodeViewModel.PinCodeState.SET_UP)
            onBackPressed()
        return true
    }
}