package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder

import com.example.android.trackmysleepquality.database.SleepNight



class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
   // taking the full list bof items in SleepNight
    var data = listOf<SleepNight>()
       // letting recycler view know when data change and redraw everything on screen
    set(value) {
           field = value
           notifyDataSetChanged()
       }



    // getItemCount() tells the recyclerView the number of items to recycle
    override fun getItemCount() = data.size

    //tells the RecyclerView how to bind/draw the data to views
    override fun onBindViewHolder(holder: TextItemViewHolder , position: Int) {
        // getting the item in the specified position
        val item = data[position]
        //getting the sleep quality icon to string and passing it to the holder
        holder.textView.text = item.sleepQuality.toString()

    }

    //onCreateViewHolder tells recyclerView how to create new view
    //viewType is used when there are multiple views in the recycler view
    //the ViewGroup is the Recyclerview which is the parent
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        // to inflate layout
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view,parent,false) as TextView
        return TextItemViewHolder(view)

    }

}