package com.s21.movietest.presentation.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.s21.movietest.R
import com.s21.movietest.app.MyApp
import com.s21.movietest.databinding.ActivityMainBinding
import com.s21.movietest.presentation.adapters.MovieAdapter
import com.s21.movietest.presentation.models.CharactersViewData
import com.s21.movietest.presentation.models.MovieViewData
import com.s21.movietest.presentation.ui.fragments.PersonsFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    @Inject lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApp).myAppComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadAllMovies()
        observeViewModel()
    }

    private fun loadAllMovies() {
        mainActivityViewModel.getAllMovies()
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter(emptyList(), object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(movie: MovieViewData) {
                val myFragment = PersonsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, myFragment)
                    .commit()
                mainActivityViewModel.setPersonsList(CharactersViewData(movie.characters))
            }
        })
        binding.movieAdapter.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }
    }

    private fun observeViewModel() {
        mainActivityViewModel.movies.observe(this, Observer { movies ->
            movieAdapter.updateMovies(movies)
        })

        mainActivityViewModel.error.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                mainActivityViewModel.onErrorShown()
            }
        })
    }
}