package com.example.simpleapp.Service

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import java.io.IOException

class MediaPlayerManager(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false // Indicates whether audio is currently playing

    // Initialize the MediaPlayer and set audio attributes
    init {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
    }

    // Start playing audio from the specified URL
    fun playAudio(audioUrl: String) {
        mediaPlayer?.apply {
            try {
                reset() // Reset the MediaPlayer
                setDataSource(context, Uri.parse(audioUrl)) // Set the data source to the audio URL
                prepareAsync() // Prepare the MediaPlayer asynchronously

                // Set a callback for when the MediaPlayer is prepared
                setOnPreparedListener {
                    start() // Start playing the audio
                    this@MediaPlayerManager.isPlaying = true // Set isPlaying to true
                }

                // Set a callback for when audio playback completes
                setOnCompletionListener {
                    this@MediaPlayerManager.isPlaying = false // Set isPlaying to false
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    // Pause the currently playing audio
    fun pauseAudio() {
        mediaPlayer?.apply {
            if (isPlaying) {
                pause() // Pause the audio
                this@MediaPlayerManager.isPlaying = false // Set isPlaying to false
            }
        }
    }

    // Release the MediaPlayer resources
    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
