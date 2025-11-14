package com.example.aurasense.ui.doctors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aurasense.AuraSenseApp
import com.example.aurasense.databinding.FragmentDoctorListBinding
import com.example.aurasense.ui.ViewModelFactory

class DoctorListFragment : Fragment() {

    private var _binding: FragmentDoctorListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DoctorViewModel by viewModels {
        ViewModelFactory((requireActivity().application as AuraSenseApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentDoctorListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DoctorListAdapter()
        binding.doctorsRecyclerView.adapter = adapter
        binding.doctorsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.doctors.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
