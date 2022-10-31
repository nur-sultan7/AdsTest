package ersecboom.bet.kof.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ersecboom.bet.kof.R
import ersecboom.bet.kof.databinding.LoadingScreenBinding


class LoadingFragment : Fragment() {
    private var _binding: LoadingScreenBinding? = null
    private val binding: LoadingScreenBinding
        get() = _binding ?: throw RuntimeException("LoadingScreenBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoadingScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().window.statusBarColor =
                requireActivity().getColor(R.color.transparent_black)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().window.statusBarColor =
                requireActivity().getColor(R.color.white)
        }
        _binding = null
    }


    companion object {
        fun newInstance(): LoadingFragment {
            return LoadingFragment()
        }
    }
}