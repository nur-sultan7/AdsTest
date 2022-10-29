package com.nursultan.adstest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nursultan.adstest.R
import com.nursultan.adstest.databinding.FragmentDefaultBinding
import com.nursultan.adstest.domain.Exercise
import com.nursultan.adstest.presentation.adapters.ExercisesAdapter


class DefaultFragment : Fragment() {

    private var _binding: FragmentDefaultBinding? = null
    private val binding: FragmentDefaultBinding
        get() = _binding ?: throw RuntimeException("FragmentDefaultBinding is null")

    private lateinit var adapter: ExercisesAdapter

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefaultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvExercises.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = ExercisesAdapter()
        binding.rvExercises.adapter = adapter
        adapter.submitList(viewModel.getAllExercises())
        setOnItemClickListener()
    }

    private fun setOnItemClickListener() {
        adapter.setOnClickListener = {
            launchDetailFragment(it)
        }
    }

    private fun launchDetailFragment(exercise: Exercise) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, DetailFragment.newInstance(exercise))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(): DefaultFragment {
            return DefaultFragment()
        }
    }
}