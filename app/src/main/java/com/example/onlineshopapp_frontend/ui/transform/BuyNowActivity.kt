package com.example.onlineshopapp_frontend.ui.transform

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineshopapp_frontend.R

class BuyNowActivity : AppCompatActivity()
{
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_now)

        val priceTextView = findViewById<TextView>(R.id.priceTextView)
        val nameProductTextView = findViewById<TextView>(R.id.productNameTextView)
        val imageView = findViewById<ImageView>(R.id.itemImageView)

        val price = intent.getStringExtra("price")
        val name = intent.getStringExtra("name")
        val imageId = intent.getIntExtra("image", 0)

        imageView.setImageResource(R.drawable.avatar_16)
        priceTextView.text = price
        nameProductTextView.text = name
    }
}