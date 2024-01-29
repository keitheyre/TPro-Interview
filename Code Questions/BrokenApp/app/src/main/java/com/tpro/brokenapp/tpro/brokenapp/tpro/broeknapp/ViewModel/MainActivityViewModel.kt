package com.tpro.brokenapp.tpro.brokenapp.tpro.broeknapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _inputText1 = MutableLiveData<String>()
    val inputText1: LiveData<String> = _inputText1

    private val _inputText2 = MutableLiveData<String>()
    val inputText2: LiveData<String> = _inputText2

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> = _resultText

    fun setInputText1(text: String) {
        _inputText1.value = text
    }

    fun setInputText2(text: String) {
        _inputText2.value = text
    }

    fun calculateSum() {
        val text1 = _inputText1.value ?: ""
        val text2 = _inputText2.value ?: ""

        // Check for empty inputs
        if (text1.isEmpty() || text2.isEmpty()) {
            _resultText.value = "Please enter both numbers"
        } else {
            try {
                val num1 = text1.toInt()
                val num2 = text2.toInt()
                val sum = num1 + num2
                _resultText.value = "$sum"
                print(_resultText)
            } catch (e: NumberFormatException) {
                _resultText.value = "Invalid input"
            }
        }

        Log.d("KEITHEYRE", "result is: " + _resultText.value)
    }
}
