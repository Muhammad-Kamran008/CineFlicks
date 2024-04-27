package com.example.cineflicks

import android.app.Application
import com.example.cineflicks.data.SimpleMovieSampleData
import com.example.cineflicks.entity.SimpleMovieItem

class MovieViewerApplication : Application(){



    companion object {

        var movieItemArrays: ArrayList<SimpleMovieItem>?=null
        val appInstance = MovieViewerApplication()

    }

    init {
        movieItemArrays = SimpleMovieSampleData.simpleMovieitemArray

    }


}