package com.yourcast.app.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yourcast.app.R
import com.yourcast.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}