package com.example.onlineshopapp_frontend.ui.authentication.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.onlineshopapp_frontend.R
import com.example.onlineshopapp_frontend.databinding.ActivityRegisterBinding
import com.example.onlineshopapp_frontend.ui.authentication.data.dto.user.AuthorizationResponse
import com.example.onlineshopapp_frontend.ui.authentication.data.services.UserService
import com.example.onlineshopapp_frontend.ui.authentication.data.token.TokenManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener,
    View.OnKeyListener {

    private lateinit var mBinding: ActivityRegisterBinding
    private var userService = UserService.create()
    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))

        setContentView(mBinding.root)
        mBinding.loginTextInputEditText.onFocusChangeListener = this
        mBinding.firstNameTextInputEditText.onFocusChangeListener = this
        mBinding.lastNameTextInputEditText.onFocusChangeListener = this
        mBinding.emailTextInputEditText.onFocusChangeListener = this
        mBinding.passwordTextInputEditText.onFocusChangeListener = this
        mBinding.confirmPasswordTextInputEdit.onFocusChangeListener = this

        mBinding.registerButton.setOnClickListener {
            val login = mBinding.loginTextInputEditText.text.toString();
            val firstName = mBinding.firstNameTextInputEditText.text.toString();
            val lastName = mBinding.lastNameTextInputEditText.text.toString();
            val email = mBinding.emailTextInputEditText.text.toString();
            val password = mBinding.passwordTextInputEditText.text.toString();

            lifecycleScope.launch {
                try {
                    val response = registerUser(login, firstName, lastName, email, password)
                    response?.let { x ->
                        tokenManager = TokenManager(this@RegisterActivity)
                        tokenManager.saveToken(x.token)
                    }
                } catch (e: Exception) {
                    // Obsługa błędu logowania
                }
            }

/*            if (!(mBinding.loginTextInputLayout.isErrorEnabled
                        && mBinding.firstNameTextInputLayout.isErrorEnabled
                        && mBinding.lastNameTextInputLayout.isErrorEnabled
                        && mBinding.emailTextInputLayout.isErrorEnabled
                        && mBinding.passwordTextInputLayout.isErrorEnabled
                        && mBinding.confirmPasswordTextInputLayout.isErrorEnabled)
            ) {


            } else {
                Toast.makeText(this@RegisterActivity, "Wypełnij wszystkie pola", Toast.LENGTH_SHORT)
                    .show()
            }*/

        }
    }

    private suspend fun registerUser(login: String, firstName: String, lastName: String, email: String, password: String)
    : AuthorizationResponse? {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext userService.registerUser(login, firstName, lastName, email, password)
            } catch (e: Exception) {
                // Obsługa błędu logowania
                return@withContext null
            }
        }
    }


    private fun validationFirstName(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.firstNameTextInputEditText.text.toString()

        if (value.isEmpty()) {
            errorMessage = "First name is required"
        }

        if (errorMessage != null) {
            mBinding.firstNameTextInputLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validationLogin(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.loginTextInputEditText.text.toString()

        if (value.isEmpty()) {
            errorMessage = "Login is required"
        }

        if (errorMessage != null) {
            mBinding.loginTextInputLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validationLastName(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.lastNameTextInputEditText.text.toString()

        if (value.isEmpty()) {
            errorMessage = "Last name is required"
        }

        if (errorMessage != null) {
            mBinding.lastNameTextInputLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validationEmail(): Boolean {
        var errorMessage: String? = null
        val value: String = mBinding.emailTextInputEditText.text.toString()

        if (value.isEmpty()) {
            errorMessage = "Email is required"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            errorMessage = "Please enter a valid email address"
        }

        if (errorMessage != null) {
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

        if (value.isEmpty()) {
            errorMessage = "Password is required"
        } else if (value.length < 6) {
            errorMessage = "Password must be at least 6 characters long"
        }
        if (errorMessage != null) {
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

        if (value.isEmpty()) {
            errorMessage = "Confirm password is required"
        } else if (value.length < 6) {
            errorMessage = "Confirm password must be at least 6 characters long"
        } else if (mBinding.passwordTextInputEditText.text.toString() != value) {
            errorMessage = "Password and confirm password must be the same"
        }
        if (errorMessage != null) {
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

        if (password != confirmPassword) {
            errorMessage = "Password and confirm password must be the same"
        }
        if (errorMessage != null) {
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
        if (view != null) {
            when (view.id) {
                R.id.loginTextInputEditText -> {
                    if (hasFocus) {
                        if (mBinding.loginTextInputLayout.isErrorEnabled) {
                            mBinding.loginTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        validationLogin()
                    }
                }

                R.id.firstNameTextInputEditText -> {
                    if (hasFocus) {
                        if (mBinding.firstNameTextInputLayout.isErrorEnabled) {
                            mBinding.firstNameTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        validationFirstName()
                    }
                }

                R.id.lastNameTextInputEditText -> {
                    if (hasFocus) {
                        if (mBinding.lastNameTextInputLayout.isErrorEnabled) {
                            mBinding.lastNameTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        validationLastName()
                    }
                }

                R.id.loginTextInputEditText -> {
                    if (hasFocus) {
                        if (mBinding.emailTextInputLayout.isErrorEnabled) {
                            mBinding.emailTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        validationEmail()
                    }
                }

                R.id.passwordTextInputEditText -> {
                    if (hasFocus) {
                        if (mBinding.passwordTextInputLayout.isErrorEnabled) {
                            mBinding.passwordTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        if (validationPassword() && mBinding.confirmPasswordTextInputEdit.text!!.isNotEmpty() && validationConfirmPassword() && validationPassowrdAndConfirmPassword()) {
                            if (mBinding.confirmPasswordTextInputLayout.isErrorEnabled) {
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
                    if (hasFocus) {
                        if (mBinding.confirmPasswordTextInputLayout.isErrorEnabled) {
                            mBinding.confirmPasswordTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        if (validationConfirmPassword() && validationPassword() && validationPassowrdAndConfirmPassword()) {
                            if (mBinding.passwordTextInputLayout.isErrorEnabled) {
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