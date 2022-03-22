package com.everest.movieapp.adapters

/**
 * Recycler view adapter sets the data in view.
 */

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.everest.movieapp.DetailsScreenActivity
import com.everest.movieapp.R
import com.everest.movieapp.model.MovieDb
import com.everest.movieapp.model.Result

class MovieRecyclerViewAdapter(var dataModel: MovieDb) :
    RecyclerView.Adapter<MovieRecyclerViewAdapter.MyViewHolder>(), Filterable {

    private var fullMovieList = ArrayList<Result>(dataModel.results)

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
            .load("https://image.tmdb.org/t/p/original/" + fullMovieList[position].poster_path)
            .into(holder.image)


        holder.title.text = fullMovieList[position].title
        holder.releaseDate.text = fullMovieList[position].release_date
        holder.voteRate.text = fullMovieList[position].vote_count.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailsScreenActivity::class.java)
            intent.putExtra("dataset", fullMovieList.get(position))

            holder.itemView.context.startActivity(intent)

            Log.i("testIntent", "yess")
        }


    }

    override fun getItemCount(): Int {   //gets the item count
        return fullMovieList.size
    }

    override fun getFilter(): Filter {     //filters the users input while searching with charSequence
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                if (charSequence.toString().isEmpty()) {
                    fullMovieList.addAll(dataModel.results)
                } else {
                    val filteredList = ArrayList<Result>()
                    dataModel.results.filter {
                        it.title.lowercase().contains(charSequence.toString().lowercase())
                    }.forEach { filteredList.add(it) }
                    fullMovieList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = fullMovieList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults?
            ) {
                fullMovieList = filterResults?.values as ArrayList<Result>
                notifyDataSetChanged()
            }
        }

    }
}



