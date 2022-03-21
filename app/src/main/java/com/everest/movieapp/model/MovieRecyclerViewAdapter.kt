package com.everest.movieapp.model



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

class MovieRecyclerViewAdapter(var dataModel: ArrayList<Result>): RecyclerView.Adapter<MovieRecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        val releaseDate:TextView= itemView.findViewById(R.id.release_date)
        val voteRate:TextView= itemView.findViewById(R.id.vote_average)
        val image:ImageView= itemView.findViewById(R.id.image_view)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/original/"+dataModel[position].poster_path)
            .into(holder.image)


        holder.title.text = dataModel[position].title
        holder.releaseDate.text= dataModel[position].release_date
        holder.voteRate.text= dataModel[position].vote_count.toString()

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,DetailsScreenActivity::class.java)
            intent.putExtra("dataset", dataModel[position])

            holder.itemView.context.startActivity(intent)

            Log.i("testIntent", "yess")
        }


    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

}

