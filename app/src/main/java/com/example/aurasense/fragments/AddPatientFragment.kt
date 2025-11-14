package com.example.aurasense.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.aurasense.AuraSenseApp
import com.example.aurasense.data.db.models.Patient
import com.example.aurasense.databinding.FragmentAddPatientBinding
import com.example.aurasense.viewmodel.PatientViewModel
import com.example.aurasense.viewmodel.PatientViewModelFactory

class AddPatientFragment : Fragment() {

    private var _binding: FragmentAddPatientBinding? = null
    private val binding get() = _binding!!

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((requireActivity().application as AuraSenseApp).patientRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.savePatientButton.setOnClickListener {
            val patientName = binding.patientNameInput.text.toString()
            if (patientName.isNotEmpty()) {
                patientViewModel.insertPatient(Patient(name = patientName))
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}