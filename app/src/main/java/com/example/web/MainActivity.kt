package com.example.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = URL("https://api.github.com/search/users?q=ZooShow")
        val progressBar:ProgressBar = findViewById(R.id.progressBar)
        val button:Button = findViewById(R.id.startUrl)
        val result:TextView = findViewById(R.id.result)
        val web = Web(progressBar, result)
        button.setOnClickListener {
            web.execute(url)
        }

    }
}