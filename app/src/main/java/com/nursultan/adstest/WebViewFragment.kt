package com.nursultan.adstest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nursultan.adstest.databinding.FragmentWebviewBinding

class WebViewFragment : Fragment() {
    private var _binding: FragmentWebviewBinding? = null
    private val binding: FragmentWebviewBinding
        get() = _binding ?: throw RuntimeException("FragmentWebViewBinding is null")
    private lateinit var url: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        url = arguments?.getString(RemoteConfigUtil.URL_KEY)
            ?: throw RuntimeException("url is null")
        binding.mainWebView.loadUrl(url)
    }

    companion object {

        fun newInstance(url: String): WebViewFragment {
            return WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(RemoteConfigUtil.URL_KEY, url)
                }
            }
        }
    }
}