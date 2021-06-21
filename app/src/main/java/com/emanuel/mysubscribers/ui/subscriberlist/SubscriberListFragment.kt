package com.emanuel.mysubscribers.ui.subscriberlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emanuel.mysubscribers.data.db.entity.SubscriberEntity
import com.emanuel.mysubscribers.databinding.SubscriberListFragmentBinding

class SubscriberListFragment : Fragment() {

    private var _binding: SubscriberListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubscriberListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter: SubscriberListAdapter = SubscriberListAdapter(
            listOf(
                SubscriberEntity(1, "Emanuel", "emanuel@emanul.com"),
                SubscriberEntity(2, "Neite", "neite@emanul.com"),
                SubscriberEntity(3, "Alice", "alice@emanul.com"),
            )
        )

        with(binding.recyclerSubscribers) {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

}