package com.example.notes2tab.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes2tab.adapters.SongAdapter
import com.example.notes2tab.dataModels.Song
import com.example.notes2tab.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //
        val songsList = mutableListOf<Song>()
        songsList.add(Song("Звезда по имени солнце", "В. Цой"))
        songsList.add(Song("Джокер", "Король и Шут"))
        songsList.add(Song("Кукла колдуна", "Король и Шут"))
        songsList.add(Song("Что такое осень?", "ДДТ"))
        songsList.add(Song("Батарейка", "Жуки"))
        songsList.add(Song("Я солдат", "Пятница"))
        songsList.add(Song("Районы-кварталы", "Звери"))
        songsList.add(Song("Прыгну со скалы", "Король и Шут"))
        songsList.add(Song("Бомж", "Сектор газа"))
        songsList.add(Song("Потерянный рай", "Ария"))
        songsList.add(Song("Конь", "Любэ"))
        songsList.add(Song("Танец злобного гения", "Король и Шут"))

        val adapter = SongAdapter(songsList)
        binding.rvSongs.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }
    }
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = HomeFragment()
    }
}