package com.io.fizmat.singoltonbase

import com.io.fizmat.assynctask.GetListCurs
import com.io.fizmat.xlsreader.model.Curs

object BaseCurses {

    lateinit var listCurs : List<Curs>

    fun inization()
    {
        val task = GetListCurs().execute("http://www.brsu.by/sites/default/files/phys/07_1.xls")
        listCurs = task.get()
    }
}