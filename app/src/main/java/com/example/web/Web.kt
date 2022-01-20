package com.example.web

import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Web(progressBar: ProgressBar, result:TextView): AsyncTask<URL, Int, String>() {
    private var result: String = "";
    private var resultView = result
    private var progressBar = progressBar

    override fun onPreExecute() {
        super.onPreExecute()
        progressBar.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg params: URL?): String {

        val connect = params[0]?.openConnection() as HttpURLConnection
        connect.readTimeout = 8000
        connect.connectTimeout = 8000
        connect.requestMethod = "GET"
        connect.connect();

        val responseCode: Int = connect.responseCode;
        if (responseCode == 200) {
            result = streamToString(connect.inputStream)
        }

        return result
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        progressBar.visibility = View.GONE
        resultView.text = result
    }
}

fun streamToString(inputStream: InputStream): String {

    val bufferReader = BufferedReader(InputStreamReader(inputStream))
    var line: String
    var result = ""

    try {
        do {
            line = bufferReader.readLine()
            if (line != null) {
                result += line
            }
        } while (line != null)
        inputStream.close()
    } catch (ex: Exception) {

    }
    return result
}