package com.example.onlineshopapp_frontend.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshopapp_frontend.R
import com.example.onlineshopapp_frontend.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textAccount
//        accountViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    private lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load user data
        user = loadUser()

        // Populate views
        view.findViewById<TextView>(R.id.textName).text = "${user.firstName} ${user.lastName}"
        view.findViewById<TextView>(R.id.textEmail).text = user.email
    }

    private fun loadUser(): User {
        // Dummy data
        return User("John", "Doe", "john@doe.com")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}