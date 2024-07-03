package com.s21.movietest.presentation.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.s21.movietest.R
import com.s21.movietest.app.MyApp
import com.s21.movietest.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApp).myAppComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadAllMovies()

    }

    private fun loadAllMovies() {
        mainActivityViewModel.loadAllMovies()
    }

    private fun checkErrors(){
        mainActivityViewModel.error.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                mainActivityViewModel.onErrorShown()
            }
        })
    }
}