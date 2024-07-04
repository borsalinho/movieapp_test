package com.s21.movietest.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.s21.movietest.app.MyApp
import com.s21.movietest.databinding.FragmentPersonBinding
import com.s21.movietest.presentation.adapters.PersonAdapter
import com.s21.movietest.presentation.models.PersonViewData
import com.s21.movietest.presentation.ui.mainactivity.MainActivityViewModel
import javax.inject.Inject

class PersonsFragment : Fragment() {

    private lateinit var binding: FragmentPersonBinding
    private lateinit var personsAdapter: PersonAdapter
    @Inject lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MyApp).myAppComponent.inject(this)
        binding = FragmentPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPersons()
        setupRecyclerView()
        observeViewModel()
    }

    private fun getPersons(){
        mainActivityViewModel.getPersons()
    }

    private fun setupRecyclerView() {
        personsAdapter = PersonAdapter(emptyList(), object : PersonAdapter.OnItemClickListener {
            override fun onItemClick(person: PersonViewData) {

            }
        })
        binding.personsAdapter.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = personsAdapter
        }
    }

    private fun observeViewModel() {
        mainActivityViewModel.persons.observe(viewLifecycleOwner, Observer { persons ->
            personsAdapter.updatePersons(persons)
        })
    }

}