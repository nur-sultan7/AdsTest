package ersecboom.bet.kof.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ersecboom.bet.kof.databinding.FragmentDetailsBinding

import ersecboom.bet.kof.domain.Exercise

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailsBinding is null")
    private lateinit var exercise: Exercise

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ivExercise.setImageResource(exercise.imgResourceId)
            tvName.text = exercise.name
            tvSpec.text = exercise.spec
            tvDescription.text = exercise.description
        }
    }

    private fun parseArgs() {
        exercise = arguments?.getParcelable(EXERCISE_KEY)
            ?: throw RuntimeException("Exercise in null")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EXERCISE_KEY = "exercise"
        fun newInstance(exercise: Exercise): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXERCISE_KEY, exercise)
                }
            }
        }
    }
}