package com.io.fizmat.singoltonbase

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.io.fizmat.singoltonbase.savebaseroom.dao.CursDao
import com.io.fizmat.singoltonbase.savebaseroom.dao.GroupDao
import com.io.fizmat.singoltonbase.savebaseroom.database.TimetableBase
import com.io.fizmat.xlsreader.model.Curs
import kotlinx.coroutines.*

class SQLbase(val context: Context) {
    private lateinit var cursDao: CursDao
    private lateinit var groupDao: GroupDao

    init {
     baseCreate()
    }

    private fun baseCreate()
    {
        val base = Room.databaseBuilder(context, TimetableBase::class.java,"basee").build()
        cursDao = base.cursDao()
        groupDao = base.groupDao()
    }

    fun putBaseCurs()
    {
        GlobalScope.launch {
            val firstStart = FirstStart(context)
            if (firstStart.getFirstStart())
            {
                insertBase()
                firstStart.setFirstStart()
            }
            else
            {
                updateBase()
            }
        }
    }

    private fun insertBase()
    {
        BaseCurses.listCurs.forEach{
            Log.d("BaseReader",it.cursTitle)
            cursDao.insert(it)
            it.groupList.forEach { group ->
                Log.d("BaseReader"," ${group.nameGroup}")
                groupDao.insert(group)
            }
        }
    }

    private fun updateBase()
    {
        val listCurs = cursDao.listCurs as ArrayList<Curs>

        listCurs.forEach {
            it.groupList = groupDao.getListGroup(it.cursTitle)

            it.groupList.forEach {group ->
                groupDao.delete(group)
            }

            cursDao.delete(it)
        }

        insertBase()
    }


    fun getAllListCurs(): Job = GlobalScope.launch {
        val listCurs = cursDao.listCurs as ArrayList<Curs>

        listCurs.forEach {
            Log.d("BaseReaderList", it.cursTitle)
            it.groupList = groupDao.getListGroup(it.cursTitle)
            it.groupList.forEach {group ->
                Log.d("BaseReaderList", "${group.nameGroup} ${group.curcTitle}")
            }
        }

        BaseCurses.listCurs = listCurs
        BaseCurses.isListLoaded = true
    }
}