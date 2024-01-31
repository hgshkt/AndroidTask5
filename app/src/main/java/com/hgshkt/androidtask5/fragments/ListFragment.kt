package com.hgshkt.androidtask5.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hgshkt.androidtask5.R
import com.hgshkt.androidtask5.SuperHeroAdapter
import com.hgshkt.androidtask5.model.SuperHeroDisplay

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    var list = mutableListOf<SuperHeroDisplay>()
        set(value) {
            field = value
            updateUI()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init(container!!)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun updateUI() {
        val adapter = SuperHeroAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun init(container: ViewGroup) {
        recyclerView = container.findViewById(R.id.recyclerView)
    }
}