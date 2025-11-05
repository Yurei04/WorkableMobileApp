package com.example.jobsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Model for a single resource item
data class ResourceItem(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val url: String // could be a link to a PDF, webpage, or video
)

class ResourceHubViewModel : ViewModel() {

    private val _resources = MutableLiveData<List<ResourceItem>>()
    val resources: LiveData<List<ResourceItem>> = _resources

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadResources()
    }

    private fun loadResources() {
        _loading.value = true
        try {
            // Dummy data — replace with Firebase or API later
            val sampleResources = listOf(
                ResourceItem(
                    id = 1,
                    title = "How to Write a Great Resume",
                    description = "Tips and templates to build a professional resume.",
                    category = "Resume",
                    url = "https://example.com/resume-guide"
                ),
                ResourceItem(
                    id = 2,
                    title = "Top 50 Interview Questions",
                    description = "Common interview questions and how to answer them.",
                    category = "Interview",
                    url = "https://example.com/interview-questions"
                ),
                ResourceItem(
                    id = 3,
                    title = "Free Online Courses for Developers",
                    description = "A curated list of online learning resources.",
                    category = "Learning",
                    url = "https://example.com/free-courses"
                )
            )

            _resources.value = sampleResources
            _error.value = null
        } catch (e: Exception) {
            _error.value = e.localizedMessage
        } finally {
            _loading.value = false
        }
    }

    fun filterByCategory(category: String) {
        val current = _resources.value ?: return
        _resources.value = if (category == "All") current else current.filter { it.category == category }
    }

    fun searchResources(query: String) {
        val current = _resources.value ?: return
        _resources.value = current.filter {
            it.title.contains(query, ignoreCase = true) ||
            it.description.contains(query, ignoreCase = true)
        }
    }
}
