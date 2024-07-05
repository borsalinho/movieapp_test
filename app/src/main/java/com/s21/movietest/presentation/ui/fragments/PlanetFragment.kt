package com.s21.movietest.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.s21.movietest.R
import com.s21.movietest.app.MyApp
import com.s21.movietest.databinding.FragmentPlanetBinding
import com.s21.movietest.presentation.ui.mainactivity.MainActivityViewModel
import javax.inject.Inject

class PlanetFragment : Fragment() {

    private lateinit var binding: FragmentPlanetBinding
    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MyApp).myAppComponent.inject(this)
        binding = FragmentPlanetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(view)

        getPlanet()
        observeViewModel()
    }

    private fun getPlanet(){
        mainActivityViewModel.getPlanet()
    }

    private fun observeViewModel() {
        mainActivityViewModel.planet.observe(viewLifecycleOwner, Observer { planet ->

            binding.planetName.text = planet.name
            binding.planetDiameter.text = planet.diameter
            binding.planetGrav.text = planet.gravity
            binding.planetKlimat.text = planet.climate
            binding.planetMestnost.text = planet.terrain
            binding.planetPopulation.text = planet.population
        })

        mainActivityViewModel.personName.observe(viewLifecycleOwner, Observer { planetNmae ->
            (requireActivity() as AppCompatActivity).supportActionBar?.
            setTitle(planetNmae.name)
        })
    }

    private fun setupToolbar(view: View) {
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}