package com.iamauttamai.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iamauttamai.notification.databinding.ActivityMainBinding
import com.iamauttamai.notification.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}