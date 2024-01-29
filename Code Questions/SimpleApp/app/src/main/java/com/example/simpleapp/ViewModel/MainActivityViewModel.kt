package com.example.simpleapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleapp.Model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException

class MainViewModel : ViewModel() {

    // Endpoint url
    private val BASE_URL = "https://urvd7g56zh.execute-api.eu-west-2.amazonaws.com/dev"

    // Initialize OkHttpClient
    private val client = OkHttpClient()

    // LiveData to hold the list of items
    val itemsLiveData = MutableLiveData<List<Item>>()

    // LiveData for loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchData() {
        _isLoading.value = true // Set loading to true while fetching data

        // Create a coroutine to make a network request
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = makeApiRequest(BASE_URL)
                if (response != null && response.isSuccessful) {
                    val responseBody: ResponseBody? = response.body
                    val json = responseBody?.string()
                    val items = parseJson(json)

                    // Use withContext to update LiveData on the main thread
                    withContext(Dispatchers.Main) {
                        itemsLiveData.value = items
                        _isLoading.value = false
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                }
                e.printStackTrace()
            }
        }
    }

    private fun makeApiRequest(url: String): Response? {
        val request = Request.Builder()
            .url(url)
            .build()

        return client.newCall(request).execute()
    }

    private fun parseJson(json: String?): List<Item> {
        val itemsList = mutableListOf<Item>()

        try {
            if (!json.isNullOrBlank()) {
                val jsonArray = JSONArray(json)

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val id = jsonObject.getInt("id")
                    val name = jsonObject.getString("name")
                    val audioUrl = jsonObject.getString("audio_url")

                    val item = Item(id, name, audioUrl)
                    itemsList.add(item)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return itemsList
    }
}
