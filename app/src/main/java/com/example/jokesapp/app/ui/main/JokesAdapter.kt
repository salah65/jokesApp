package com.example.jokesapp.app.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesapp.databinding.JokeRvItemBinding
import com.example.jokesapp.domain.Model.Joke

class JokesAdapter(
    private var jokes: List<Joke>? = null,
    val onJokeClickListener: (joke: Joke) -> Unit = {}
) :
    RecyclerView.Adapter<JokesAdapter.ViewHolder>() {
    class ViewHolder(binding: JokeRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding = JokeRvItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            JokeRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun updateJokes(jokes: List<Joke>) {
        this.jokes = jokes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentJoke = jokes?.get(position)
        holder.binding.jokeText.text = if (currentJoke?.type.equals(
                "single",
                true
            )
        ) currentJoke?.joke else "${currentJoke?.setup}\n ${currentJoke?.delivery}"
        holder.itemView.setOnClickListener {
            currentJoke?.let {
                onJokeClickListener(it)
            }
        }
    }

    override fun getItemCount(): Int {
        return jokes?.size ?: 0
    }
}