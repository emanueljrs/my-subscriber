package com.emanuel.mysubscribers.repository

import androidx.lifecycle.LiveData
import com.emanuel.mysubscribers.data.db.entity.SubscriberEntity

/* Responsável por fazer a ligação entre o ViewModel e o Banco de Dados
*  Contém os mesmos métodos da classe DAO, mas recebendo os dados puros
*  Anotadas com SUSPEND pois irá trabalhar com Courotines.
*/
interface SubscriberRepository {

    suspend fun insertSubscriber(name: String, email: String): Long

    suspend fun updateSubscriber(id: Long, name: String, email: String)

    suspend fun deleteSubscriber(id: Long)

    suspend fun deleteAllSubscribers()

    fun getAllSubscribers(): LiveData<List<SubscriberEntity>>
}