package com.example.jokesapp.app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.jokesapp.R
import com.example.jokesapp.app.core.JOKE
import com.example.jokesapp.data.network.ResponseWrapper
import com.example.jokesapp.databinding.FragmentJokesBinding
import com.example.jokesapp.domain.Model.Joke
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesFragment : Fragment() {
    private var _binding: FragmentJokesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: JokesAdapter
    private val viewModel: JokesFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = JokesAdapter {
            findNavController().navigate(
                R.id.action_jokesFragment_to_jokeDialogFragment,
                bundleOf(JOKE to it)
            )
        }
        binding.jokesRv.adapter = adapter
        binding.swipeLayout.setOnRefreshListener {
            viewModel.requestJokes()
        }

        viewModel.mutableJokesFlow.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseWrapper.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                }
                is ResponseWrapper.Success -> {
                    binding.loader.visibility = View.GONE
                    binding.errorMsg.visibility = View.GONE

                    adapter.updateJokes(it.data as List<Joke>)
                }
                else -> {
                    binding.loader.visibility = View.GONE
                    binding.errorMsg.visibility = View.VISIBLE
                }


            }
            binding.swipeLayout.isRefreshing = false

        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}