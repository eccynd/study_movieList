package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val movieList: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

        class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val rank: TextView = view.findViewById(R.id.tvRank)
            val title: TextView = view.findViewById(R.id.tvTitle)
            val date: TextView = view.findViewById(R.id.tvDate)
            val sales: TextView = view.findViewById(R.id.tvSales)
            val audience: TextView = view.findViewById(R.id.tvAudience)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.rank.text = "순위: ${movie.rank}"
        holder.title.text = movie.movieNm
        holder.date.text = "개봉일: ${movie.openDt}"
        holder.sales.text = "매출: ${movie.salesAmt} 원"
        holder.audience.text = "관객 수: ${movie.audiCnt} 명"
    }

    override fun getItemCount(): Int = movieList.size
}