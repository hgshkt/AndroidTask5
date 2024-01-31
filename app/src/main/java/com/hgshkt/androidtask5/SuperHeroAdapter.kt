package com.hgshkt.androidtask5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hgshkt.androidtask5.model.SuperHeroDisplay

class SuperHeroAdapter(
    private val items: MutableList<SuperHeroDisplay>,
    private val onItemClick: (SuperHeroDisplay) -> Unit
) : RecyclerView.Adapter<SuperHeroRecyclerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuperHeroRecyclerViewHolder {
        val listItemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.super_hero_item_layout, parent, false)

        return SuperHeroRecyclerViewHolder(listItemView)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(
        holder: SuperHeroRecyclerViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            onItemClick(items[position])
        }

        val superHero = items[position]
        holder.name.text = superHero.name

        Glide.with(holder.itemView.context)
            .load(superHero.imageUrl)
            .into(holder.image)
    }
}

class SuperHeroRecyclerViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    val image: ImageView = view.findViewById(R.id.super_hero_image)
    val name: TextView = view.findViewById(R.id.nameTextView)
}