package com.io.fizmat.singoltonbase

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.room.Room
import com.io.fizmat.assynctask.GetListCurs
import com.io.fizmat.singoltonbase.savebaseroom.dao.CursDao
import com.io.fizmat.singoltonbase.savebaseroom.dao.GroupDao
import com.io.fizmat.singoltonbase.savebaseroom.database.TimetableBase
import com.io.fizmat.util.UtilToast
import kotlinx.coroutines.*
import com.io.fizmat.xlsreader.model.Curs
import kotlin.coroutines.suspendCoroutine

object BaseCurses {

    lateinit var listCurs : List<Curs>
    var isListLoaded = true

    fun inization(context: Context)
    {
        try {
            val baseSQL = SQLbase(context)
            if (isConnect(context)) {
                val task = GetListCurs().execute()
                listCurs = task.get()
                baseSQL.putBaseCurs()
            } else {
                isListLoaded = false
                listCurs = arrayListOf()
                baseSQL.getAllListCurs()
            }
        }catch (e:Exception)
        {
            Log.d("BaseReader",e.message)
        }
    }


    private fun isConnect(context: Context):Boolean
    {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }
}