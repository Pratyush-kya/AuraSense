package com.example.aurasense.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aurasense.AuraSenseApp
import com.example.aurasense.databinding.FragmentEmergencyContactListBinding
import com.example.aurasense.ui.ViewModelFactory

class EmergencyContactListFragment : Fragment() {

    private var _binding: FragmentEmergencyContactListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EmergencyContactViewModel by viewModels {
        ViewModelFactory((requireActivity().application as AuraSenseApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmergencyContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EmergencyContactListAdapter { contact ->
            val action = EmergencyContactListFragmentDirections.actionEmergencyContactListFragmentToAddEmergencyContactFragment(contact.id)
            findNavController().navigate(action)
        }
        binding.contactsRecyclerView.adapter = adapter
        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.contacts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
