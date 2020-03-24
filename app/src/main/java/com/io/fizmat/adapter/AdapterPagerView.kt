package com.io.fizmat.adapter

import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.io.fizmat.R
import com.io.fizmat.stringparse.DisplayString
import com.io.fizmat.worktotime.DayOfWeek
import com.io.fizmat.xlsreader.model.Day

class AdapterPagerView(val day: Day) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) return Item(LayoutInflater.from(parent.context).inflate(R.layout.itemrecycleview_textview, parent, false))
        else return Data(LayoutInflater.from(parent.context).inflate(R.layout.itemrecycleview_textviewdata, parent, false))
    }

    override fun getItemCount(): Int = day.timetable.size

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return 0
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Data)
            holder.textData(day.timetable[position])
        else if (holder is Item)
            holder.textUpdate(day.timetable[position])
    }

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val textview = itemView.findViewById<TextView>(R.id.occupation)

        fun textUpdate(s: String) {
            textview.text = DisplayString.fixString(s)
            corect(s)
        }

        private fun corect(s: String){
            if (s.length > 50) {
                if (s.length > 90) {
                    textview.textSize = 10f
                }
                else textview.textSize = 13f
            } else if (textview.textSize == 20f || textview.textSize == 26f) {
                if (s.length < 50) {
                    textview.textSize = 15f
                }
                else {
                    textview.textSize = 13f
                }
            }
        }
    }

    class Data(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        private val textview = itemView.findViewById<TextView>(R.id.occupation)

        fun textData(s: String) {
            textview.text = s
        }
    }
}