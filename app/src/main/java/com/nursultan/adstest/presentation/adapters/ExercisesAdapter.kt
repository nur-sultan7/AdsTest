package com.nursultan.adstest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nursultan.adstest.databinding.ItemExerciseBinding
import com.nursultan.adstest.domain.Exercise
import com.squareup.picasso.Picasso

class ExercisesAdapter :
    ListAdapter<Exercise, ExercisesAdapter.ViewHolderExercise>(DiffUtilExercises) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExercise {
        return ViewHolderExercise.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolderExercise, position: Int) {
        val exercise = getItem(position)
        holder.bind(exercise)
    }

    class ViewHolderExercise(private val binding: ItemExerciseBinding) :
        ViewHolder(binding.root) {
        fun bind(exercise: Exercise) {
            Picasso.get()
                .load(exercise.imgResourceId)
                .into(binding.ivExercise)
            binding.tvName.text = exercise.name
            binding.tvDescription.text = exercise.description
        }

        companion object {
            fun create(view: ViewGroup): ViewHolderExercise {
                val binding =
                    ItemExerciseBinding.inflate(
                        LayoutInflater.from(view.context),
                        view,
                        false
                    )
                return ViewHolderExercise(binding)
            }
        }

    }
}