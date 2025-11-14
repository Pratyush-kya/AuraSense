package com.example.aurasense.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aurasense.AuraSenseApp
import com.example.aurasense.adapter.PatientListAdapter
import com.example.aurasense.databinding.FragmentPatientListBinding
import com.example.aurasense.viewmodel.PatientViewModel
import com.example.aurasense.viewmodel.PatientViewModelFactory
import kotlinx.coroutines.launch

class PatientListFragment : Fragment() {

    private var _binding: FragmentPatientListBinding? = null
    private val binding get() = _binding!!

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((requireActivity().application as AuraSenseApp).patientRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPatientListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PatientListAdapter { patient ->
            val action = PatientListFragmentDirections.actionPatientListFragmentToTaskListFragment(patient.id.toString())
            findNavController().navigate(action)
        }

        binding.patientsRecyclerView.adapter = adapter
        binding.patientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            patientViewModel.allPatients.collect { patients ->
                adapter.submitList(patients)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}