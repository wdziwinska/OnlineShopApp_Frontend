package com.example.onlineshopapp_frontend.ui.authentication.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineshopapp_frontend.R
import com.example.onlineshopapp_frontend.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener  {


    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        mBinding.emailTextInputEditText.onFocusChangeListener = this
        mBinding.passwordTextInputEditText.onFocusChangeListener = this
        mBinding.passwordTextInputEditText.setOnKeyListener(this)
        mBinding.loginButton.onFocusChangeListener = this
        mBinding.registerButton.onFocusChangeListener = this

        mBinding.registerButton.setOnClickListener {
            val intent = Intent(mBinding.registerButton.context, RegisterActivity::class.java)
            mBinding.registerButton.context.startActivity(intent)
        }

        setContentView(mBinding.root)
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
    override fun onClick(view: View?) {
        // TODO
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(view != null) {
            when(view.id){
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
                        validationPassword()
                    }
                }
            }
        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }
}
