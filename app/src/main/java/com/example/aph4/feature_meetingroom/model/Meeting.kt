package com.example.aph4.feature_meetingroom.model

data class Meeting(
    val id: String,
    val name: String,
    val date: String,
    val place: String,
    val members: List<Member>
)

