package com.example.jobsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jobsearch.model.Job
import com.example.jobsearch.repository.JobRepository

class JobViewModel : ViewModel() {
    private val repository = JobRepository()
    private val _jobs = MutableLiveData<List<Job>>()
    val jobs: LiveData<List<Job>> = _jobs

    init {
        loadJobs()
    }

    private fun loadJobs() {
        _jobs.value = repository.getJobs()
    }
}
