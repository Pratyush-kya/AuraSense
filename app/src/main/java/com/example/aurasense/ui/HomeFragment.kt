package com.example.aurasense.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aurasense.R
import com.example.aurasense.databinding.FragmentHomeBinding
import com.example.aurasense.utils.ThemeManager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var themeManager: ThemeManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themeManager = ThemeManager(requireContext())

        binding.doctorsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_doctorListFragment)
        }

        binding.emergencyContactsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_emergencyContactListFragment)
        }

        binding.themeSwitch.isChecked = themeManager.getTheme()

        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            themeManager.saveTheme(isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
