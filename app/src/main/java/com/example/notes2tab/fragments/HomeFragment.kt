package com.example.notes2tab.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes2tab.adapters.SongAdapter
import com.example.notes2tab.dataModels.Song
import com.example.notes2tab.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val songsList = mutableListOf<Song>()
        songsList.add(Song("Звезда по имени солнце", "В. Цой"))
        songsList.add(Song("Название песни 2", "Автор 2"))
        songsList.add(Song("Название песни 3", "Автор 3"))

        val adapter = SongAdapter(songsList)
        binding.rvSongs.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = HomeFragment()
    }
}