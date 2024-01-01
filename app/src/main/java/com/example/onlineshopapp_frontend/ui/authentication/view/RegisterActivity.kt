package com.example.onlineshopapp_frontend.ui.authentication.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineshopapp_frontend.R
import com.example.onlineshopapp_frontend.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener {

    private lateinit var mBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.fullNameTextInputEditText.onFocusChangeListener = this
        mBinding.emailTextInputEditText.onFocusChangeListener = this
        mBinding.passwordTextInputEditText.onFocusChangeListener = this
        mBinding.confirmPasswordTextInputEdit.onFocusChangeListener = this
    }

    private fun validationFullName(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.fullNameTextInputEditText.text.toString()

        if(value.isEmpty()){
            errorMessage = "Full name is required"
        }

        if(errorMessage != null){
            mBinding.fullNameTextInputLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }
    private fun validationEmail(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.emailTextInputEditText.text.toString()

        if(value.isEmpty()){
            errorMessage = "Email is required"
        } else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            errorMessage = "Please enter a valid email address"
        }

        if(errorMessage != null){
            mBinding.emailTextInputLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }
    private fun validationPassword(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.passwordTextInputEditText.text.toString()

        if(value.isEmpty()){
            errorMessage = "Password is required"
        } else if(value.length < 6){
            errorMessage = "Password must be at least 6 characters long"
        }
        if(errorMessage != null){
            mBinding.passwordTextInputLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }
    private fun validationConfirmPassword(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.confirmPasswordTextInputEdit.text.toString()

        if(value.isEmpty()){
            errorMessage = "Confirm password is required"
        } else if(value.length < 6){
            errorMessage = "Conbfrim password must be at least 6 characters long"
        } else if(mBinding.passwordTextInputEditText.text.toString()!= value){
            errorMessage = "Password and confirm password must be the same"
        }
        if(errorMessage != null){
            mBinding.confirmPasswordTextInputLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }
    private fun validationPassowrdAndConfirmPassword(): Boolean {
        var errorMessage: String? = null
        val password: String = mBinding.passwordTextInputEditText.text.toString()
        val confirmPassword: String = mBinding.confirmPasswordTextInputEdit.text.toString()

        if(password != confirmPassword){
            errorMessage = "Password and confirm password must be the same"
        }
        if(errorMessage != null){
            mBinding.confirmPasswordTextInputLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }
    override fun onClick(p0: View?) {
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(view != null) {
            when(view.id){
                R.id.fullNameTextInputEditText -> {
                    if(hasFocus){
                        if(mBinding.fullNameTextInputLayout.isErrorEnabled){
                            mBinding.fullNameTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        validationFullName()
                    }
                }
                R.id.emailTextInputEditText -> {
                    if(hasFocus){
                        if(mBinding.emailTextInputLayout.isErrorEnabled){
                            mBinding.emailTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        validationEmail()
                    }
                }
                R.id.passwordTextInputEditText -> {
                    if(hasFocus){
                        if(mBinding.passwordTextInputLayout.isErrorEnabled){
                            mBinding.passwordTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        if(validationPassword() && mBinding.confirmPasswordTextInputEdit.text!!.isNotEmpty() && validationConfirmPassword() && validationPassowrdAndConfirmPassword()){
                            if(mBinding.confirmPasswordTextInputLayout.isErrorEnabled){
                                mBinding.confirmPasswordTextInputLayout.isErrorEnabled = false
                            }
                            mBinding.confirmPasswordTextInputLayout.apply {
                                setStartIconDrawable(R.drawable.ic_check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
                R.id.confirmPasswordTextInputEdit -> {
                    if(hasFocus){
                        if(mBinding.confirmPasswordTextInputLayout.isErrorEnabled){
                            mBinding.confirmPasswordTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        if(validationConfirmPassword() && validationPassword() && validationPassowrdAndConfirmPassword()){
                            if(mBinding.passwordTextInputLayout.isErrorEnabled){
                                mBinding.passwordTextInputLayout.isErrorEnabled = false
                            }
                            mBinding.confirmPasswordTextInputLayout.apply {
                                setStartIconDrawable(R.drawable.ic_check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }
}