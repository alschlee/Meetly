package com.example.aph4.feature_home.presentation

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aph4.R
import com.example.aph4.feature_home.data.MyInfoData
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class MyInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        val tvMyinfo = findViewById<TextView>(R.id.tv_myinfo)

        val prefs = getSharedPreferences("user_info", MODE_PRIVATE)
        val pkey = prefs.getString("pkey", null) ?: run {
            tvMyinfo.text = "로그인 정보 없음"
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("http://39.118.94.53/myinfo.php")
                val params = "pkey=$pkey"
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.doOutput = true
                OutputStreamWriter(connection.outputStream).use { writer ->
                    writer.write(params)
                }
                val response = connection.inputStream.bufferedReader().use { it.readText() }.trim()
                connection.disconnect()

                runOnUiThread {
                    if (response == "0") {
                        tvMyinfo.text = "내 정보 불러오기 실패"
                    } else {
                        // JSON 파싱
                        val gson = GsonBuilder().create()
                        val myInfo = gson.fromJson(response, MyInfoData::class.java)
                        tvMyinfo.text = "아이디: ${myInfo.id}\n이름: ${myInfo.name}\n생년월일: ${myInfo.birth}"
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    tvMyinfo.text = "네트워크 오류"
                }
            }
        }
    }
}
