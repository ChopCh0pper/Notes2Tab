package com.example.notes2tab.fragments

import com.example.notes2tab.parser.StringTabParser
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes2tab.databinding.FragmentTabsBinding

class TabsFragment : Fragment() {
    private lateinit var binding: FragmentTabsBinding
    private lateinit var stringTabParser : StringTabParser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //путь в raw файлах, для заглушки
        stringTabParser = StringTabParser(requireContext())
        stringTabParser.parseFile("kuzne4ik")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(param1: String, param2: String) = TabsFragment()
    }
}