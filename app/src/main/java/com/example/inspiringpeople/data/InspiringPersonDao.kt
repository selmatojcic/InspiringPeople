package com.example.inspiringpeople.data

import androidx.room.*
import com.example.inspiringpeople.model.InspiringPerson

@Dao
interface InspiringPersonDao {
    @Query("SELECT * FROM inspiringPeople")
    fun getInspiringPeople(): List<InspiringPerson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(inspiringPerson: InspiringPerson)

    @Delete
    fun delete(inspiringPerson: InspiringPerson)

    @Query("DELETE FROM inspiringPeople")
    fun deleteAll()

    @Query("SELECT * FROM inspiringPeople WHERE id=:id")
    fun getInspiringPerson(id: Long): InspiringPerson

    @Update
    fun update(inspiringPerson: InspiringPerson)

    @Query("UPDATE inspiringPeople SET name=:name WHERE id = :id")
    fun updateName(name: String?, id: Long)

    @Query("UPDATE inspiringPeople SET date=:date WHERE id = :id")
    fun updateDate(date: String?, id: Long)

    @Query("UPDATE inspiringPeople SET details=:details WHERE id = :id")
    fun updateDetails(details: String?, id: Long)

    @Query("UPDATE inspiringPeople SET imageUrl=:imageUrl WHERE id = :id")
    fun updateImageUrl(imageUrl: String?, id: Long)

    @Query("UPDATE inspiringPeople SET firstQuote=:firstQuote WHERE id = :id")
    fun updateFirstQuote(firstQuote: String?, id: Long)

    @Query("UPDATE inspiringPeople SET secondQuote=:secondQuote WHERE id = :id")
    fun updateSecondQuote(secondQuote: String?, id: Long)

}