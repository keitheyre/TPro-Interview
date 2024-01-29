package com.example.simpleapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.simpleapp.Model.Item
import com.example.simpleapp.R
import com.example.simpleapp.Service.MediaPlayerManager

class ItemAdapter(
    private val context: Context,
    private val items: List<Item>,
    private val mediaPlayerManager: MediaPlayerManager
) : BaseAdapter() {

    // Define the number of items in the adapter
    override fun getCount(): Int = items.size

    // Get the item at a specific position
    override fun getItem(position: Int): Item = items[position]

    // Get the item's unique identifier
    override fun getItemId(position: Int): Long = position.toLong()

    // Create a view for each item in the adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Get the item at the current position
        val item = getItem(position)

        // Create a new view or reuse an existing one
        val view: View =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        // Find the TextView for displaying item name
        view.findViewById<TextView>(R.id.itemNameTextView).text = item.name

        // Find the Button for play/ pause functionality
        val playButton = view.findViewById<Button>(R.id.playPauseButton)

        // Set the text of the play button based on the isPlaying property
        playButton.text = context.getString(if (item.isPlaying) R.string.pause else R.string.play)

        // Define the click listener for the play button
        playButton.setOnClickListener {
            // Handle play/pause button click here
            if (item.isPlaying) {
                // Pause the audio
                mediaPlayerManager.pauseAudio()
                item.isPlaying = false
            } else {
                // Stop the previous item's playback (if any)
                stopPreviousItemPlayback()

                // Start playing the audio for the current item
                mediaPlayerManager.playAudio(item.audioUrl)
                item.isPlaying = true
            }

            // Notify the adapter that the data has changed
            notifyDataSetChanged()
        }

        // Return the populated view
        return view
    }

    // Add a method to stop the playback of the previous item if they are playing
    private fun stopPreviousItemPlayback() {
        for (item in items) {
            if (item.isPlaying) {
                mediaPlayerManager.pauseAudio()
                item.isPlaying = false
            }
        }
    }
}
