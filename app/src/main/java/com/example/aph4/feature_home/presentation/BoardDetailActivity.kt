package com.example.aph4.feature_home.presentation

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aph4.R
import com.example.aph4.feature_home.data.BoardData
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class BoardDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)

        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val tvContent = findViewById<TextView>(R.id.tv_content)
        val tvWriter = findViewById<TextView>(R.id.tv_writer)
        val tvDate = findViewById<TextView>(R.id.tv_date)
        val tvViewCount = findViewById<TextView>(R.id.tv_viewcount)

        val pkey = intent.getStringExtra("pkey") ?: return

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("http://39.118.94.53/boarddetail.php?pkey=$pkey")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                connection.disconnect()

                val gson = GsonBuilder().create()
                val board: BoardData = gson.fromJson(response, BoardData::class.java)

                runOnUiThread {
                    tvTitle.text = board.title
                    tvContent.text = board.content
                    tvWriter.text = board.username
                    tvDate.text = board.insertdate
                    tvViewCount.text = board.viewcount
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@BoardDetailActivity, "네트워크 오류", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
