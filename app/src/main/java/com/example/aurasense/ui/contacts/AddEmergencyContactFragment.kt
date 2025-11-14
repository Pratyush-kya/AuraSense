package com.example.aurasense.ui.contacts

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aurasense.AuraSenseApp
import com.example.aurasense.data.db.entity.EmergencyContactEntity
import com.example.aurasense.databinding.FragmentAddEmergencyContactBinding
import com.example.aurasense.ui.ViewModelFactory
import kotlinx.coroutines.launch

class AddEmergencyContactFragment : Fragment() {

    private var _binding: FragmentAddEmergencyContactBinding? = null
    private val binding get() = _binding!!

    private var imageUri: Uri? = null

    private val args: AddEmergencyContactFragmentArgs by navArgs()

    private val viewModel: EmergencyContactViewModel by viewModels {
        ViewModelFactory((requireActivity().application as AuraSenseApp).repository)
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUri = result.data?.data
            binding.contactImage.setImageURI(imageUri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEmergencyContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contactId = args.contactId
        if (contactId != -1L) {
            viewModel.getContact(contactId).observe(viewLifecycleOwner) { contact ->
                contact?.let {
                    binding.contactNameInput.setText(it.name)
                    binding.contactPhoneInput.setText(it.phone)
                    it.photoPath?.let {
                        imageUri = Uri.parse(it)
                        binding.contactImage.setImageURI(imageUri)
                    }
                }
            }
        }

        binding.addImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImage.launch(intent)
        }

        binding.saveContactButton.setOnClickListener {
            val name = binding.contactNameInput.text.toString()
            val phone = binding.contactPhoneInput.text.toString()

            if (name.isNotBlank() && phone.isNotBlank()) {
                val contact = EmergencyContactEntity(
                    id = if (contactId != -1L) contactId else 0,
                    name = name,
                    phone = phone,
                    photoPath = imageUri?.toString()
                )
                saveContact(contact)
            } else {
                // Show error
            }
        }
    }

    private fun saveContact(contact: EmergencyContactEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            if (contact.id == 0L) {
                viewModel.insert(contact)
            } else {
                viewModel.update(contact)
            }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
