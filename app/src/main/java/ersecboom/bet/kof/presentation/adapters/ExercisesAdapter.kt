package ersecboom.bet.kof.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ersecboom.bet.kof.databinding.ItemExerciseBinding

import ersecboom.bet.kof.domain.Exercise

class ExercisesAdapter :
    ListAdapter<Exercise, ExercisesAdapter.ViewHolderExercise>(DiffUtilExercises) {

    lateinit var setOnClickListener: (exercise: Exercise) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExercise {
        return ViewHolderExercise.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolderExercise, position: Int) {
        val exercise = getItem(position)
        holder.bind(exercise)
        holder.itemView.setOnClickListener {
            setOnClickListener.invoke(exercise)
        }
    }

    class ViewHolderExercise(private val binding: ItemExerciseBinding) :
        ViewHolder(binding.root) {
        fun bind(exercise: Exercise) {
            binding.ivExercise.setImageResource(exercise.imgResourceId)
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