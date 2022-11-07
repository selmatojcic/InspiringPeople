package com.example.inspiringpeople.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.inspiringpeople.data.InspiringPeopleDatabaseBuilder
import com.example.inspiringpeople.data.InspiringPeopleRepository
import com.example.inspiringpeople.databinding.ActivityNewInspiringPersonBinding
import com.example.inspiringpeople.model.InspiringPerson

class NewInspiringPersonActivity : AppCompatActivity() {
    private lateinit var newInspiringPersonBinding: ActivityNewInspiringPersonBinding
    private val inspiringPersonDao =
        InspiringPeopleDatabaseBuilder.getInstance().inspiringPersonDao()
    private val inspiringPeopleRepository = InspiringPeopleRepository(inspiringPersonDao)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newInspiringPersonBinding = ActivityNewInspiringPersonBinding.inflate(layoutInflater)
        newInspiringPersonBinding.btnSave.setOnClickListener { saveInspiringPerson() }
        setContentView(newInspiringPersonBinding.root)
    }

    private fun saveInspiringPerson() {
        val name = newInspiringPersonBinding.etNewInspiringPersonNameInput.text.toString()
        val date = newInspiringPersonBinding.etNewInspiringPersonDateInput.text.toString()
        val details = newInspiringPersonBinding.etNewInspiringPersonDetailsInput.text.toString()
        val imageUrl = newInspiringPersonBinding.etNewInspiringPersonUrlInput.text.toString()
        val firstQuote =
            newInspiringPersonBinding.etNewInspiringPersonFirstQuoteInput.text.toString()
        val secondQuote =
            newInspiringPersonBinding.etNewInspiringPersonSecondQuoteInput.text.toString()

        val inspiringPerson =
            InspiringPerson(0, name, date, details, imageUrl, firstQuote, secondQuote)
        inspiringPeopleRepository.insertInspiringPerson(inspiringPerson)
        finish()
    }
}