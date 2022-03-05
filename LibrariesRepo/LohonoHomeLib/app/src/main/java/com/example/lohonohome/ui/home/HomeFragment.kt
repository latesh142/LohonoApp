package com.example.lohonohome.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lohonohome.R
import com.example.lohonohome.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment() : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    open val binding get() = _binding!!
    lateinit var fab: FloatingActionButton
    lateinit var homeConnector: HomeConnector

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setListeners()
        setObservers()
        return root
    }

    private fun setObservers() {
        homeViewModel.themeMode.observe(viewLifecycleOwner, Observer {
            changeModeIcon(it)
        })
    }

    private fun setListeners() {
        binding.btnDarkTheme.setOnClickListener {
            if (::homeConnector.isInitialized)
                homeConnector.changeToDark()
        }

        binding.btnLightTheme.setOnClickListener {
            if (::homeConnector.isInitialized)
                homeConnector.changeToLight()
        }
    }

    private fun changeModeIcon(mode: String) {
        when (mode) {
            HomeUtil.DARK -> {
                binding.ivThemeMode.setImageResource(R.drawable.dark_mode_theme)
            }
            HomeUtil.LIGHT -> {
                binding.ivThemeMode.setImageResource(R.drawable.twotone_wb_sunny_black_48dp)
            }
        }
    }

    /**
     * this is connector for changing the theme as per the user request
     */
    open fun setConnector(connector: HomeConnector) {
        homeConnector = connector
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}