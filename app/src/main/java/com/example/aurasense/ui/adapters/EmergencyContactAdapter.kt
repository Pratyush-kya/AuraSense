package com.example.aurasense.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.aurasense.data.db.entity.EmergencyContactEntity
import com.example.aurasense.databinding.ItemEmergencyContactBinding

class EmergencyContactAdapter(
    private val onContactClick: (EmergencyContactEntity) -> Unit
) : ListAdapter<EmergencyContactEntity, EmergencyContactAdapter.ContactViewHolder>(ContactDiffCallback()) {

    inner class ContactViewHolder(private val binding: ItemEmergencyContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: EmergencyContactEntity) {
            binding.contact = contact
            contact.photoPath?.let {
                binding.contactImage.visibility = View.VISIBLE
                binding.contactImage.load(Uri.parse(it))
            } ?: run {
                binding.contactImage.visibility = View.GONE
            }
            binding.root.setOnClickListener { onContactClick(contact) }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemEmergencyContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ContactDiffCallback : DiffUtil.ItemCallback<EmergencyContactEntity>() {
    override fun areItemsTheSame(oldItem: EmergencyContactEntity, newItem: EmergencyContactEntity) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: EmergencyContactEntity, newItem: EmergencyContactEntity) = oldItem == newItem
}