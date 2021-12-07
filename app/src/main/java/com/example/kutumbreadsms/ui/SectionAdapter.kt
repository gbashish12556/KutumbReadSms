package com.example.kutumbreadsms.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kutumbreadsms.data.SectionData
import com.example.kutumbreadsms.databinding.SectionItemBinding
import timber.log.Timber


class SectionAdapter(private val viewModel: MainViewModel,private val mainActivity: MainActivity) :
    ListAdapter<SectionData, SectionAdapter.ViewHolder>(TaskDiffCallback2()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item, mainActivity)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: SectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var smsAdapter: SmsAdapter

        fun bind(viewModel: MainViewModel, item: SectionData, mainActivity: MainActivity) {

            binding.viewmodel = viewModel
            binding.section = item
            binding.context = mainActivity
            setupListAdapter()
            binding.executePendingBindings()
        }

        private fun setupListAdapter() {
                smsAdapter = SmsAdapter(binding.viewmodel!!)
                val llm = LinearLayoutManager(binding.context)
                llm.orientation = LinearLayoutManager.VERTICAL
                binding.smsRecyclerView.setLayoutManager(llm)
                binding.smsRecyclerView.adapter = smsAdapter
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SectionItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}
//
/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class TaskDiffCallback2 : DiffUtil.ItemCallback<SectionData>() {
    override fun areItemsTheSame(oldItem: SectionData, newItem: SectionData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SectionData, newItem: SectionData): Boolean {
        return oldItem == newItem
    }
}