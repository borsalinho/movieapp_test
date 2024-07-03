package com.s21.movietest.presentation.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        try {
            lifecycleScope.launch {
                mainActivityViewModel.loadAllMovies()
            }
        } catch (e : Exception) {
            Toast.makeText(this, "Ошибка загрузkи данных", Toast.LENGTH_LONG).show()
        }

    }
}