package com.hgshkt.androidtask5.view.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hgshkt.androidtask5.data.mappers.ImageSizeType
import com.hgshkt.androidtask5.data.mappers.toDetail
import com.hgshkt.androidtask5.data.mappers.toDisplay
import com.hgshkt.androidtask5.data.repository.SuperHeroRepository
import com.hgshkt.androidtask5.data.repository.SuperHeroesResponse
import com.hgshkt.androidtask5.data.repository.model.Biography
import com.hgshkt.androidtask5.data.repository.model.Images
import com.hgshkt.androidtask5.data.repository.model.PowerStats
import com.hgshkt.androidtask5.data.repository.model.SuperHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MainViewModelTest {

    private val testHero = SuperHero(
        name = "name",
        images = Images(
            xs = "xs",
            sm = "sm",
            md = "md",
            lg = "lg"
        ),
        powerStats = PowerStats(
            intelligence = 0,
            strength = 1,
            speed = 1,
            durability = 10,
            power = 20,
            combat = 100
        ),
        biography = Biography(
            fullName = "full name",
            alterEgos = "alter ego",
            placeOfBirth = "place of birth",
            firstAppearance = "first appearance",
            publisher = "publisher"
        )
    )

    @get:Rule
    val testRule = InstantTaskExecutorRule()


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `display list filled after getSuperHeroes()`() {
        val repository = Mockito.mock(SuperHeroRepository::class.java)
        val successfulResponse = SuperHeroesResponse(
            body = listOf(testHero),
            isSuccessful = true,
            errorMassage = null
        )
        val viewModel = MainViewModel(repository)

        runBlocking {
            Mockito.`when`(repository.getSuperHeroes()).thenReturn(successfulResponse)
        }

        viewModel.getSuperHeroes()

        Assert.assertEquals(viewModel.displayList.isNotEmpty(), true)
    }

    @Test
    fun `detail list filled after getSuperHeroes()`() {
        val repository = Mockito.mock(SuperHeroRepository::class.java)
        val successfulResponse = SuperHeroesResponse(
            body = listOf(testHero),
            isSuccessful = true,
            errorMassage = null
        )
        val viewModel = MainViewModel(repository)

        runBlocking {
            Mockito.`when`(repository.getSuperHeroes()).thenReturn(successfulResponse)
        }

        viewModel.getSuperHeroes()

        Assert.assertEquals(viewModel.detailList.isNotEmpty(), true)
    }

    @Test
    fun `get success response after getSuperHeroes()`() {
        val repository = Mockito.mock(SuperHeroRepository::class.java)
        val successfulResponse = SuperHeroesResponse(
            body = listOf(testHero),
            isSuccessful = true,
            errorMassage = null
        )
        val displayList = successfulResponse.body!!.map { it.toDisplay(ImageSizeType.MD) }
        val viewModel = MainViewModel(repository)
        val eventList = mutableListOf<MainViewModel.UIState>()
        viewModel.uiState.observeForever {
            eventList.add(it)
        }

        runBlocking {
            Mockito.`when`(repository.getSuperHeroes()).thenReturn(successfulResponse)
        }

        viewModel.getSuperHeroes()

        Assert.assertEquals(MainViewModel.UIState.EmptyList, eventList[0])
        Assert.assertEquals(MainViewModel.UIState.FilledList(displayList), eventList[1])
    }

    @Test
    fun `detail state after openDetailScreen()`() {
        val repository = Mockito.mock(SuperHeroRepository::class.java)
        val viewModel = MainViewModel(repository)
        val successfulResponse = SuperHeroesResponse(
            body = listOf(testHero),
            isSuccessful = true,
            errorMassage = null
        )
        val eventList = mutableListOf<MainViewModel.UIState>()

        viewModel.uiState.observeForever {
            eventList.add(it)
        }

        runBlocking {
            Mockito.`when`(repository.getSuperHeroes()).thenReturn(successfulResponse)
        }

        viewModel.getSuperHeroes()
        viewModel.openDetailScreen(testHero.toDisplay(ImageSizeType.MD))

        Assert.assertEquals(MainViewModel.UIState.EmptyList, eventList[0])
        Assert.assertEquals(
            MainViewModel.UIState.DetailedScreen(
                testHero.toDetail(ImageSizeType.LG)
            ),
            eventList[2]
        )
    }
}