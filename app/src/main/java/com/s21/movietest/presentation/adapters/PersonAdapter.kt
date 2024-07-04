package com.s21.movietest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.s21.movietest.databinding.ItemPersonBinding

import com.s21.movietest.presentation.models.PersonViewData

class PersonAdapter(
    private var persons: List<PersonViewData>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(movie: PersonViewData)
    }

    class PersonViewHolder(
        val binding: ItemPersonBinding,
        private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: PersonViewData) {
            binding.personName.text = person.name
            binding.root.setOnClickListener { onItemClickListener.onItemClick(person) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(persons[position])
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    fun updatePersons(newPersons: List<PersonViewData>) {
        persons = newPersons
        notifyDataSetChanged()
    }
}