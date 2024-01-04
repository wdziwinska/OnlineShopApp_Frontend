package com.example.onlineshopapp_frontend.ui.main_page

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineshopapp_frontend.R
import com.example.onlineshopapp_frontend.databinding.AdminPanelBinding

class AdminPanelActivity: AppCompatActivity() {

    private lateinit var binding: AdminPanelBinding

    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AdminPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addImageButton.setOnClickListener {
            pickImageFromGallery()
        }
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
}