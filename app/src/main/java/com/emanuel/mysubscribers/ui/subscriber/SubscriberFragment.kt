package com.emanuel.mysubscribers.ui.subscriber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emanuel.mysubscribers.R

class SubscriberFragment : Fragment() {

    private lateinit var viewModel: SubscriberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.subscriber_fragment, container, false)
    }


}