package com.example.inspiringpeople.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.inspiringpeople.GlideApp
import com.example.inspiringpeople.R
import com.example.inspiringpeople.databinding.ItemInspiringPersonBinding
import com.example.inspiringpeople.model.InspiringPerson

class InspiringPeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(inspiringPerson: InspiringPerson) {
        val itemInspiringPersonBinding = ItemInspiringPersonBinding.bind(itemView)

        itemInspiringPersonBinding.tvInspiringPersonItemName.text = inspiringPerson.name
        itemInspiringPersonBinding.tvInspiringPersonItemDetails.text = inspiringPerson.details
        itemInspiringPersonBinding.tvInspiringPersonItemDate.text = inspiringPerson.date

        GlideApp.with(itemView)
            .load(inspiringPerson.imageUrl)
            .placeholder(R.drawable.placeholder_person)
            .centerCrop()
            .into(itemInspiringPersonBinding.ivInspiringPersonItemPhoto)

        val quotesList: MutableList<String> = mutableListOf()
        quotesList.add(inspiringPerson.firstQuote)
        quotesList.add(inspiringPerson.secondQuote)

        itemInspiringPersonBinding.ivInspiringPersonItemPhoto.setOnClickListener {
            Toast.makeText(
                itemInspiringPersonBinding.ivInspiringPersonItemPhoto.context,
                quotesList.random(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}