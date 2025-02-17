package com.s21.movietest.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.s21.movietest.databinding.ItemMovieBinding
import com.s21.movietest.presentation.models.MovieViewData

class MovieAdapter(
    private var movies: List<MovieViewData>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(movie: MovieViewData)
    }

    class MovieViewHolder(
        val binding: ItemMovieBinding,
        private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(movie: MovieViewData) {
            binding.filmName.text = movie.title
            binding.directorName.text = "director: " + movie.director
            binding.producerName.text = "producer: " + movie.producer
            binding.date.text = "release date: " + movie.release_date
            binding.root.setOnClickListener { onItemClickListener.onItemClick(movie) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovies(newMovies: List<MovieViewData>) {
        movies = newMovies.sortedBy { it.episode_id }
        notifyDataSetChanged()
    }
}