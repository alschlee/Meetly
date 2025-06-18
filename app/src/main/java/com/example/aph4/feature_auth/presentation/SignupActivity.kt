package com.example.aph4.feature_auth.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aph4.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val etId = findViewById<EditText>(R.id.et_id)
        val etPwd = findViewById<EditText>(R.id.et_pwd)
        val etName = findViewById<EditText>(R.id.et_name)
        val etBirth = findViewById<EditText>(R.id.et_birth)
        val btnSignup = findViewById<Button>(R.id.btn_signup)

        btnSignup.setOnClickListener {
            val id = etId.text.toString()
            val pwd = etPwd.text.toString()
            val name = etName.text.toString()
            val birth = etBirth.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val url = URL("http://39.118.94.53/signup.php")
                    val params = "id=${URLEncoder.encode(id, "UTF-8")}" +
                            "&pwd=${URLEncoder.encode(pwd, "UTF-8")}" +
                            "&name=${URLEncoder.encode(name, "UTF-8")}" +
                            "&birth=${URLEncoder.encode(birth, "UTF-8")}"
                    val conn = url.openConnection() as HttpURLConnection
                    conn.requestMethod = "POST"
                    conn.doOutput = true
                    conn.outputStream.use { it.write(params.toByteArray()) }
                    val result = conn.inputStream.bufferedReader().readText().trim()
                    runOnUiThread {
                        if (result != "0") {
                            Toast.makeText(this@SignupActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@SignupActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this@SignupActivity, "네트워크 오류", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
