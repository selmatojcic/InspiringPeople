package com.example.inspiringpeople.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inspiringpeople.OnInspiringPersonSelectedListener
import com.example.inspiringpeople.R
import com.example.inspiringpeople.adapters.InspiringPeopleAdapter
import com.example.inspiringpeople.data.InspiringPeopleDatabaseBuilder
import com.example.inspiringpeople.data.InspiringPeopleRepository
import com.example.inspiringpeople.databinding.FragmentInspiringPeopleListBinding

class InspiringPeopleListFragment : Fragment() {
    private lateinit var inspiringPeopleListBinding: FragmentInspiringPeopleListBinding
    private val inspiringPersonDao =
        InspiringPeopleDatabaseBuilder.getInstance().inspiringPersonDao()
    private val inspiringPeopleRepository = InspiringPeopleRepository(inspiringPersonDao)
    private lateinit var onInspiringInspiringPersonSelectedListener:
            OnInspiringPersonSelectedListener

    companion object {
        const val TAG = "List"
        fun create(): InspiringPeopleListFragment {
            return InspiringPeopleListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inspiringPeopleListBinding = FragmentInspiringPeopleListBinding.inflate(
            inflater,
            container,
            false
        )
        setupRecyclerView()

        setHasOptionsMenu(true)

        return inspiringPeopleListBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInspiringPersonSelectedListener) {
            onInspiringInspiringPersonSelectedListener = context
        }
    }

    override fun onResume() {
        super.onResume()
        (inspiringPeopleListBinding.rvInspiringPeople.adapter as InspiringPeopleAdapter)
            .refreshData(inspiringPeopleRepository.getInspiringPeople())
    }

    private fun setupRecyclerView() {
        inspiringPeopleListBinding.rvInspiringPeople.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        inspiringPeopleListBinding.rvInspiringPeople.adapter =
            InspiringPeopleAdapter(
                inspiringPeopleRepository.getInspiringPeople(),
                onInspiringInspiringPersonSelectedListener
            )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllInspiringPeople()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllInspiringPeople() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Are you sure you want to delete everyone?")
            .setPositiveButton("Yes") { _, _ ->
                inspiringPeopleRepository.deleteAll()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }
}