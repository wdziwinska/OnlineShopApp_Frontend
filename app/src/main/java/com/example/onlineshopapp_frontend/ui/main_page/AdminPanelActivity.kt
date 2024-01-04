package com.example.onlineshopapp_frontend.ui.main_page

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineshopapp_frontend.R
import com.example.onlineshopapp_frontend.databinding.AdminPanelBinding

class AdminPanelActivity: AppCompatActivity(), View.OnFocusChangeListener, View.OnKeyListener {

    private lateinit var binding: AdminPanelBinding

    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AdminPanelBinding.inflate(LayoutInflater.from(this))
        binding.nameTextInputEditText.onFocusChangeListener = this
        binding.descriptionTextInputEditText.onFocusChangeListener = this
        binding.priceTextInputEditText.onFocusChangeListener = this

        binding.addImageButton.setOnClickListener {
            pickImageFromGallery()
        }

        setContentView(binding.root)
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.imageView.setImageURI(data?.data)
        }
    }

    private fun validationName(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.nameTextInputEditText.text.toString()

        if(value.isEmpty()){
            errorMessage = "Nazwa jest wymagana"
        }
        if(errorMessage != null){
            binding.nameTextInputLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validationDescription(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.descriptionTextInputEditText.text.toString()

        if(value.isEmpty()){
            errorMessage = "Opis jest wymagany"
        }
        if(errorMessage != null){
            binding.descriptionTextInputLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validationPrice(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.priceTextInputEditText.text.toString()

        if(value.isEmpty()){
            errorMessage = "Opis jest wymagany"
        }
        if(errorMessage != null){
            binding.priceTextInputLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(view != null) {
            when(view.id){
                R.id.nameTextInputEditText -> {
                    if(hasFocus){
                        if(binding.nameTextInputLayout.isErrorEnabled){
                            binding.nameTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        validationName()
                    }
                }
                R.id.descriptionTextInputEditText -> {
                    if(hasFocus){
                        if(binding.descriptionTextInputLayout.isErrorEnabled){
                            binding.descriptionTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        validationDescription()
                    }
                }
                R.id.priceTextInputEditText -> {
                    if(hasFocus){
                        if(binding.priceTextInputLayout.isErrorEnabled){
                            binding.priceTextInputLayout.isErrorEnabled = false
                        }
                    } else {
                        validationPrice()
                    }
                }
            }
        }
    }
    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }
}