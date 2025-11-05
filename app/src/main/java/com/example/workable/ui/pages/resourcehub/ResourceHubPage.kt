package com.example.workable.ui.pages.resourcehub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workable.databinding.PageResourceHubBinding
import com.example.workable.model.Resource
import com.example.workable.ui.pages.resourcehub.adapter.ResourceAdapter
import com.example.workable.viewmodel.ResourceHubViewModel

class ResourceHubPage : Fragment() {

    private lateinit var binding: PageResourceHubBinding
    private val viewModel: ResourceHubViewModel by viewModels()
    private lateinit var adapter: ResourceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageResourceHubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ResourceAdapter(emptyList()) { resource ->
            // TODO: Handle resource click (open details or external link)
        }

        binding.resourcesRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.resourcesRecycler.adapter = adapter

        // Observe resources from ViewModel
        viewModel.resources.observe(viewLifecycleOwner) { resources ->
            adapter.updateData(resources)
        }

        // Loading and error handling
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            binding.errorText.apply {
                visibility = if (error != null) View.VISIBLE else View.GONE
                text = error ?: ""
            }
        }

        // Fetch resources
        viewModel.loadResources()
    }
}
