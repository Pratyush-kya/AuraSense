package com.example.aurasense.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aurasense.data.db.entity.TaskEntity
import com.example.aurasense.databinding.ItemTaskBinding

class TaskListAdapter : ListAdapter<TaskEntity, TaskListAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }

    class TaskViewHolder private constructor(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskEntity) {
            binding.task = task
            binding.root.setOnClickListener {
                val action = TaskListFragmentDirections.actionTaskListFragmentToTaskDetailsFragment(
                    patientId = task.patientId,
                    taskId = task.id
                )
                it.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TaskViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTaskBinding.inflate(layoutInflater, parent, false)
                return TaskViewHolder(binding)
            }
        }
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<TaskEntity>() {
    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem == newItem
    }
}
