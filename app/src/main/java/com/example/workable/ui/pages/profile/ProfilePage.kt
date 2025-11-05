package com.example.workable.ui.pages.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.workable.databinding.PageProfileBinding
import com.example.workable.viewmodel.ProfileViewModel

class ProfilePage : Fragment() {

    private lateinit var binding: PageProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe user data
        viewModel.userName.observe(viewLifecycleOwner) { name ->
            binding.userName.text = name
        }

        viewModel.userEmail.observe(viewLifecycleOwner) { email ->
            binding.userEmail.text = email
        }

        viewModel.profileImageUrl.observe(viewLifecycleOwner) { imageUrl ->
            // TODO: Load image with Glide/Picasso
            // Example with Glide:
            // Glide.with(this).load(imageUrl).into(binding.profileImage)
        }

        // Handle logout
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            // TODO: Navigate to login page
        }

        // Handle edit profile
        binding.editProfileButton.setOnClickListener {
            // TODO: Navigate to EditProfilePage
        }
    }
}
