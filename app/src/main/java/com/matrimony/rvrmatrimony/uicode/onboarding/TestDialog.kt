package com.matrimony.rvrmatrimony.uicode.onboarding


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseDialogFragment
import com.matrimony.rvrmatrimony.databinding.DialogTermsConditionsBinding

// Main purpose is for Testing Compose UI
class TestDialog(
    private val ctx: Context
) : BaseDialogFragment<DialogTermsConditionsBinding>(ctx) {
    override fun getLayoutId(): Int = R.layout.dialog_terms_conditions
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDialogFragmentBinding().tcDialogComposeView.setContent {
            TestDialogComposeContent()
        }
    }

    @Composable
    fun TestDialogComposeContent() {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "This is a Compose Dialog", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { dismiss() }) {
                Text("Close")
            }
        }
    }

    override fun handleDayNightChange(isDay: Boolean) {

    }

}