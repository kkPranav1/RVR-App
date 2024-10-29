package com.matrimony.rvrmatrimony.uicode.onboarding

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.databinding.DialogRegisterNewUserBinding

class RegisterNewUserDialog(
    private val activityCtx: Activity
) : Dialog(activityCtx) {

    private lateinit var binding: DialogRegisterNewUserBinding
    private var isPasswordVisible = false


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(activityCtx),
            R.layout.dialog_register_new_user,
            null,
            false
        )

        val etPassword: EditText = binding.etPassword
        val etConfirmPassword: EditText = binding.etPassword

        etConfirmPassword.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (etPassword.right - etPassword.compoundDrawables[2].bounds.width())) {
                    if (isPasswordVisible) {
                        // Hide password
                        etPassword.inputType =
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_eye_off,
                            0
                        )
                    } else {
                        // Show password
                        etPassword.inputType = InputType.TYPE_CLASS_TEXT
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_eye_on,
                            0
                        )
                    }
                    isPasswordVisible = !isPasswordVisible
                    etPassword.setSelection(etPassword.text.length) // Keep cursor at end
                    return@setOnTouchListener true
                }
            }
            false
        }
        etConfirmPassword.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (etConfirmPassword.right - etConfirmPassword.compoundDrawables[2].bounds.width())) {
                    if (isPasswordVisible) {
                        // Hide password
                        etConfirmPassword.inputType =
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        etConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_eye_off,
                            0
                        )
                    } else {
                        // Show password
                        etConfirmPassword.inputType = InputType.TYPE_CLASS_TEXT
                        etConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_eye_on,
                            0
                        )
                    }
                    isPasswordVisible = !isPasswordVisible
                    etConfirmPassword.setSelection(etConfirmPassword.text.length)
                    return@setOnTouchListener true
                }
            }
            false
        }


        // Set the content view to the binding's root
        setContentView(binding.root)

        // Set dialog to 3/4 the height of the screen and center it
        val window = window
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            (context.resources.displayMetrics.heightPixels * 0.82).toInt()
        )
        // Optional: Set a dim background
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        val layoutParams = window?.attributes
        layoutParams?.dimAmount = 0.5f  // Dimming amount (0.0 to 1.0)
        window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        binding.registerDialogBtn.setOnClickListener {
            Toast.makeText(activityCtx, "Registration Successful", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        this.setCancelable(true)
    }


}