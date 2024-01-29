package com.example.simpleapp.Activity

import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.simpleapp.Adapter.ItemAdapter
import com.example.simpleapp.R
import com.example.simpleapp.Service.MediaPlayerManager
import com.example.simpleapp.ViewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var listView: ListView
    private lateinit var adapter: ItemAdapter
    private lateinit var progressBar: ProgressBar

    private lateinit var mediaPlayerManager: MediaPlayerManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Initialise the media player
        mediaPlayerManager = MediaPlayerManager(this)


        // Initialize ListView and Adapter and Progress Bar
        listView = findViewById(R.id.listView)
        adapter = ItemAdapter(this, emptyList(), mediaPlayerManager)
        progressBar = findViewById(R.id.loadingProgressBar)
        // Set adapter to the ListView
        listView.adapter = adapter

        // Observe isLoading to show/ hide the progress bar
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE // Show progress
            } else {
                progressBar.visibility = View.GONE // Hide progress
            }
        }

        // Observe itemsLiveData and update the adapter when data changes
        viewModel.itemsLiveData.observe(this) { items ->
            adapter = ItemAdapter(this, items, mediaPlayerManager)
            listView.adapter = adapter
            listView.visibility = View.VISIBLE
        }

        // Fetch data from ViewModel
        viewModel.fetchData()
    }

    override fun onDestroy() {
        super.onDestroy()

        // Release the Media Player resource
        mediaPlayerManager.release()
    }

    override fun onStop() {
        super.onStop()
        // Release the Media Player resource
        mediaPlayerManager.release()
    }
}