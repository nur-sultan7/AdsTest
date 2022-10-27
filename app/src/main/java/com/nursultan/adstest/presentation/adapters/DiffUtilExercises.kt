package com.nursultan.adstest.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.nursultan.adstest.domain.Exercise

object DiffUtilExercises : DiffUtil.ItemCallback<Exercise>() {
    override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem == newItem
    }
}