package com.example.aurasense.ui.doctors

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aurasense.AuraSenseApp
import com.example.aurasense.data.db.entity.DoctorEntity
import com.example.aurasense.databinding.FragmentAddDoctorBinding
import kotlinx.coroutines.launch

class AddDoctorFragment : Fragment() {

    private var _binding: FragmentAddDoctorBinding? = null
    private val binding get() = _binding!!

    private var imageUri: Uri? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUri = result.data?.data
            binding.doctorImage.setImageURI(imageUri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddDoctorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImage.launch(intent)
        }

        binding.saveDoctorButton.setOnClickListener {
            val name = binding.doctorNameInput.text.toString()
            val specialty = binding.doctorSpecialtyInput.text.toString()
            val phone = binding.doctorPhoneInput.text.toString()
            val email = binding.doctorEmailInput.text.toString()

            if (name.isNotBlank() && specialty.isNotBlank()) {
                val doctor = DoctorEntity(
                    name = name, 
                    specialty = specialty, 
                    phone = phone, 
                    email = email,
                    photoPath = imageUri?.toString()
                )
                saveDoctor(doctor)
            } else {
                // Show error
            }
        }
    }

    private fun saveDoctor(doctor: DoctorEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            val app = requireActivity().application as AuraSenseApp
            app.repository.insertDoctor(doctor)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
