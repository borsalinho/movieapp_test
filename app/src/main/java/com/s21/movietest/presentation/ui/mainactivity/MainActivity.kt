package com.s21.movietest.presentation.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.s21.movietest.R
import com.s21.movietest.app.MyApp
import com.s21.movietest.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApp).myAppComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}