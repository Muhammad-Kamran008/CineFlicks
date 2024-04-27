package com.example.cineflicks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.cineflicks.data.DatabaseAdapter
import com.example.cineflicks.data.SimpleMovieDbHelper
import com.example.cineflicks.entity.SimpleMovieItem

import kotlinx.android.synthetic.main.activity_favourite_movies.*

class FavouriteMovies : AppCompatActivity() {
    private var arrayAdapter: DatabaseAdapter? = null
    private lateinit var sqliteHelper: SimpleMovieDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_movies)
        sqliteHelper = SimpleMovieDbHelper(this)
        getFavouritesMovie()
    }

    private fun getFavouritesMovie(){
        val favouriteList = sqliteHelper.getAllFavouriteMovies()
        arrayAdapter = DatabaseAdapter(this,R.layout.movieitem,favouriteList)
        favouriteListView.adapter = arrayAdapter

        favouriteListView.setOnItemClickListener { parent, view, position, id ->
            val movie: SimpleMovieItem = parent.getItemAtPosition(position) as SimpleMovieItem
            GoMovieDetail(movie)
        }
    }

    private fun GoMovieDetail(movie: SimpleMovieItem) {
        val intent = Intent(this, SimpleItemDetailActivity::class.java)
        intent.putExtra("movie_name", movie.title)
        intent.putExtra("release_date", movie.release_date)
        startActivity(intent)
    }


    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this@FavouriteMovies,SimpleViewListOfMoviesActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }
}