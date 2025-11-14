package com.example.aurasense.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aurasense.AuraSenseApp
import com.example.aurasense.data.db.TaskStatus
import com.example.aurasense.databinding.FragmentTaskDetailsBinding
import com.example.aurasense.ui.ViewModelFactory
import kotlinx.coroutines.launch

class TaskDetailsFragment : Fragment() {

    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: TaskDetailsFragmentArgs by navArgs()

    private val viewModel: TaskViewModel by viewModels {
        ViewModelFactory((requireActivity().application as AuraSenseApp).repository, args.patientId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tasks.collect { tasks ->
                val task = tasks.find { it.id == args.taskId }
                if (task != null) {
                    binding.taskTitle.text = task.title
                    binding.taskDescription.text = task.description
                    binding.taskProgress.progress = if (task.status == TaskStatus.COMPLETED) 100 else 75

                    binding.completeTaskButton.setOnClickListener {
                        lifecycleScope.launch {
                            viewModel.addOrUpdateTask(task.copy(status = TaskStatus.COMPLETED))
                            findNavController().popBackStack()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}