package com.everest.movieapp.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.everest.movieapp.R
import com.everest.movieapp.data.model.UiMovieDetails

class MovieRecyclerViewAdapter(private var clickListener: CustomClick) :
    RecyclerView.Adapter<MovieRecyclerViewAdapter.MyViewHolder>() {
    private var fullMovieList = ArrayList<UiMovieDetails>()

    fun setMovies(newMovieList: ArrayList<UiMovieDetails>) {
        val diffUtil = com.everest.movieapp.utils.DiffUtil(fullMovieList, newMovieList)
        val result = DiffUtil.calculateDiff(diffUtil)
        fullMovieList.clear()
        fullMovieList = newMovieList
        result.dispatchUpdatesTo(this)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        val releaseDate: TextView = itemView.findViewById(R.id.release_date)
        val voteRate: TextView = itemView.findViewById(R.id.vote_average)
        val image: ImageView = itemView.findViewById(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(fullMovieList[position].posterPath)
            .into(holder.image)
        holder.title.text = fullMovieList[position].movieName
        holder.releaseDate.text = fullMovieList[position].releaseDate
        holder.voteRate.text = fullMovieList[position].voteRate.toString()


        holder.itemView.setOnClickListener {
            clickListener.onClick(position, holder.itemView.context, fullMovieList[position])
        }
    }


    override fun getItemCount(): Int {
        return fullMovieList.size
    }

    interface CustomClick {
        fun onClick(position: Int, context: Context, movieDetails: UiMovieDetails)
    }


}



