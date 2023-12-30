package com.example.onlineshopapp_frontend.ui.main_page

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopapp_frontend.R
import com.example.onlineshopapp_frontend.databinding.FragmentMainPageBinding
import com.example.onlineshopapp_frontend.databinding.ItemMainPageBinding

/**
 * Fragment that demonstrates a responsive layout pattern where the format of the content
 * transforms depending on the size of the screen. Specifically this Fragment shows items in
 * the [RecyclerView] using LinearLayoutManager in a small screen
 * and shows items using GridLayoutManager in a large screen.
 */
class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainPageViewModel = ViewModelProvider(this).get(MainPageViewModel::class.java)
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerviewMainPage
        val adapter = MainPageAdapter()
        recyclerView.adapter = adapter
        mainPageViewModel.texts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class MainPageAdapter :
        ListAdapter<String, MainPageViewHolder>(object : DiffUtil.ItemCallback<String>() {

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }) {

        private val drawables = listOf(
            R.drawable.avatar_1,
            R.drawable.avatar_2,
            R.drawable.avatar_3,
            R.drawable.avatar_4,
            R.drawable.avatar_5,
            R.drawable.avatar_6,
            R.drawable.avatar_7,
            R.drawable.avatar_8,
            R.drawable.avatar_9,
            R.drawable.avatar_10,
            R.drawable.avatar_11,
            R.drawable.avatar_12,
            R.drawable.avatar_13,
            R.drawable.avatar_14,
            R.drawable.avatar_15,
            R.drawable.avatar_16,
        )

        private val prices = listOf(
            "$10",
            "$20",
            "$30",
            "$40",
            "$50",
            "$60",
            "$70",
            "$80",
            "$90",
            "$100",
            "$110",
            "$120",
            "$130",
            "$140",
            "$150",
            "$160",
        )

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageViewHolder {
            val binding = ItemMainPageBinding.inflate(LayoutInflater.from(parent.context))
            return MainPageViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MainPageViewHolder, position: Int) {
            holder.nameView.text = getItem(position)
            holder.imageView.setImageDrawable(
                ResourcesCompat.getDrawable(holder.imageView.resources, drawables[position], null)
            )
            holder.priceView.text = prices[position]
            holder.itemView.setOnClickListener {
                val item = getItem(position)
                val intent = Intent(holder.itemView.context, ItemDetailActivity::class.java)
                intent.putExtra("name", item)
                intent.putExtra("description", item)
                intent.putExtra("image", drawables[position])
                intent.putExtra("position", position)
                intent.putExtra("price", prices[position])
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    class MainPageViewHolder(binding: ItemMainPageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imageView: ImageView = binding.imageViewItemMainPage
        val nameView: TextView = binding.textViewItemMainPage
        val priceView: TextView = binding.priceTextViewMainPage!!
    }
}