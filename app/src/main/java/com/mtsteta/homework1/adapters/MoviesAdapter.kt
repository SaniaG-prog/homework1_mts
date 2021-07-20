package com.mtsteta.homework1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mtsteta.homework1.listeners.MovieItemClickListener
import com.mtsteta.homework1.R
import com.mtsteta.homework1.dto.MovieDto

class MoviesAdapter(private val listener: MovieItemClickListener):
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private var movies: List<MovieDto> = emptyList()

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)
        private val name: TextView = itemView.findViewById(R.id.item_movie_name)
        private val description: TextView = itemView.findViewById(R.id.item_movie_description)
        private val ratingBar: RatingBar = itemView.findViewById(R.id.item_movie_rating_bar)
        private val age: TextView = itemView.findViewById(R.id.item_movie_age)

        fun bind(movie: MovieDto) {
            poster.load(movie.imageUrl)
            name.text = movie.title
            description.text = movie.description
            ratingBar.rating = movie.rateScore.toFloat()
            age.text = movie.ageRestriction.toString() + "+"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))
        holder.itemView.setOnClickListener {
            listener.onMovieClick(movies.get(position).title)
        }
    }

    fun setData(newMovies: List<MovieDto>) {
        movies = newMovies
    }

    fun getData(): List<MovieDto> {
        return movies
    }
}