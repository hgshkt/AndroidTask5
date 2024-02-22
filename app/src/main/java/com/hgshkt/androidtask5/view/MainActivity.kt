package com.hgshkt.androidtask5.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hgshkt.androidtask5.R
import com.hgshkt.androidtask5.view.fragments.details.DetailsFragment
import com.hgshkt.androidtask5.view.fragments.details.model.SuperHeroDetail
import com.hgshkt.androidtask5.view.fragments.list.ListFragment
import com.hgshkt.androidtask5.view.model.SuperHeroDisplay
import com.hgshkt.androidtask5.view.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var listFragment: ListFragment
    private var detailsFragment: DetailsFragment? = null

    private var listDetail = mutableListOf<SuperHeroDetail>()

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    override fun onStart() {
        super.onStart()

        viewModel.uiState.observe(this) { updatedState ->
            when (updatedState) {
                is MainViewModel.UIState.EmptyList -> updateUIStateEmpty()
                is MainViewModel.UIState.FilledList -> updateUIStateFilled(updatedState.list)
                is MainViewModel.UIState.DetailedScreen -> updateUIStateDetail(updatedState.superHero)
                is MainViewModel.UIState.Error -> updateUIStateError(updatedState.errorMessage)
            }
        }

        viewModel.getSuperHeroes()

        listFragment.onItemClick = { superHero ->
            viewModel.openDetailScreen(superHero)
        }
    }

    private fun updateUIStateError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun updateUIStateDetail(superHero: SuperHeroDetail) {
        val detailsFragment = detailsFragment ?: DetailsFragment()
            .apply {
                this.superHero = superHero
            }

        supportFragmentManager.beginTransaction()
            .replace(R.id.listFragmentContainer, detailsFragment)
            .addToBackStack("details_fragment")
            .commit()
    }

    private fun updateUIStateFilled(list: List<SuperHeroDisplay>) {
        listFragment.list = list.toMutableList()
    }

    private fun updateUIStateEmpty() {
        listDetail.clear()
    }

    private fun init() {
        listFragment =
            supportFragmentManager.findFragmentById(R.id.listFragmentContainer) as ListFragment
    }
}