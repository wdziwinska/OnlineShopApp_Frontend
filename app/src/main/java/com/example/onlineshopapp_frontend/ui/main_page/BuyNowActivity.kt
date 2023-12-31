package com.example.onlineshopapp_frontend.ui.main_page

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
    private lateinit var name: String
    private lateinit var price: String
    var imageId: Int = 0
    private lateinit var namePointFields: LinearLayout
    private lateinit var nameAddressFields: LinearLayout
    private lateinit var radioGroup: RadioGroup

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_now)

        findViews()

        imageView.setImageResource(R.drawable.avatar_16)
        priceTextView.text = price
        nameProductTextView.text = name

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
        imageView = findViewById(R.id.itemImageView)

        price = intent.getStringExtra("price").toString()
        name = intent.getStringExtra("name").toString()
        imageId = intent.getIntExtra("image", 0)

        namePointFields = findViewById(R.id.namePointTextInputLayout)
        nameAddressFields = findViewById(R.id.addressTextInputLayout)
        radioGroup = findViewById(R.id.radioGroup)

    }

    private fun setupRadioGroup() {

        findViews()

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