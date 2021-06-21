package com.emanuel.mysubscribers.ui.subscriberlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.mysubscribers.data.db.entity.SubscriberEntity
import com.emanuel.mysubscribers.databinding.SubscriberItemBinding

class SubscriberListAdapter(
    private val subscribers: List<SubscriberEntity>
) : RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    private lateinit var binding: SubscriberItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        binding = SubscriberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubscriberListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bind(subscribers[position])
    }

    override fun getItemCount() = subscribers.size

    class SubscriberListViewHolder(binding: SubscriberItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val name = binding.textSubscriberName
        private val email = binding.textSubscriberEmail

        fun bind(subscriber: SubscriberEntity) {
            name.text = subscriber.name
            email.text = subscriber.email
        }
    }
}