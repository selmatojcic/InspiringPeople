package com.example.inspiringpeople.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.inspiringpeople.OnInspiringPersonEditDetailsSelectedListener
import com.example.inspiringpeople.OnInspiringPersonSelectedListener
import com.example.inspiringpeople.R
import com.example.inspiringpeople.databinding.ActivityMainBinding
import com.example.inspiringpeople.fragments.InspiringPeopleListFragment
import com.example.inspiringpeople.fragments.InspiringPersonDetailsFragment
import com.example.inspiringpeople.fragments.InspiringPersonEditDetailsFragment
import com.example.inspiringpeople.model.InspiringPerson

class MainActivity : AppCompatActivity(),
    OnInspiringPersonSelectedListener,
    OnInspiringPersonEditDetailsSelectedListener {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mainBinding.fabAddPerson.setOnClickListener { createNewInspiringPerson() }
        setContentView(mainBinding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fl_fragmentContainer,
                    InspiringPeopleListFragment.create(),
                    InspiringPeopleListFragment.TAG
                )
                .commit()
        }
    }

    private fun createNewInspiringPerson() {
        val newInspiringPersonIntent =
            Intent(this, NewInspiringPersonActivity::class.java)
        startActivity(newInspiringPersonIntent)
    }

    override fun onInspiringPersonSelected(inspiringPerson: InspiringPerson) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fl_fragmentContainer,
                InspiringPersonDetailsFragment.create(inspiringPerson),
                InspiringPersonDetailsFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onInspiringPersonEditDetailsSelected(inspiringPerson: InspiringPerson) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fl_fragmentContainer,
                InspiringPersonEditDetailsFragment.create(inspiringPerson),
                InspiringPersonEditDetailsFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }
}