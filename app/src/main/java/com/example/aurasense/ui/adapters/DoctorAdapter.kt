package com.example.aurasense.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.aurasense.data.db.entity.DoctorEntity
import com.example.aurasense.databinding.ItemDoctorBinding

class DoctorAdapter(
    private val onDoctorClick: (DoctorEntity) -> Unit
) : ListAdapter<DoctorEntity, DoctorAdapter.DoctorViewHolder>(DoctorDiffCallback()) {

    inner class DoctorViewHolder(private val binding: ItemDoctorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(doctor: DoctorEntity) {
            binding.doctor = doctor
            doctor.photoPath?.let {
                binding.doctorImage.visibility = View.VISIBLE
                binding.doctorImage.load(Uri.parse(it))
            } ?: run {
                binding.doctorImage.visibility = View.GONE
            }
            binding.root.setOnClickListener { onDoctorClick(doctor) }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val binding = ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DoctorDiffCallback : DiffUtil.ItemCallback<DoctorEntity>() {
    override fun areItemsTheSame(oldItem: DoctorEntity, newItem: DoctorEntity) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: DoctorEntity, newItem: DoctorEntity) = oldItem == newItem
}