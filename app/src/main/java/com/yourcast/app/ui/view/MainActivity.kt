package com.yourcast.app.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.yourcast.app.databinding.ActivityMainBinding
import com.yourcast.app.ui.viewModel.CurrentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // ViewBinding
    private lateinit var binding: ActivityMainBinding

    // ViewModel
    private val viewModel: CurrentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.result.observe(this, { response ->
            when(response.statusCode) {
                200 -> {
                    val name = response.data?.weather!![0].description
                    Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
                }
                else -> Toast.makeText(this, response.msg, Toast.LENGTH_SHORT).show()
            }
        })

        binding.edit.doOnTextChanged{ text, _, _, _ ->
            viewModel.city = text.toString()
        }

        binding.buttonSearch.setOnClickListener { viewModel.getCurrent() }
    }
}