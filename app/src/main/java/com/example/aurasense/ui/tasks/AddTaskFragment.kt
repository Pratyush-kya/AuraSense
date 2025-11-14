package com.example.aurasense.ui.tasks

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
import com.example.aurasense.databinding.FragmentAddTaskBinding
import com.example.aurasense.ui.ViewModelFactory
import kotlinx.coroutines.launch

class AddTaskFragment : Fragment() {

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private val args: AddTaskFragmentArgs by navArgs()

    private val viewModel: TaskViewModel by viewModels {
        ViewModelFactory((requireActivity().application as AuraSenseApp).repository, args.patientId)
    }

    private var imageUri: Uri? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUri = result.data?.data
            binding.taskImage.setImageURI(imageUri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImage.launch(intent)
        }

        binding.saveTaskButton.setOnClickListener {
            val title = binding.taskTitleInput.text.toString()
            val description = binding.taskDescriptionInput.text.toString()

            if (title.isNotEmpty()) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.insertTask(
                        title = title,
                        description = description,
                        imagePath = imageUri?.toString()
                    )
                    findNavController().popBackStack()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}