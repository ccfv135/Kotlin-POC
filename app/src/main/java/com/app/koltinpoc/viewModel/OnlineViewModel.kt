package com.app.koltinpoc.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.koltinpoc.di.DBRepository
import com.app.koltinpoc.di.NetworkRepository
import com.app.koltinpoc.model.AnimeData
import com.app.koltinpoc.model.AnimeInfo
import com.app.koltinpoc.utils.DataHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class OnlineViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DBRepository
) : ViewModel() {

    private val _animeTop = MutableLiveData<DataHandler<List<AnimeData>>>()
    val animeTop: LiveData<DataHandler<List<AnimeData>>> = _animeTop

    private val _animeSeasonsNowTop = MutableLiveData<DataHandler<List<AnimeData>>>()
    val animeSeasonsNowTop: LiveData<DataHandler<List<AnimeData>>> = _animeSeasonsNowTop

    private val _animeSearched = MutableLiveData<DataHandler<AnimeData>>()
    val animeSearched: LiveData<DataHandler<AnimeData>> = _animeSearched

    fun getAnimeTop() {
        viewModelScope.launch {
            val response = networkRepository.getAnimeTop()
            handleResponse(response)
        }
    }

    fun getAnimeSeasonsNow() {
        viewModelScope.launch {
            val response = networkRepository.getAnimeSeasonsNow()
            handleSeasonsNowResponse(response)
        }
    }


    private fun handleResponse(response: Response<AnimeInfo>) {
        if (response.isSuccessful) {
            response.body()?.let {
                saveAnimeInfo(it)
            }
        }
    }

    private fun handleSeasonsNowResponse(response: Response<AnimeInfo>) {
        if (response.isSuccessful) {
            response.body()?.let {
                saveAnimeSeasonsNowInfo(it)
            }
        }
    }


    fun getAllLocalAnimeInfo() {
        _animeTop.postValue(DataHandler.LOADING())
        viewModelScope.launch(Dispatchers.IO) {
            _animeTop.postValue(DataHandler.SUCCESS(dbRepository.getAllAnimeInfo()))
        }
    }

    fun getAllLocalAnimeSeasonNowInfo() {
        _animeTop.postValue(DataHandler.LOADING())
        viewModelScope.launch(Dispatchers.IO) {
            _animeTop.postValue(DataHandler.SUCCESS(dbRepository.getAllSeasonsAnimeInfo()))
        }
    }


    private fun saveAnimeInfo(animeInfo: AnimeInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = dbRepository.insertAnimeInfo(animeInfo)) {
                is DataHandler.SUCCESS -> {
                    val data = dbRepository.getAllAnimeInfo()
                    _animeTop.postValue(DataHandler.SUCCESS(data))
                }
                is DataHandler.ERROR -> {
                    _animeTop.postValue(DataHandler.ERROR(message = result.message.toString()))
                }
                else -> {}
            }
        }
    }

    private fun saveAnimeSeasonsNowInfo(animeInfo: AnimeInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = dbRepository.insertAnimeSeasonsNowInfo(animeInfo)) {
                is DataHandler.SUCCESS -> {
                    val data = dbRepository.getAllSeasonsAnimeInfo()
                    _animeSeasonsNowTop.postValue(DataHandler.SUCCESS(data))
                }
                is DataHandler.ERROR -> {
                    _animeSeasonsNowTop.postValue(DataHandler.ERROR(message = result.message.toString()))
                }
                else -> {}
            }
        }
    }

     fun searchAnimeByTitle(title:String){
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = dbRepository.searchAnimeByTitle(title)){
                is DataHandler.SUCCESS ->{
                    _animeSearched.postValue(result)
                }
                is DataHandler.ERROR ->{
                    _animeSearched.postValue(DataHandler.ERROR(message = result.message.toString()))
                }
                else -> {}
            }
        }
    }


    fun deleteAllElements() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = dbRepository.deleteAllElements()) {
                is DataHandler.SUCCESS -> {
                    _animeTop.postValue(DataHandler.SUCCESS(emptyList()))
                }
                is DataHandler.ERROR -> {
                    _animeTop.postValue(DataHandler.ERROR(message = result.message.toString()))
                }
                else -> {}
            }
        }
    }
}