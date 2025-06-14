package com.example.aph4.feature_meetingroom.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aph4.R
import com.example.aph4.feature_meetingroom.model.Meeting

class MeetingAdapter(
    private val context: Context,
    private val meetings: List<Meeting>
) : RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder>() {

    private val colors = listOf(
        "#4CAF50", "#FDD835", "#4D9DE0"
    )
    private var shuffledColors = colors.shuffled()

    class MeetingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMeetingName: TextView = view.findViewById(R.id.tvMeetingName)
        val tvMeetingDate: TextView = view.findViewById(R.id.tvMeetingDate)
        val tvMeetingPlace: TextView = view.findViewById(R.id.tvMeetingPlace)
        val llMemberImages: LinearLayout = view.findViewById(R.id.llMemberImages)
        val llCardBackground: LinearLayout = view.findViewById(R.id.llCardBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meeting, parent, false)
        return MeetingViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeetingViewHolder, position: Int) {
        val meeting = meetings[position]

        // 카드 수가 색상 수보다 많으면 다시 섞기
        if (position == 0) shuffledColors = colors.shuffled()
        val color = shuffledColors[position % shuffledColors.size]
        val randomColor = Color.parseColor(color)
        Log.d("MeetingAdapter", "position: $position, color: $randomColor")
        holder.llCardBackground.setBackgroundColor(randomColor)

        // 약속 정보 설정
        holder.tvMeetingName.text = meeting.name
        holder.tvMeetingDate.text = meeting.date
        holder.tvMeetingPlace.text = "장소: ${meeting.place}"

        // 멤버 사진 추가
        holder.llMemberImages.removeAllViews()
        meeting.members.forEach { member ->
            val imageView = ImageView(context).apply {
                layoutParams = LinearLayout.LayoutParams(40, 40).apply {
                    marginEnd = 8
                }
                setImageResource(R.drawable.default_profile) // TODO: member.profileImage
                scaleType = ImageView.ScaleType.CENTER_CROP
                clipToOutline = true
                background = context.getDrawable(R.drawable.btn_rounded_corners)
            }
            holder.llMemberImages.addView(imageView)
        }

        // 카드 클릭 시 상세 화면 이동
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MeetingRoomActivity::class.java)
            intent.putExtra("meetingId", meeting.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = meetings.size
}
