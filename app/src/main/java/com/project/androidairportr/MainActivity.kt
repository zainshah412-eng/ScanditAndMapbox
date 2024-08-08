package com.project.androidairportr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.androidairportr.databinding.ActivityMainBinding
import com.project.androidairportr.mapbox.MapNavigationActivity
import com.project.androidairportr.models.NavigationModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mapBox.setOnClickListener {
            val model = NavigationModel(
                32.16069370847263,
                74.1905686399553,
                32.1578168802837,
                74.19549580926716,
                0
            )


            val intent = Intent(this, MapNavigationActivity::class.java)
            intent.putExtra("navigation_model", model)
            startActivity(intent)
        }
        binding.scanner.setOnClickListener {
            val intent = Intent(this, IdCaptureActivity2::class.java)
            startActivity(intent)
        }
    }
}