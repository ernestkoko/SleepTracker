package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString

import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding


class SleepNightAdapter(val clickListerner: SleepNightListener): ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {


    //tells the RecyclerView how to bind/draw the data to views
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // val item = getItem(position)
        holder.bind( clickListerner, getItem(position)!!)

    }



    //onCreateViewHolder tells recyclerView how to create new view
    //viewType is used when there are multiple views in the recycler view
    //the ViewGroup is the Recyclerview which is the parent
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // to inflate layout
        return ViewHolder.from(parent)

    }

    //new ViewHolder we want to use
    class ViewHolder private constructor(val binding: ListItemSleepNightBinding):
            RecyclerView.ViewHolder(binding.root){

        // details of how to update views
       fun bind(clickListener: SleepNightListener, item: SleepNight) {
            binding.sleep = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
         // methods in companion object can be called without the instance of the class they are in
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding.inflate(layoutInflater,parent, false)
                return ViewHolder(binding)
            }
        }


   }

        // new class that will tell recyclerview something has changed
    class SleepNightDiffCallback: DiffUtil.ItemCallback<SleepNight>(){
            //this checks if the items(e.g id) are the same
            override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
                return oldItem.nightId == newItem.nightId
            }

            //this checks if the contents of old and new items are same
            override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
                return oldItem == newItem
            }

        }

     //listener class for the recycler view items
    class SleepNightListener(val clickListener: (sleepId: Long) -> Unit){
         fun onClick(night: SleepNight) = clickListener(night.nightId)
     }

}