package com.example.cineflicks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent

import android.view.Menu
import android.view.MenuItem
import com.example.cineflicks.data.DatabaseAdapter
import com.example.cineflicks.data.SimpleMovieSampleData
import com.example.cineflicks.entity.SimpleMovieItem

import kotlinx.android.synthetic.main.activity_simple_view_list_of_movies.movielist

class SimpleViewListOfMoviesActivity : AppCompatActivity() {
    private var arrayAdapter: DatabaseAdapter? = null
    private val moviesWithDetails:ArrayList<SimpleMovieItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_view_list_of_movies)
        getMoviesWithDetails()
        arrayAdapter = DatabaseAdapter(this,R.layout.movieitem,moviesWithDetails)
        movielist.adapter = arrayAdapter
        movielist.setOnItemClickListener { parent, view, position, id ->
            val movie:SimpleMovieItem = parent.getItemAtPosition(position) as SimpleMovieItem
            GoMovieDetail(movie)
        }
    }



    private fun GoMovieDetail(movie: SimpleMovieItem) {
        val intent = Intent(this, SimpleItemDetailActivity::class.java)
        intent.putExtra("movie_name", movie.title)
        intent.putExtra("release_date", movie.release_date)
        startActivity(intent)
    }

    private fun getMoviesWithDetails() {
        for (movie in SimpleMovieSampleData.simpleMovieitemArray) {
            val name = movie.title.toString()
            val releaseDate = movie.release_date.toString()

            moviesWithDetails.add(
                SimpleMovieItem(
                    null,
                    releaseDate,
                    null,
                    name
                )
            )
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movieslistactivitymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miFavourite->{
                val intent = Intent(this@SimpleViewListOfMoviesActivity,FavouriteMovies::class.java)
                startActivity(intent)
            }
            R.id.miLogout->{
                val intent = Intent(this@SimpleViewListOfMoviesActivity,Login::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}