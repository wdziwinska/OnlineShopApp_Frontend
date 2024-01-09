package com.example.onlineshopapp_frontend.ui.authentication.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.onlineshopapp_frontend.R
import com.example.onlineshopapp_frontend.databinding.ActivityLoginBinding
import com.example.onlineshopapp_frontend.ui.authentication.data.dto.user.AuthorizationResponse
import com.example.onlineshopapp_frontend.ui.authentication.data.services.UserService
import com.example.onlineshopapp_frontend.ui.authentication.data.token.TokenManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener  {


    private lateinit var mBinding: ActivityLoginBinding
    private var userService = UserService.create()
    private lateinit var tokenManager: TokenManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        mBinding.loginTextInputEditText.onFocusChangeListener = this
        mBinding.passwordTextInputEditText.onFocusChangeListener = this
        mBinding.passwordTextInputEditText.setOnKeyListener(this)
        mBinding.loginButton.onFocusChangeListener = this
        mBinding.registerButton.onFocusChangeListener = this

        mBinding.loginButton.setOnClickListener {
            val login = mBinding.loginTextInputEditText.text.toString()
            val password = mBinding.passwordTextInputEditText.text.toString()

            // Zamiast używać GlobalScope.launch, użyj lifecycleScope.launch
            lifecycleScope.launch {
                try {
                    val response = login(login, password)
                    response?.let { x ->
                        tokenManager = TokenManager(this@LoginActivity)
                        tokenManager.saveToken(x.token)
                    }
                } catch (e: Exception) {
                    // Obsługa błędu logowania
                }
            }
        }



        mBinding.registerButton.setOnClickListener {
            val intent = Intent(mBinding.registerButton.context, RegisterActivity::class.java)
            mBinding.registerButton.context.startActivity(intent)
        }

        setContentView(mBinding.root)
    }

    private suspend fun login(login: String, password: String): AuthorizationResponse? {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext userService.loginUser(login, password)
            } catch (e: Exception) {
                // Obsługa błędu logowania
                return@withContext null
            }
        }
    }

    private fun validationLogin(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.loginTextInputEditText.text.toString()

        if(value.isEmpty()){
            errorMessage = "Login is required"
        }

        if(errorMessage != null){
            mBinding.loginTextInputLayout.apply {
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
                        if(mBinding.loginTextInputLayout.isErrorEnabled){
                            mBinding.loginTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        validationLogin()
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
