package com.hgshkt.androidtask5.view.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hgshkt.androidtask5.R
import com.hgshkt.androidtask5.view.SuperHeroAdapter
import com.hgshkt.androidtask5.view.model.SuperHeroDisplay

class ListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    var onItemClick: (SuperHeroDisplay) -> Unit = {}

    private var adapter: SuperHeroAdapter? = null

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
        return inflater.inflate(R.layout.list_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        updateUI()
    }

    private fun updateUI() {
        adapter = SuperHeroAdapter(list) { item ->
            onItemClick(item)
        }
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun init(view: View?) {
        recyclerView = view?.findViewById(R.id.recyclerView)
    }
}