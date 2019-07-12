package com.example.tictactoe.viewPager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tictactoe.R

class WelcomeAdapter(activity: ViewPagerActivity, private val list: List<String>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<WelcomeAdapter.MyViewHolder>() {
    private val layoutInflater: LayoutInflater

    private val colourArray = intArrayOf(
        android.R.color.black,
        android.R.color.holo_blue_dark,
        android.R.color.holo_green_dark,
        android.R.color.holo_red_dark
    )


    init {

        this.layoutInflater = LayoutInflater.from(activity)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        //        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_welcome,parent,false);

        val view = layoutInflater.inflate(R.layout.item_welcome, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvTitleAdapter.text = list[position]
        holder.llParent.setBackgroundColor(colourArray[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val tvTitleAdapter: TextView
        private val btnToggleAdapter: Button
        val llParent: LinearLayout

        init {

            tvTitleAdapter = itemView.findViewById(R.id.tvTitleAdapter)
            btnToggleAdapter = itemView.findViewById(R.id.btnToggleAdapter)
            llParent = itemView.findViewById(R.id.llParent)

            btnToggleAdapter.setOnClickListener(this)
        }

        override fun onClick(v: View) {

            if (viewPager2.getOrientation() === ViewPager2.ORIENTATION_HORIZONTAL) {
                viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL)
            } else {
                viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)

            }

        }
    }
}
