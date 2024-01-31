package com.hgshkt.androidtask5.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.hgshkt.androidtask5.fragments.details.model.SuperHeroDetail

class DetailsFragment : Fragment() {

    var superHero: SuperHeroDetail? = null
        set(value) {
            field = value
            updateUI(value)
        }

    private lateinit var imageView: ImageView

    private lateinit var nameTextView: TextView
    private lateinit var intelligenceTextView: TextView
    private lateinit var strengthTextView: TextView
    private lateinit var speedTextView: TextView
    private lateinit var durabilityTextView: TextView
    private lateinit var powerTextView: TextView
    private lateinit var combatTextView: TextView
    private lateinit var fullNameTextView: TextView
    private lateinit var alterEgosTextView: TextView
    private lateinit var placeOfBirthTextView: TextView
    private lateinit var firstAppearanceTextView: TextView
    private lateinit var publisherTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun updateUI(superHero: SuperHeroDetail?) {
        Glide.with(requireContext())
            .load(superHero?.imageUrl)
            .error(android.R.drawable.ic_menu_report_image)
            .into(imageView)

        nameTextView.text = "Name: ${superHero?.name}"
        intelligenceTextView.text = "Intelligence: ${superHero?.powerStats?.intelligence}"
        strengthTextView.text = "Strength: ${superHero?.powerStats?.intelligence}"
        speedTextView.text = "Speed: ${superHero?.powerStats?.intelligence}"
        durabilityTextView.text = "Durability: ${superHero?.powerStats?.intelligence}"
        powerTextView.text = "Power: ${superHero?.powerStats?.intelligence}"
        combatTextView.text = "Combat: ${superHero?.powerStats?.intelligence}"
        fullNameTextView.text = "Full Name: ${superHero?.biography?.fullName}"
        alterEgosTextView.text = "Alter Egos: ${superHero?.biography?.alterEgos}"
        placeOfBirthTextView.text = "Place of Berth: ${superHero?.biography?.placeOfBirth}"
        firstAppearanceTextView.text = "First Appearance: ${superHero?.biography?.firstAppearance}"
        publisherTextView.text = "Publisher: ${superHero?.biography?.publisher}"
    }
}