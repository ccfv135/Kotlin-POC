package com.app.koltinpoc.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.koltinpoc.R
import com.app.koltinpoc.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.btnOnline.setOnClickListener {
            val bundle = bundleOf("delete_state" to false)
            findNavController().navigate(R.id.action_homeFragment_to_onlineFragment, bundle)
        }
    }
}