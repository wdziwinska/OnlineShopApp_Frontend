package com.example.onlineshopapp_frontend.ui.transform

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineshopapp_frontend.R

class BuyNowActivity : AppCompatActivity()
{
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_now)

        val priceTextView = findViewById<TextView>(R.id.priceTextView)

        val price = intent.getStringExtra("price")

        priceTextView.text = price
    }
}