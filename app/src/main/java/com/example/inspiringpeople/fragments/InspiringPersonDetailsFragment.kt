package com.example.inspiringpeople.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.inspiringpeople.GlideApp
import com.example.inspiringpeople.OnInspiringPersonEditDetailsSelectedListener
import com.example.inspiringpeople.R
import com.example.inspiringpeople.data.InspiringPeopleDatabaseBuilder
import com.example.inspiringpeople.data.InspiringPeopleRepository
import com.example.inspiringpeople.databinding.FragmentInspiringPersonDetailsBinding
import com.example.inspiringpeople.model.InspiringPerson


class InspiringPersonDetailsFragment : Fragment() {
    lateinit var inspiringPersonDetailsBinding: FragmentInspiringPersonDetailsBinding
    private val inspiringPersonDao =
        InspiringPeopleDatabaseBuilder.getInstance().inspiringPersonDao()
    private val inspiringPeopleRepository = InspiringPeopleRepository(inspiringPersonDao)

    private lateinit var onInspiringPersonEditDetailsSelectedListener:
            OnInspiringPersonEditDetailsSelectedListener

    companion object {
        const val TAG = "Details"
        const val KEY = "InspiringPerson"

        fun create(inspiringPerson: InspiringPerson): InspiringPersonDetailsFragment {
            val args = Bundle()
            args.putSerializable(KEY, inspiringPerson)
            val fragment = InspiringPersonDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inspiringPersonDetailsBinding = FragmentInspiringPersonDetailsBinding.inflate(
            inflater,
            container,
            false
        )

        arguments?.let {
            val inspiringPerson = it.getSerializable(KEY) as InspiringPerson
            inspiringPersonDetailsBinding.tvInspiringPersonName.text = inspiringPerson.name
            inspiringPersonDetailsBinding.tvInspiringPersonDate.text = inspiringPerson.date
            inspiringPersonDetailsBinding.tvInspiringPersonDetails.text = inspiringPerson.details

            GlideApp.with(this)
                .load(inspiringPerson.imageUrl)
                .placeholder(R.drawable.placeholder_person)
                .centerCrop()
                .into(inspiringPersonDetailsBinding.ivInspiringPersonPhoto)

            inspiringPersonDetailsBinding.btnEditInformation.setOnClickListener {
                onInspiringPersonEditDetailsSelectedListener
                    .onInspiringPersonEditDetailsSelected(
                        inspiringPeopleRepository.getInspiringPerson(inspiringPerson.id)
                    )
            }
        }

        setHasOptionsMenu(true)

        return inspiringPersonDetailsBinding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInspiringPersonEditDetailsSelectedListener) {
            onInspiringPersonEditDetailsSelectedListener = context
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteInspiringPerson()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteInspiringPerson() {
        arguments?.let {
            val inspiringPerson = it.getSerializable(KEY) as InspiringPerson

            val builder = AlertDialog.Builder(requireContext())

            builder.setTitle("Are you sure you want to delete this person?")
                .setPositiveButton("Yes") { _, _ ->
                    inspiringPeopleRepository.deleteInspiringPerson(inspiringPerson)
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.cancel()
                }
                .create()
                .show()
        }
    }
}