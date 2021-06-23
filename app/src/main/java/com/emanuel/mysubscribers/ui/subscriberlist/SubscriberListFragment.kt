package com.emanuel.mysubscribers.ui.subscriberlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.emanuel.mysubscribers.R
import com.emanuel.mysubscribers.data.db.AppDatabase
import com.emanuel.mysubscribers.data.db.dao.SubscriberDAO
import com.emanuel.mysubscribers.databinding.SubscriberListFragmentBinding
import com.emanuel.mysubscribers.extension.navigateWithAnimations
import com.emanuel.mysubscribers.repository.DatabaseDataSource
import com.emanuel.mysubscribers.repository.SubscriberRepository

class SubscriberListFragment : Fragment() {

    private var _binding: SubscriberListFragmentBinding? = null
    private val binding get() = _binding!!

    //Cria uma instância do viewModel com o padrão factory com injeção de dependência
    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO = AppDatabase.getInstance(requireContext()).subscriberDAO

                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberListViewModel(repository) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubscriberListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelEvents()
        configureViewListeners()
    }

    private fun observeViewModelEvents() {
        viewModel.allSubscriberEvent.observe(viewLifecycleOwner) { allSubscribers ->
            val mAdapter: SubscriberListAdapter = SubscriberListAdapter(allSubscribers)

            with(binding.recyclerSubscribers) {
                setHasFixedSize(true)
                adapter = mAdapter
            }
        }
    }


    private fun configureViewListeners() {
        binding.fabSubscriberAdd.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.subscriberFragment)
        }
    }
}