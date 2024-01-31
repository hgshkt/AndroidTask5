package com.hgshkt.androidtask5

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hgshkt.androidtask5.api.ApiClient.client
import com.hgshkt.androidtask5.api.ApiInterface
import com.hgshkt.androidtask5.fragments.ListFragment
import com.hgshkt.androidtask5.model.SuperHeroDisplay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        request { list, error ->
            handleResponse(list, error)
        }
    }

    private fun request(
        handleResponse: (MutableList<SuperHeroDisplay>, Throwable?) -> Unit
    ) {
        client.create(ApiInterface::class.java)
            .getSuperHeroes()
            .subscribeOn(Schedulers.io())
            .map { response ->
                response.map { superHero ->
                    superHero.toDisplay(ImageSizeType.MD)
                }.toMutableList()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list, error ->
                handleResponse(list, error)
            }
    }



    private fun handleResponse(
        list: MutableList<SuperHeroDisplay>,
        error: Throwable?
    ) {
        listFragment.list = list
        error?.let {
            showError(error)
        }
    }

    private fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun init() {
        listFragment = supportFragmentManager.findFragmentById(R.id.listFragmentContainer) as ListFragment
    }
}