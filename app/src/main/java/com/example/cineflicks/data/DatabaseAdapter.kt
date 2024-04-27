package com.example.cineflicks.data

import android.content.Context


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.cineflicks.R
import com.example.cineflicks.entity.SimpleMovieItem


class DatabaseAdapter(
    context: Context,
    private val resource: Int = R.layout.movieitem,
    objects: MutableList<SimpleMovieItem>
):
    ArrayAdapter<SimpleMovieItem>(context,resource,objects){
    private val mContext: Context

    init {
        mContext = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val viewInflater: LayoutInflater = LayoutInflater.from(mContext)
            view = viewInflater.inflate(resource, null)
        }
        val movie: SimpleMovieItem? = getItem(position)

        if (movie != null) {
            val name: TextView = view!!.findViewById(R.id.movie_name)
            val releaseDate: TextView = view.findViewById(R.id.release_date)

            name.text = movie.title
            releaseDate.text = movie.release_date

        }
        return view!!
    }

}

