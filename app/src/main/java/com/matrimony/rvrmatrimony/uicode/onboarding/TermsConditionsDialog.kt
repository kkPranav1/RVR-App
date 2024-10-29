package com.matrimony.rvrmatrimony.uicode.onboarding

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseDialogFragment
import com.matrimony.rvrmatrimony.databinding.DialogTermsConditionsBinding

class TermsConditionsDialog(
    private val ctx: Context,
    private val termsConditionsList: List<TermsDataModel>
) : BaseDialogFragment<DialogTermsConditionsBinding>(ctx) {

    private lateinit var binding: DialogTermsConditionsBinding
    override fun getLayoutId(): Int = R.layout.dialog_terms_conditions
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getDialogFragmentBinding()
        initComposeUi()
    }

    private fun initComposeUi() {
        // Custom fonts from resources
        val fontMerriweatherExtraBold = FontFamily(Font(R.font.merriweathersans_extrabold))
        val fontMerriweatherBold = FontFamily(Font(R.font.merriweathersans_bold))
        val fontMerriweatherMedium = FontFamily(Font(R.font.merriweathersans_medium))
        binding.tcDialogComposeView.setContent {
            // TermsConditionsComposeContent1()
            TermsConditionsMainScreen(
                fontExtraBold = fontMerriweatherExtraBold,
                fontBold = fontMerriweatherBold,
                fontMedium = fontMerriweatherMedium,
                isDarkMode = isSystemInDarkTheme()
            )
        }
    }

    @Composable
    fun TermsConditionsMainScreen(
        fontExtraBold: FontFamily,
        fontBold: FontFamily,
        fontMedium: FontFamily,
        isDarkMode: Boolean = false
    ) {
        val backgroundColor = if (isDarkMode) Color.Black else Color.White
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TCTitleTextView(fontExtraBold)
            TCListRecyclerScreen(
                termsConditionsList,
                fontBold,
                fontMedium,
                Modifier.fillMaxWidth().weight(1f)
            )
            TCDismissButton(fontBold, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }

    // ListScreen Composable for displaying the list

    @Composable
    fun TCTitleTextView(fontExtraBold: FontFamily, modifier: Modifier = Modifier) {
        // Title Text
        Text(
            text = "Terms and Conditions",
            color = colorResource(R.color.black),
            fontSize = 15.sp,
            fontFamily = fontExtraBold,
            modifier = modifier
                .width(300.dp)  // Fixed width
                .height(50.dp)  // Fixed height
                .padding(10.dp)
        )
    }

    @Composable
    fun TCListRecyclerScreen(
        itemsList: List<TermsDataModel>,
        fontBold: FontFamily,
        fontMedium: FontFamily,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(modifier = modifier) {
            items(itemsList.size) { i ->
                TCItemView(item = itemsList[i], fontBold = fontBold, fontMedium = fontMedium)
            }
        }
    }

    @Composable
    fun TCDismissButton(
        fontBold: FontFamily,
        modifier: Modifier = Modifier
    ) {
        Button(
            onClick = {
                this@TermsConditionsDialog.dismiss()
            },
            modifier = modifier
                .width(180.dp)
                .height(38.dp)
                .background(colorResource(id = R.color.background_color_et1)),
        ) {
            Text(
                text = "Clear",
                fontFamily = fontBold,
                fontSize = 14.sp,
                // color = colorResource(R.color.lite_yellow) // Button text color
            )
        }
    }

    @Composable
    fun TCItemView(item: TermsDataModel, fontBold: FontFamily, fontMedium: FontFamily) {
        Column(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
            // Title text in each list item
            Text(
                text = item.termTitle,
                color = colorResource(R.color.black),
                fontFamily = fontBold,
                fontSize = 14.sp
            )
            // Subtitle text in each list item
            Text(
                text = item.termDesc,
                color = colorResource(R.color.black),
                fontFamily = fontMedium,
                fontSize = 10.sp
            )
        }
    }

    override fun handleDayNightChange(isDay: Boolean) {
        // binding.tcDialogComposeView.invalidate()
        binding.tcDialogCL.setBackgroundColor(ContextCompat.getColor(ctx, R.color.white))
        this@TermsConditionsDialog.dismiss()
    }


    /*
    * Test Composables below----------------------------------------
    *
    *
    *
    *
    * */


    @Composable
    fun TermsConditionsComposeContent1() {
        // Text(text = "Terms and Conditions", modifier = Modifier.fillMaxSize())
        TermsAndConditionsListRecyclerScreen1(termsConditionsList)
    }

    @Composable
    fun TermAndConditionItemView1(itemData: TermsDataModel) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Text(text = itemData.termTitle, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = itemData.termTitle, style = MaterialTheme.typography.body2)
        }
    }

    @Composable
    fun TermsAndConditionsListRecyclerScreen1(termsList: List<TermsDataModel>) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(termsList.size) { index ->
                TermAndConditionItemView1(termsList[index])
            }
        }
    }

}