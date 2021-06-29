package com.emanuel.mysubscribers.repository

import com.emanuel.mysubscribers.data.db.dao.SubscriberDAO
import com.emanuel.mysubscribers.data.db.entity.SubscriberEntity

/*Classe de fonte de dados, que recebe um DAO para chamar as operações e salvar no banco.
Herda de classe repository e implementa os métedos chamando os métodos da classe DAO.
*/
class DatabaseDataSource(private val subscriberDAO: SubscriberDAO) : SubscriberRepository {

    override suspend fun insertSubscriber(name: String, email: String): Long {

        val subscriber = SubscriberEntity(name = name, email = email)

        return subscriberDAO.insert(subscriber)
    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {

        val subscriber = SubscriberEntity(id = id, name = name, email = email)

        subscriberDAO.update(subscriber)
    }

    override suspend fun deleteSubscriber(id: Long) {
        subscriberDAO.delete(id)
    }

    override suspend fun deleteAllSubscribers() {
        subscriberDAO.deleteAll()
    }

    override suspend fun getAllSubscribers(): List<SubscriberEntity> {
        return subscriberDAO.getAll()
    }

}