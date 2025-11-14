package com.example.aurasense.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.aurasense.R
import com.example.aurasense.data.db.entity.EmergencyContactEntity
import com.example.aurasense.databinding.EmergencyContactListItemBinding

class EmergencyContactListAdapter(
    private val onItemClicked: (EmergencyContactEntity) -> Unit
) : ListAdapter<EmergencyContactEntity, EmergencyContactListAdapter.EmergencyContactViewHolder>(EmergencyContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmergencyContactViewHolder {
        val binding = EmergencyContactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmergencyContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmergencyContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(contact)
        }
        holder.bind(contact)
    }

    class EmergencyContactViewHolder(private val binding: EmergencyContactListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: EmergencyContactEntity) {
            binding.contactName.text = contact.name
            binding.contactPhone.text = contact.phone
            binding.contactImage.load(contact.photoPath) {
                placeholder(R.drawable.ic_contact_placeholder)
            }
        }
    }
}

class EmergencyContactDiffCallback : DiffUtil.ItemCallback<EmergencyContactEntity>() {
    override fun areItemsTheSame(oldItem: EmergencyContactEntity, newItem: EmergencyContactEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EmergencyContactEntity, newItem: EmergencyContactEntity): Boolean {
        return oldItem == newItem
    }
}
