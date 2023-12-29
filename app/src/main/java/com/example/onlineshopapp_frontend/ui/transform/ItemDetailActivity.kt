package com.example.onlineshopapp_frontend.ui.transform

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.onlineshopapp_frontend.R
import com.example.onlineshopapp_frontend.databinding.ActivityItemDetailBinding
import com.example.onlineshopapp_frontend.ui.transform.TransformFragment

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemDetailBinding

    private val descriptions = listOf(
        "Laptop Inspire Pro 15: Nowoczesny laptop z potężnym procesorem i długą żywotnością baterii, idealny do pracy i rozrywki.",
        "Smartwatch ConnectFit X: Elegancki zegarek z funkcjami monitorowania zdrowia, powiadomieniami o wiadomościach i stylowym designem.",
        "Kamera MirrorShot 4K: Kompaktowa kamera z funkcją nagrywania w jakości 4K, idealna do rejestrowania ważnych chwil.",
        "Słuchawki douszne SoundWave: Wygodne słuchawki douszne z doskonałym dźwiękiem, idealne do słuchania ulubionej muzyki w podróży.",
        "Ekspres do kawy AromaBlend: Nowoczesny ekspres, który przygotuje doskonałą kawę o intensywnym aromacie w zaledwie kilka minut.",
        "Rowerek treningowy FitCycle Pro: Zaawansowany rowerek treningowy z programami treningowymi i monitorowaniem parametrów fizycznych.",
        "Zestaw garnków Chef's Choice: Elegancki zestaw garnków z wysokiej jakości stali nierdzewnej, idealny do gotowania zdrowych posiłków.",
        "Smartfon Galaxy Pro X: Flagowy smartfon z potężnym aparatem, dużym ekranem i zaawansowanymi funkcjami.",
        "Odkurzacz TurboClean Max: Wydajny odkurzacz z zaawansowaną technologią, usuwający kurz i alergeny z każdego kąta.",
        "Nawigacja satelitarna ExploreNav 5000: Zaawansowana nawigacja z funkcją śledzenia tras, idealna dla miłośników podróży.",
        "Masażer RelaxTone Plus: Masażer do relaksu mięśni z różnymi trybami masażu i funkcją podgrzewania.",
        "Blender VitalBlend Pro: Potężny blender do przygotowywania zdrowych smoothie i koktajli, z wieloma funkcjami.",
        "Zestaw narzędziowy MasterCraft: Solidny zestaw narzędziowy zawierający wszystko, co potrzebne do domowych napraw i majsterkowania.",
        "Pamięć USB SwiftDrive 128GB: Przenośna pamięć USB o dużej pojemności, idealna do przechowywania i przenoszenia danych.",
        "Grill Gazowy FireMaster Deluxe: Nowoczesny grill gazowy z zaawansowanymi funkcjami regulacji temperatury, doskonały do grillowania na świeżym powietrzu.",
        "Konsola do gier QuantumPlay X: Zaawansowana konsola do gier z bogatą biblioteką gier, idealna dla pasjonatów rozrywki wideo."
    )

    @SuppressLint("MissingInflatedId", "SetTextI18n", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the views
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val itemImageView = findViewById<ImageView>(R.id.itemImageView)
        val priceTextView = findViewById<TextView>(R.id.priceTextView)

        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val imageId = intent.getIntExtra("image", 0)
        val position = intent.getIntExtra("position", 0)
        val price = intent.getStringExtra("price")

        // Populate views with item details
        nameTextView.text = name
        descriptionTextView.text = descriptions[position]
        itemImageView.setImageResource(imageId)
        priceTextView.text = price
    }

    override fun onStart() {
        super.onStart()

        binding.buttonBuyNow.setOnClickListener {
            val item = "34"
            val intent = Intent(binding.buttonBuyNow.context, BuyNowActivity::class.java)
            intent.putExtra("price", item)
            binding.buttonBuyNow.context.startActivity(intent)
        }
    }
}
