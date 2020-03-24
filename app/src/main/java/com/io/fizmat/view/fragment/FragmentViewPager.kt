package com.io.fizmat.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.io.fizmat.R
import com.io.fizmat.adapter.AdapterPagerView
import com.io.fizmat.worktotime.DayOfWeek
import com.io.fizmat.xlsreader.model.Day

class FragmentViewPager(val day: Day,val dayOfWeek: Int) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pagerview,container,false)

        val recycleView = view.findViewById<RecyclerView>(R.id.recycleview)
        recycleView.adapter = AdapterPagerView(day)
        recycleView.layoutManager = LinearLayoutManager(activity?.baseContext,LinearLayoutManager.VERTICAL, false)
        return view

    }

}