package com.example.aurasense.ui.doctors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.aurasense.R
import com.example.aurasense.data.db.entity.DoctorEntity
import com.example.aurasense.databinding.DoctorListItemBinding

class DoctorListAdapter : ListAdapter<DoctorEntity, DoctorListAdapter.DoctorViewHolder>(DoctorDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val binding = DoctorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = getItem(position)
        holder.bind(doctor)
    }

    class DoctorViewHolder(private val binding: DoctorListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(doctor: DoctorEntity) {
            binding.doctorName.text = doctor.name
            binding.doctorSpecialty.text = doctor.specialty
            binding.doctorPhone.text = doctor.phone
            binding.doctorImage.load(doctor.photoPath) {
                placeholder(R.drawable.ic_doctor_placeholder)
            }
        }
    }
}

class DoctorDiffCallback : DiffUtil.ItemCallback<DoctorEntity>() {
    override fun areItemsTheSame(oldItem: DoctorEntity, newItem: DoctorEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DoctorEntity, newItem: DoctorEntity): Boolean {
        return oldItem == newItem
    }
}
