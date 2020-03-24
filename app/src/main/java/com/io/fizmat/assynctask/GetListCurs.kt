package com.io.fizmat.assynctask

import android.os.AsyncTask
import com.io.fizmat.xlsreader.XLSReaderBRSU
import com.io.fizmat.xlsreader.model.Curs
import java.net.URL

class GetListCurs : AsyncTask<String,Void,List<Curs>>(){

    override fun doInBackground(vararg params: String?): List<Curs>? {
        val xlsReaderBRSU = XLSReaderBRSU.newInstance(URL(params[0]).openStream())
        val list = xlsReaderBRSU.readXLS()
        if (list != null)
            return xlsReaderBRSU.readXLS()
        else
            return listOf()
    }
}