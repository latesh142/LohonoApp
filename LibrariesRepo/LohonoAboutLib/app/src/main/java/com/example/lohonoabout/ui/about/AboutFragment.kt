package com.example.lohonoabout.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.lohonoabout.R
import com.example.lohonoabout.databinding.FragmentAboutBinding

/**
 * in constructor we accepting to show view model structure im simple way
 * in future we can expand it
 */
class AboutFragment(private val about: AboutData) : Fragment() {

    lateinit var aboutViewModel: AboutViewModel
    private var _binding: FragmentAboutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
            ViewModelProvider(this).get(AboutViewModel::class.java)

        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setObservers()
        setAboutData(about)
        return root
    }

    /**
     * this is the binding between view model and ui
     * currently we doing it in fragment
     * but in future we can do it in another api call
     */
    private fun setObservers() {
        aboutViewModel.aboutUsTitle.observe(viewLifecycleOwner, Observer {
            binding.tvTitle.text = it
        })

        aboutViewModel.description.observe(viewLifecycleOwner, Observer {
            binding.tvDescription.text = it
        })
    }

    /**
     * for now we are setting data offline by taking offline input
     */
    private fun setAboutData(about: AboutData) {
        aboutViewModel.setAboutUsTitle(about.title)
        aboutViewModel.setDescription(about.desc)
        Glide
            .with(this)
            .load(about.image)
            .centerCrop()
            .placeholder(R.drawable.about_us_placeholder)
            .into(binding.ivAboutUs)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}