package com.example.onlineshopapp_frontend.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineshopapp_frontend.R

class ItemDetailActivity : AppCompatActivity() {

    private val descriptions = listOf(
        "description1",
        "description2",
        "description3",
        "description4",
        "description5",
        "description6",
        "description7",
        "description8",
        "description9",
        "description10",
        "description11",
        "description12",
        "description13",
        "description14",
        "description15",
        "description16"
    )

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        // Get the item from the intent extra
        val item = intent.getStringExtra("item")

        // Find the views
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val itemImageView = findViewById<ImageView>(R.id.itemImageView)

        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val imageId = intent.getIntExtra("image", 0)
        val position = intent.getIntExtra("position", 0)

        // Populate views with item details
        nameTextView.text = name
        descriptionTextView.text = descriptions[position]
        itemImageView.setImageResource(imageId)
    }

}
