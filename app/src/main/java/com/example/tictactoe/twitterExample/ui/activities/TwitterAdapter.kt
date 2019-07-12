package com.example.tictactoe.twitterExample.ui.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.item_twitter.view.*
import twitter4j.Status

class TwitterAdapter(listOfStatus: MutableList<Status>) : RecyclerView.Adapter<TwitterAdapter.MyViewHolder>() {

    lateinit var listOfStatus :MutableList<Status>
    init {
        this.listOfStatus = listOfStatus
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_twitter,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfStatus.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvTitleTwitter.text = listOfStatus.get(position).text
        holder.tvDescriptionTwitter.text = listOfStatus.get(position).user.description
        holder.reTweetCount.text = listOfStatus.get(position).retweetCount.toString()
        holder.likesCount.text = listOfStatus.get(position).favoriteCount.toString()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitleTwitter = itemView.tvTitleTwitter
        val tvDescriptionTwitter = itemView.tvDescriptionTwitter
        val reTweetCount = itemView.tvRetweets
        val likesCount = itemView.tvLikesTwitter

    }

}
