package com.example.aph4.feature_home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aph4.R
import com.example.aph4.databinding.FragmentHomeBinding
import com.example.aph4.feature_meetingroom.model.Meeting
import com.example.aph4.feature_meetingroom.model.Member
import com.example.aph4.feature_meetingroom.presentation.MeetingAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val meetings = listOf(
            Meeting(
                "1", "맛집찾아삼만리..", "2025-06-15", "서울시 강남구",
                listOf(
                    Member("1", "하니", R.drawable.default_profile),
                    Member("2", "김안경만두", R.drawable.default_profile),
                    Member("3", "마크", R.drawable.default_profile)
                )
            ),
            Meeting(
                "2", "모각코", "2025-06-16", "서울시 서초구",
                listOf(
                    Member("4", "조성진", R.drawable.default_profile),
                    Member("5", "마크", R.drawable.default_profile)
                )
            )
        )

        // Adapter 연결
        binding.rvMeetings.adapter = MeetingAdapter(requireContext(), meetings)
        binding.rvMeetings.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
