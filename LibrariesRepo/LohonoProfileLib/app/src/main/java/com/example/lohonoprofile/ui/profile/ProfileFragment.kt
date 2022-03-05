package com.example.lohonoprofile.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lohonoprofile.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide
import com.example.lohonoprofile.R

/**
 * for now we are accessing the offline data using profile class
 */
class ProfileFragment(private val profile: Profile) : Fragment() {

    lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private lateinit var profileListener: ProfileListener

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setObservers()
        setListeners()
        setUpData(profile)
        return root
    }

    private fun setObservers() {
        profileViewModel.userName.observe(viewLifecycleOwner, Observer {
            binding.tvUsername.text = it
        })

        profileViewModel.area.observe(viewLifecycleOwner, Observer {
            binding.tvArea.text = it
        })

        profileViewModel.contact.observe(viewLifecycleOwner, Observer {
            binding.tvContact.text = it
        })
    }

    private fun setListeners() {
        binding.btnHi.setOnClickListener {
            if (::profileListener.isInitialized)
                profileListener.onLanguageChange(ProfileConstants.HINDI)
        }

        binding.btnEng.setOnClickListener {
            if (::profileListener.isInitialized)
                profileListener.onLanguageChange(ProfileConstants.ENGLISH)
        }
    }

    private fun setUpData(profile: Profile) {
        profileViewModel.setUserName(profile.name)
        profileViewModel.serAreaName(profile.area)
        profileViewModel.setContact(profile.contact)

        Glide
            .with(this)
            .load(profile.profilePic)
            .centerCrop()
            .placeholder(R.drawable.place_holder_profile)
            .into(binding.ivProfilePic)
    }

    /**
     * this will give the click event callback
     */
    open fun setProfileListenerValue(profileListener: ProfileListener) {
        this.profileListener = profileListener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}