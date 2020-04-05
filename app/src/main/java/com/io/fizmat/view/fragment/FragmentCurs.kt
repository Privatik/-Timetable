package com.io.fizmat.view.fragment

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.io.fizmat.adapter.AdapterCurs
import com.io.fizmat.singoltonbase.BaseCurses
import com.io.fizmat.util.UtilToast
import com.io.fizmat.xlsreader.model.Curs

class FragmentCurs : FragmentMain() {

    lateinit var listCurs : List<Curs>

    override fun workRecycltView(recyclerView: RecyclerView?) {
        recyclerView?.adapter = AdapterCurs(listCurs)
        super.workRecycltView(recyclerView)
    }

    override fun daggerInit(arguments: Bundle?) {
        if (!BaseCurses.isListLoaded)
        {
            while (true)
            {
                if (BaseCurses.isListLoaded)
                    break
            }
            UtilToast.show("Офлайн версия")
        }
        listCurs = BaseCurses.listCurs
    }
}