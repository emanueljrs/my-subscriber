package com.emanuel.mysubscribers.ui.subscriberlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emanuel.mysubscribers.data.db.entity.SubscriberEntity
import com.emanuel.mysubscribers.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberListViewModel(private val repository: SubscriberRepository) : ViewModel() {

//    Padrão back property para deixar a variavél mais segura
    private val _allSubscribersEvent = MutableLiveData<List<SubscriberEntity>>()
    val allSubscribersEvent: LiveData<List<SubscriberEntity>>
        get() = _allSubscribersEvent

    //Cria um escopo de Coroutines para executar a fun getAll() que está anotada como suspend
    fun getAllSubscribers() = viewModelScope.launch {
        _allSubscribersEvent.postValue(repository.getAllSubscribers())
    }
}