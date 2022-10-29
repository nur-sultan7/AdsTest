package com.nursultan.adstest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nursultan.adstest.domain.Exercise

class DetailFragment : Fragment() {
    private lateinit var exercise: Exercise
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private fun parseArgs() {
        exercise = arguments?.getParcelable(EXERCISE_KEY)
            ?: throw RuntimeException("Exercise in null")
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