package com.hgshkt.androidtask5.fragments.list

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

    var onItemClick: (SuperHeroDisplay) -> Unit = {}

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
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        val adapter = SuperHeroAdapter(list) { item ->
            onItemClick(item)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun updateUI() {

    }

    private fun init(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
    }
}