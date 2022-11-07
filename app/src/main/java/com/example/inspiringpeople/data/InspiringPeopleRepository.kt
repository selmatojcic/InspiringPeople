package com.example.inspiringpeople.data

import com.example.inspiringpeople.model.InspiringPerson

class InspiringPeopleRepository(private val inspiringPersonDao: InspiringPersonDao) {
    fun getInspiringPeople(): List<InspiringPerson> = inspiringPersonDao.getInspiringPeople()

    fun getInspiringPerson(id: Long): InspiringPerson = inspiringPersonDao.getInspiringPerson(id)

    fun insertInspiringPerson(inspiringPerson: InspiringPerson) =
        inspiringPersonDao.insert(inspiringPerson)

    fun deleteInspiringPerson(inspiringPerson: InspiringPerson) =
        inspiringPersonDao.delete(inspiringPerson)

    fun deleteAll() = inspiringPersonDao.deleteAll()

    fun updateName(name: String, id: Long) {
        inspiringPersonDao.updateName(name, id)
    }

    fun updateDate(date: String, id: Long) {
        inspiringPersonDao.updateDate(date, id)
    }

    fun updateDetails(details: String, id: Long) {
        inspiringPersonDao.updateDetails(details, id)
    }

    fun updateImageUrl(imageUrl: String, id: Long) {
        inspiringPersonDao.updateImageUrl(imageUrl, id)
    }

    fun updateFirstQuote(firstQuote: String, id: Long) {
        inspiringPersonDao.updateFirstQuote(firstQuote, id)
    }

    fun updateSecondQuote(secondQuote: String, id: Long) {
        inspiringPersonDao.updateSecondQuote(secondQuote, id)
    }
}