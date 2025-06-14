package com.example.aph4.feature_meetingroom.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aph4.R

class MeetingRoomFragment : Fragment() {

    private var meetingId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        meetingId = arguments?.getString("meetingId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meeting_room, container, false)
    }

    companion object {
        fun newInstance(meetingId: String): MeetingRoomFragment {
            return MeetingRoomFragment().apply {
                arguments = Bundle().apply {
                    putString("meetingId", meetingId)
                }
            }
        }
    }
}
