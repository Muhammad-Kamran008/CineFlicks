package com.example.cineflicks

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cineflicks.R
import com.example.cineflicks.data.SimpleMovieDbHelper
import com.example.cineflicks.data.SimpleMovieSampleData
import com.example.cineflicks.entity.SimpleMovieItem
import kotlinx.android.synthetic.main.activity_simple_item_detail.*


class SimpleItemDetailActivity : AppCompatActivity() {
    private lateinit var sqliteHelper: SimpleMovieDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_item_detail)
        sqliteHelper = SimpleMovieDbHelper(this)
        val movie = fetchMovieItem()
        renderMovieDetails(movie)

    }


    private fun fetchMovieItem(): SimpleMovieItem {
        val requestedTitle = intent.getStringExtra("movie_name")
        return getMovieByTitle(requestedTitle)
    }

    private fun renderMovieDetails(movieItem: SimpleMovieItem) {
        with(movieItem) {
            text_movie_title.text = title
            text_movie_overview.text = overview
            text_movie_release_date.text = release_date
            text_movie_language.text = original_langauge
        }
    }

    private fun getMovieByTitle(title: String?): SimpleMovieItem {
        var fetchedOverview = ""
        var fetchedReleaseDate = ""
        var fetchedLanguage = ""

        SimpleMovieSampleData.simpleMovieitemArray.firstOrNull { it.title == title }?.let {
            fetchedOverview = it.overview.toString()
            fetchedReleaseDate = it.release_date.toString()
            fetchedLanguage = it.original_langauge.toString()
        }

        return SimpleMovieItem(
            overview = fetchedOverview,
            release_date = fetchedReleaseDate,
            original_langauge = fetchedLanguage,
            title = title
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent =
            Intent(this@SimpleItemDetailActivity, SimpleViewListOfMoviesActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favouritemenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add_favourite -> {
                val title = intent.getStringExtra("movie_name")
                val movieItem = getMovieByTitle(title)

                if (isMovieInFavorites(movieItem)) {
                    showCustomToast("This movie is already in your favorites!")
                } else {

                    val success = sqliteHelper.insertFavouriteMovie(movieItem)

                    if (success > 0) {
                        showCustomToast("Added to favorites!")
                    } else {
                        showCustomToast("Failed to add to favorites. Please try again.")
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isMovieInFavorites(movieItem: SimpleMovieItem): Boolean {
        val favoriteMovies = sqliteHelper.getAllFavouriteMovies()
        return favoriteMovies.any { it.title == movieItem.title }
    }


    private fun showCustomToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

