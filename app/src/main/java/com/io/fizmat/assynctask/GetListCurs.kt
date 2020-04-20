package com.io.fizmat.assynctask

import android.os.AsyncTask
import com.io.fizmat.xlsreader.XLSReaderBRSU
import com.io.fizmat.xlsreader.model.Curs
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL


class GetListCurs : AsyncTask<Void,Void,List<Curs>>(){

    override fun doInBackground(vararg params: Void?): List<Curs>? {

        try {
            val url =
                URL("https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1-j9vzxQP6WOr4eQS74Iu60aHnqb5yyaF")
            var inputStream = url.openStream()
            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"), 8)

            val str = reader.readLine()

            reader.close()
            inputStream = URL(str).openStream()
            val xlsReaderBRSU = XLSReaderBRSU.newInstance(inputStream)

            inputStream.close()
            return xlsReaderBRSU.readXLS()
        } catch (e: KotlinNullPointerException) {
            return arrayListOf()
        }
    }

}