package com.emanuel.mysubscribers.ui.subscriberlist

import androidx.lifecycle.ViewModel
import com.emanuel.mysubscribers.repository.SubscriberRepository

class SubscriberListViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val allSubscriberEvent = repository.getAllSubscribers()
}