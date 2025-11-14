package com.example.aurasense.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.aurasense.data.db.entity.TaskEntity
import com.example.aurasense.databinding.ItemTaskBinding

class TaskAdapter(
    private val onTaskClick: (TaskEntity) -> Unit
) : ListAdapter<TaskEntity, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskEntity) {
            binding.task = task
            task.photoPath?.let {
                binding.taskImage.visibility = View.VISIBLE
                binding.taskImage.load(Uri.parse(it))
            } ?: run {
                binding.taskImage.visibility = View.GONE
            }
            binding.root.setOnClickListener { onTaskClick(task) }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<TaskEntity>() {
    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity) = oldItem == newItem
}