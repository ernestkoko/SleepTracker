package com.example.android.trackmysleepquality.sleeptracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString

import com.example.android.trackmysleepquality.database.SleepNight



class SleepNightAdapter: RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {
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
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.bind(item)
    }



    //onCreateViewHolder tells recyclerView how to create new view
    //viewType is used when there are multiple views in the recycler view
    //the ViewGroup is the Recyclerview which is the parent
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // to inflate layout
        return ViewHolder.from(parent)

    }

    //new ViewHolder we want to use
    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){
       val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
       val quality: TextView = itemView.findViewById(R.id.quality_string)
       val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

       // details of how to update views
       fun bind(item: SleepNight) {
           val res = itemView.context.resources
           sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
           quality.text = convertNumericQualityToString(item.sleepQuality, res)
           qualityImage.setImageResource(when (item.sleepQuality) {
               0 -> R.drawable.ic_sleep_0
               1 -> R.drawable.ic_sleep_1
               2 -> R.drawable.ic_sleep_2
               3 -> R.drawable.ic_sleep_3
               4 -> R.drawable.ic_sleep_4
               5 -> R.drawable.ic_sleep_5
               else -> R.drawable.ic_sleep_active
           })
       }
         // methods in companion object can be called without the instance of the class they are in
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
                return ViewHolder(view)
            }
        }


   }



}