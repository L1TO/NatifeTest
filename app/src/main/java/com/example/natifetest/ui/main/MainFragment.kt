package com.example.natifetest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.natifetest.databinding.FragmentMainBinding
import com.example.natifetest.model.Data
import com.example.natifetest.ui.GifActionListener
import com.example.natifetest.ui.GifAdapter
import com.example.natifetest.utils.factory

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: GifAdapter
    private val viewModel: MainViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GifAdapter(object : GifActionListener {
            override fun onGifDetails(data: Data) {
                val direction = MainFragmentDirections.actionMainFragmentToDetailsFragment(
                    data,
                    data.title ?: "Details"
                )
                findNavController().navigate(direction)
            }

        })
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        viewModel.gifs.observe(requireActivity(), Observer { data ->
            adapter.gifs = data.data
        })
    }
}