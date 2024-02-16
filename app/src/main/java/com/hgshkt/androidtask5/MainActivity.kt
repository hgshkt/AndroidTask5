package com.hgshkt.androidtask5

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hgshkt.androidtask5.api.ApiClient.client
import com.hgshkt.androidtask5.api.ApiInterface
import com.hgshkt.androidtask5.data.repository.model.SuperHero
import com.hgshkt.androidtask5.fragments.details.DetailsFragment
import com.hgshkt.androidtask5.fragments.details.model.SuperHeroDetail
import com.hgshkt.androidtask5.fragments.list.ListFragment
import com.hgshkt.androidtask5.data.mappers.ImageSizeType
import com.hgshkt.androidtask5.data.mappers.toDetail
import com.hgshkt.androidtask5.data.mappers.toDisplay
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var listFragment: ListFragment
    private var detailsFragment: DetailsFragment? = null

    private var listDetail = mutableListOf<SuperHeroDetail>()
    private var repository = client.create<ApiInterface>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    override fun onStart() {
        super.onStart()

        request { list, error ->
            handleResponse(list, error)
        }

        listFragment.onItemClick = { superHero ->
            detailsFragment = DetailsFragment()

            detailsFragment!!.superHero = listDetail.find {
                it.name == superHero.name
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.listFragmentContainer, detailsFragment!!)
                .addToBackStack("details_fragment")
                .commit()
        }
    }

    private fun request(
        handleResponse: (List<SuperHero>, Throwable?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = repository.getSuperHeroes()
            withContext(Dispatchers.Main) {
                handleResponse(list, null)
            }
        }
    }


    private fun handleResponse(
        list: List<SuperHero>,
        error: Throwable?
    ) {
        listFragment.list = list.map {
            it.toDisplay(ImageSizeType.MD)
        }.toMutableList()

        listDetail = list.map {
            it.toDetail(ImageSizeType.LG)
        }.toMutableList()

        error?.let {
            showError(error)
        }
    }

    private fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun init() {
        listFragment =
            supportFragmentManager.findFragmentById(R.id.listFragmentContainer) as ListFragment
    }
}