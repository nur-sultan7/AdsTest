package ersecboom.bet.kof.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import ersecboom.bet.kof.databinding.FragmentWebviewBinding


class WebViewFragment : Fragment() {
    private var _binding: FragmentWebviewBinding? = null
    private val binding: FragmentWebviewBinding
        get() = _binding ?: throw RuntimeException("FragmentWebViewBinding is null")
    private lateinit var url: String

    private val sharedPreferences by lazy {
        requireActivity().getSharedPreferences(MainActivity.STORAGE_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        url = arguments?.getString(RemoteConfigUtil.URL_KEY)
            ?: throw RuntimeException("url is null")
        binding.mainWebView.webViewClient = WebViewClient()
        binding.mainWebView.settings.javaScriptEnabled = true
        binding.mainWebView.settings.domStorageEnabled = true
        binding.mainWebView.settings.javaScriptCanOpenWindowsAutomatically = true
        binding.mainWebView.loadUrl(url)
        saveValue(url)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnBackPressedDispatcher()
    }

    private fun setOnBackPressedDispatcher() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.mainWebView.canGoBack())
                    binding.mainWebView.goBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }


    private fun saveValue(value: String) {
        sharedPreferences.edit().apply {
            putString(RemoteConfigUtil.URL_KEY, value)
        }.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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