package com.emanuel.mysubscribers.ui.subscriber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.emanuel.mysubscribers.R
import com.emanuel.mysubscribers.data.db.AppDatabase
import com.emanuel.mysubscribers.data.db.dao.SubscriberDAO
import com.emanuel.mysubscribers.databinding.SubscriberFragmentBinding
import com.emanuel.mysubscribers.extension.hideKeyboard
import com.emanuel.mysubscribers.repository.DatabaseDataSource
import com.emanuel.mysubscribers.repository.SubscriberRepository
import com.google.android.material.snackbar.Snackbar

class SubscriberFragment : Fragment() {

    private var _binding: SubscriberFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: SubscriberFragmentArgs by navArgs()

    //Cria uma instância do viewModel com o padrão factory com injeção de dependência
    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO = AppDatabase.getInstance(requireContext()).subscriberDAO

                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberViewModel(repository) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubscriberFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillFields()
        observeEvents()
        setListeners()
    }

    private fun fillFields() {
        args.subscriber?.let { subscriber ->
            binding.buttonAdd.setText(R.string.button_update)
            binding.inputName.setText(subscriber.name)
            binding.inputEmail.setText(subscriber.email)
        }
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is SubscriberViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()

                    //Retorna para a tela anterior da pilha
                    findNavController().popBackStack()
                }
                is SubscriberViewModel.SubscriberState.Updated -> {
                    clearFields()
                    hideKeyboard()
                    findNavController().popBackStack()
                }
            }
        }

        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        with(binding) {
            inputName.text?.clear()
            inputEmail.text?.clear()
        }
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        binding.buttonAdd.setOnClickListener {
            val name = binding.inputName.text.toString()
            val email = binding.inputEmail.text.toString()

            viewModel.addOrUpdate(name, email, args.subscriber?.id ?: 0)
        }
    }


}