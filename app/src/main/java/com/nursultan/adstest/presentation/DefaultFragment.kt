package com.nursultan.adstest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nursultan.adstest.databinding.FragmentDefaultBinding


class DefaultFragment : Fragment() {
    private var _binding: FragmentDefaultBinding? = null
    private val binding: FragmentDefaultBinding
        get() = _binding ?: throw RuntimeException("FragmentDefaultBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefaultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): DefaultFragment {
            return DefaultFragment()
        }
    }
}