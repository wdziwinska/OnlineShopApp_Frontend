package com.example.onlineshopapp_frontend.ui.transform

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineshopapp_frontend.R

class BuyNowActivity : AppCompatActivity()
{
    private lateinit var priceTextView: TextView
    private lateinit var nameProductTextView: TextView
    private lateinit var imageView: ImageView
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_now)

        findViews()

        val price = intent.getStringExtra("price")
        val name = intent.getStringExtra("name")
        val imageId = intent.getIntExtra("image", 0)

        imageView.setImageResource(R.drawable.avatar_16)
        priceTextView.text = price
        nameProductTextView.text = name

        val namePointFields = findViewById<LinearLayout>(R.id.namePointTextInputLayout)
        val nameAddressFields = findViewById<LinearLayout>(R.id.addressTextInputLayout)

        namePointFields.visibility = View.GONE
        nameAddressFields.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()

        setupRadioGroup()
    }

    private fun findViews() {
        priceTextView = findViewById(R.id.priceTextView)
        nameProductTextView = findViewById(R.id.productNameTextView)
        imageView = findViewById<ImageView>(R.id.itemImageView)
    }

    private fun setupRadioGroup() {
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val nameAddressFields = findViewById<LinearLayout>(R.id.addressTextInputLayout)
        val namePointFields = findViewById<LinearLayout>(R.id.namePointTextInputLayout)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->

            if(checkedId == R.id.radioButton1) {
                nameAddressFields.visibility = View.VISIBLE
                namePointFields.visibility = View.GONE
            }
            else if(checkedId == R.id.radioButton2) {
                nameAddressFields.visibility = View.GONE
                namePointFields.visibility = View.VISIBLE
            }
        }
    }
}