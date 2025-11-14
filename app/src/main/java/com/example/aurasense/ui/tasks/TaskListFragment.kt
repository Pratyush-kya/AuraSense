package com.example.aurasense.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aurasense.AuraSenseApp
import com.example.aurasense.databinding.FragmentTaskListBinding
import com.example.aurasense.ui.ViewModelFactory
import kotlinx.coroutines.launch

class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private val args: TaskListFragmentArgs by navArgs()

    private val viewModel: TaskViewModel by viewModels {
        ViewModelFactory((requireActivity().application as AuraSenseApp).repository, args.patientId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TaskListAdapter()
        binding.tasksRecyclerView.adapter = adapter
        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.addTaskFab.setOnClickListener {
            args.patientId?.let { patientId ->
                val action = TaskListFragmentDirections.actionTaskListFragmentToAddTaskFragment(patientId)
                findNavController().navigate(action)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            binding.progressBar.isVisible = true
            viewModel.tasks.collect { tasks ->
                adapter.submitList(tasks)
                binding.progressBar.isVisible = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}