package com.example.aph4.feature_home.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aph4.R
import com.example.aph4.feature_home.data.BoardData
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class BoardListActivity : AppCompatActivity() {
    private val datalist = ArrayList<BoardData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_list)

        val listView = findViewById<ListView>(R.id.listview_board)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("http://39.118.94.53/boardlist.php")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                connection.disconnect()

                val gson = GsonBuilder().create()
                val type = object : TypeToken<Array<BoardData>>() {}.type
                val datalist2: Array<BoardData> = gson.fromJson(response, type)

                for (i in datalist2.indices) {
                    datalist.add(datalist2[i])
                }

                runOnUiThread {
                    val adapter = android.widget.ArrayAdapter(
                        this@BoardListActivity,
                        android.R.layout.simple_list_item_1,
                        datalist.map { it.title }
                    )
                    listView.adapter = adapter

                    // 리스트 클릭 시 상세로 이동
                    listView.setOnItemClickListener { _, _, position, _ ->
                        val intent = Intent(this@BoardListActivity, BoardDetailActivity::class.java)
                        intent.putExtra("pkey", datalist[position].pkey)
                        startActivity(intent)
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@BoardListActivity, "네트워크 오류", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
