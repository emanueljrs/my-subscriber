package com.emanuel.mysubscribers.ui.subscriberlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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




}