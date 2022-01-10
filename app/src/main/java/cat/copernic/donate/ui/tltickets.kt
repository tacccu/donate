package cat.copernic.donate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.donate.R
import cat.copernic.donate.databinding.FragmentTlticketsBinding

class tltickets : Fragment() {
    private var __binding: FragmentTlticketsBinding? = null
    private val binding get() = __binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView = binding.RecyclerViewTlReport
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        __binding = FragmentTlticketsBinding.inflate(inflater, container, false)

        return inflater.inflate(R.layout.fragment_tltickets, container, false)
    }
}