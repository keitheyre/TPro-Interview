package com.tpro.brokenapp.tpro.brokenapp.tpro.broeknapp

import android.app.*
import android.os.*
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tpro.brokenapp.R
import com.tpro.brokenapp.tpro.brokenapp.tpro.broeknapp.ViewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.num_1_et
import kotlinx.android.synthetic.main.activity_main.num_2_et
import kotlinx.android.synthetic.main.activity_main.result_tv

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup ViewModel
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // Find the button using findViewById
        val button = findViewById<Button>(R.id.add_nums_bt)

        // Set onClick listener to the button
        button.setOnClickListener {
            // Get user input from EditText fields
            val input1 = num_1_et.text.toString()
            val input2 = num_2_et.text.toString()

            // Call ViewModel methods to handle input and calculate result
            viewModel.setInputText1(input1)
            viewModel.setInputText2(input2)
            viewModel.calculateSum()
        }

        // Observe the resultText LiveData and update the UI when it changes
        viewModel.resultText.observe(this, Observer { newData ->
            result_tv.text = newData
        })
    }
}
