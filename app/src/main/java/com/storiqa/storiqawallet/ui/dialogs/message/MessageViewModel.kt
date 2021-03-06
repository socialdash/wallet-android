package com.storiqa.storiqawallet.ui.dialogs.message

import android.graphics.drawable.Drawable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.storiqa.storiqawallet.common.SingleLiveEvent
import javax.inject.Inject

class MessageViewModel
@Inject
constructor() : ViewModel() {

    lateinit var title: ObservableField<String>
    lateinit var description: ObservableField<String>
    lateinit var icon: ObservableField<Drawable>
    lateinit var btnPositiveText: ObservableField<String>
    val btnNegativeText = ObservableField<String>("")

    val positiveButtonClicked = SingleLiveEvent<Void>()
    val negativeButtonClicked = SingleLiveEvent<Void>()

    fun initData(title: String, message: String, icon: Drawable,
                 btnPositiveText: String, btnNegativeText: String?) {
        this.title = ObservableField(title)
        this.description = ObservableField(message)
        this.icon = ObservableField(icon)
        this.btnPositiveText = ObservableField(btnPositiveText)

        if (btnNegativeText != null)
            this.btnNegativeText.set(btnNegativeText)
    }

    fun onPositiveButtonClicked() {
        positiveButtonClicked.trigger()
    }

    fun onNegativeButtonClicked() {
        negativeButtonClicked.trigger()
    }
}