package com.example.aurasense.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aurasense.data.db.models.Patient
import com.example.aurasense.databinding.ItemPatientBinding

class PatientListAdapter(private val onItemClicked: (Patient) -> Unit) : ListAdapter<Patient, PatientListAdapter.PatientViewHolder>(PatientsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PatientViewHolder {
        return PatientViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
    }

    class PatientViewHolder(private val binding: ItemPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(patient: Patient) {
            binding.patientName.text = patient.name
        }

        companion object {
            fun create(parent: ViewGroup): PatientViewHolder {
                val binding = ItemPatientBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return PatientViewHolder(binding)
            }
        }
    }

    class PatientsComparator : DiffUtil.ItemCallback<Patient>() {
        override fun areItemsTheSame(oldItem: Patient, newItem: Patient):
                Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Patient, newItem: Patient):
                Boolean {
            return oldItem == newItem
        }
    }
}
