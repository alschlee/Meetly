package com.example.aph4.feature_meetingroom.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aph4.R

class MeetingRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_meeting_room)
        // 루트 뷰(container)에 EdgeToEdge 적용
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val meetingId = intent.getStringExtra("meetingId") ?: return
        // FragmentTransaction으로 교체
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MeetingRoomFragment.newInstance(meetingId))
            .commit()
    }
}
