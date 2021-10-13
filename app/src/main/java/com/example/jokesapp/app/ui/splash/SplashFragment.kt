package com.example.jokesapp.app.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jokesapp.R
import com.example.jokesapp.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding: SplashFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashFragmentBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.splashText.text =
            getString(R.string.this_app_has_opened_0_times, viewModel.getAppOpenTimes())

        lifecycleScope.launchWhenStarted {
            viewModel.timerState.collect {
                when (it) {
                    SplashState.Finish -> findNavController().navigate(R.id.action_splashFragment_to_jokesFragment)
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}