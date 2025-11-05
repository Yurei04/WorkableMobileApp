package com.example.jobsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Model for a single blog post
data class BlogPost(
    val id: Int,
    val title: String,
    val author: String,
    val date: String,
    val content: String,
    val imageUrl: String
)

class BlogViewModel : ViewModel() {

    private val _blogs = MutableLiveData<List<BlogPost>>()
    val blogs: LiveData<List<BlogPost>> = _blogs

    private val _selectedBlog = MutableLiveData<BlogPost?>()
    val selectedBlog: LiveData<BlogPost?> = _selectedBlog

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadBlogs()
    }

    private fun loadBlogs() {
        _loading.value = true
        try {
            // Dummy blog list — replace with repository or Firebase fetch
            val sampleBlogs = listOf(
                BlogPost(
                    id = 1,
                    title = "Top 5 Resume Mistakes to Avoid",
                    author = "CareerCoach",
                    date = "Oct 10, 2025",
                    content = "Your resume is your first impression. Here are the top mistakes candidates make and how to fix them...",
                    imageUrl = "https://example.com/images/resume_tips.jpg"
                ),
                BlogPost(
                    id = 2,
                    title = "Mastering Remote Interviews",
                    author = "HR Insider",
                    date = "Oct 21, 2025",
                    content = "Remote interviews are here to stay. Learn how to present yourself effectively online...",
                    imageUrl = "https://example.com/images/remote_interview.jpg"
                ),
                BlogPost(
                    id = 3,
                    title = "10 Skills Every Developer Needs in 2025",
                    author = "TechToday",
                    date = "Nov 1, 2025",
                    content = "The tech world is evolving quickly. Here are the must-have skills to stay competitive...",
                    imageUrl = "https://example.com/images/dev_skills.jpg"
                )
            )

            _blogs.value = sampleBlogs
            _error.value = null

        } catch (e: Exception) {
            _error.value = e.localizedMessage
        } finally {
            _loading.value = false
        }
    }

    fun selectBlog(blog: BlogPost) {
        _selectedBlog.value = blog
    }

    fun clearSelection() {
        _selectedBlog.value = null
    }

    fun searchBlogs(query: String) {
        val current = _blogs.value ?: return
        _blogs.value = current.filter {
            it.title.contains(query, ignoreCase = true) ||
            it.content.contains(query, ignoreCase = true)
        }
    }
}
