package com.example.aph4.feature_auth.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aph4.MainActivity
import com.example.aph4.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etId = findViewById<EditText>(R.id.et_id)
        val etPwd = findViewById<EditText>(R.id.et_pwd)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnSignup = findViewById<Button>(R.id.btn_signup)

        btnLogin.setOnClickListener {
            val id = etId.text.toString()
            val pwd = etPwd.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val url = URL("http://39.118.94.53/login.php")
                    val postData = "id=${URLEncoder.encode(id, "UTF-8")}&pwd=${URLEncoder.encode(pwd, "UTF-8")}"
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "POST"
                    connection.doOutput = true

                    OutputStreamWriter(connection.outputStream).use { writer ->
                        writer.write(postData)
                    }

                    val response = connection.inputStream.bufferedReader().use { it.readText() }.trim()
                    connection.disconnect()

                    runOnUiThread {
                        if (response != "0") {
                            val prefs = getSharedPreferences("user_info", MODE_PRIVATE)
                            prefs.edit().putString("id", id).putString("pkey", response).apply()

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("pkey", response)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "네트워크 오류", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
