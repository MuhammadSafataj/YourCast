package com.yourcast.app.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yourcast.app.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    // ViewBinding
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}