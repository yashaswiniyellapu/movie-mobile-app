package com.everest.movieapp.ui.adapters

/**
 * Recycler view adapter sets the data in view.
 */

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.everest.movieapp.DetailsScreenActivity
import com.everest.movieapp.R
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.utils.constants.Constants.Companion.IMAGE_BASE_URL

class MovieRecyclerViewAdapter(var dataModel: List<Result>) :
    RecyclerView.Adapter<MovieRecyclerViewAdapter.MyViewHolder>() {

    private var fullMovieList = ArrayList<Result>(dataModel)

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

       fullMovieList[position].poster_path = IMAGE_BASE_URL + fullMovieList[position].poster_path
        Glide.with(holder.itemView.context)
            .load(fullMovieList[position].poster_path)
            .into(holder.image)
Log.i("imageTest",fullMovieList[position].poster_path.toString())

        holder.title.text = fullMovieList[position].title
        holder.releaseDate.text = fullMovieList[position].release_date
        holder.voteRate.text = fullMovieList[position].vote_count.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailsScreenActivity::class.java)
            intent.putExtra("dataset", fullMovieList[position])

            holder.itemView.context.startActivity(intent)

            Log.i("testIntent", "yess")
        }


    }

    override fun getItemCount(): Int {   //gets the item count
        return fullMovieList.size
    }


}



