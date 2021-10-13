package com.example.jokesapp.app.ui.jokepopUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.jokesapp.app.core.JOKE
import com.example.jokesapp.databinding.JokePopupDialogBinding
import com.example.jokesapp.domain.Model.Joke

class JokeDialogFragment : DialogFragment() {
    private var _binding: JokePopupDialogBinding? = null
    private val binding get() = _binding!!
    var joke: Joke? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = JokePopupDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        joke = arguments?.get(JOKE) as Joke
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.jokeId.text = joke?.id.toString()
        binding.jokeType.text = joke?.type
        binding.joke.text = if (joke?.type.equals(
                "single",
                true
            )
        ) joke?.joke else "${joke?.setup}\n ${joke?.delivery}"

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}