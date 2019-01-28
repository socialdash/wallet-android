package com.storiqa.storiqawallet.ui.authorization.signup

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.ViewGroup
import com.storiqa.storiqawallet.BR
import com.storiqa.storiqawallet.R
import com.storiqa.storiqawallet.databinding.FragmentSignUpBinding
import com.storiqa.storiqawallet.ui.base.BaseFragment
import com.storiqa.storiqawallet.ui.common.SpacesWatcher
import com.storiqa.storiqawallet.ui.common.onSubmitButtonClicked

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_sign_up

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModelClass(): Class<SignUpViewModel> = SignUpViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.scrollView.apply {
            descendantFocusability = ViewGroup.FOCUS_BEFORE_DESCENDANTS
            isFocusable = true
            isFocusableInTouchMode = true
        }

        binding.etPassword.transformationMethod = PasswordTransformationMethod()
        binding.etPasswordRepeat.transformationMethod = PasswordTransformationMethod()

        binding.policyAgreement.movementMethod = LinkMovementMethod.getInstance()
        binding.licenseAgreement.movementMethod = LinkMovementMethod.getInstance()

        binding.etFirstName.apply {
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus)
                    viewModel.validateFirstName()
            }
            addTextChangedListener(SpacesWatcher(binding.etFirstName))
        }

        binding.etLastName.apply {
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus)
                    viewModel.validateLastName()
            }
            addTextChangedListener(SpacesWatcher(binding.etLastName))
        }

        binding.etEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                viewModel.validateEmail()
        }

        binding.etPasswordRepeat.setOnEditorActionListener { textView, actionId, event ->
            onSubmitButtonClicked(textView, actionId, event) { viewModel.onSubmitButtonClicked() }
        }
    }

}