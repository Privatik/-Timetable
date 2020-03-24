package com.io.fizmat.view.dialogfragment

import com.io.fizmat.R

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.io.fizmat.adapter.AdapterTabDialog
import com.io.fizmat.navigation.Navigation
import com.io.fizmat.worktotime.DayOfWeek
import com.io.fizmat.xlsreader.model.Day

class FragmentTimetable: DialogFragment(), View.OnClickListener {

    lateinit var listDay: List<Day>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listDay = arguments!!.getSerializable("days") as List<Day>
        setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_timetable,container,false)
        dialog?.window!!.attributes.windowAnimations = R.style.DialogAnimation


        val back = view.findViewById<ImageButton>(R.id.back)
        val tab = view.findViewById<TabLayout>(R.id.days)
        val curs = view.findViewById<ImageButton>(R.id.curs)
        val viewpager = view.findViewById<ViewPager>(R.id.occupation)

        viewpager.adapter = AdapterTabDialog(childFragmentManager,listDay)
        tab.setupWithViewPager(viewpager)

        val days = arrayListOf("Пн","Вт","Ср","Чт","Пт","Сб")
        for (i in 0 until tab.tabCount)
        {
            val tabItem = tab.getTabAt(i)
            tabItem?.setCustomView(R.layout.tabitem)
            val textView = tabItem?.customView?.findViewById<TextView>(R.id.day)
            textView?.text = days.get(i)
            if (i == DayOfWeek.day)
            {
                textView?.textSize = 30f
                viewpager.currentItem = DayOfWeek.day
            }

        }

        initialTabLayout(tab)

        back.setOnClickListener(this)
        curs.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        Log.d("Click","Dialog")
        val id = v?.id
        if (id == R.id.back)
            dialog?.cancel()
        else if (id == R.id.curs)
        {
            dialog?.cancel()
            Navigation.showFragmentCurs()
        }
    }

    private fun initialTabLayout(tab: TabLayout)
    {
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabReselected(p0: TabLayout.Tab?) {
                Log.d("Tab","onTabReselected ${p0?.position}")

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                Log.d("Tab","onTabUnselected ${p0?.position}")
                textInit(true,p0)
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                Log.d("Tab","onTabSelected ${p0?.position}")
                textInit(false,p0)
            }

            private fun textInit(bool: Boolean,p0: TabLayout.Tab?)
            {
                val textView =  p0?.customView?.findViewById<TextView>(R.id.day)
               if (bool) textView?.textSize = 20f
               else textView?.textSize = 30f
            }
        })
    }
}