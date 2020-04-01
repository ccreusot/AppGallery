package fr.cedriccreusot.appgallery.detail.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.cedriccreusot.appgallery.common.state.LoadingState
import fr.cedriccreusot.appgallery.detail.DetailActivityArgs
import fr.cedriccreusot.domain.detail.usecase.FetchPhotoStatisticsUseCase
import fr.cedriccreusot.domain.detail.usecase.FetchPhotosFromUserUseCase
import fr.cedriccreusot.domain.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class DetailViewModel(
    private val detailActivityArgs: DetailActivityArgs,
    private val statisticsUseCase: FetchPhotoStatisticsUseCase,
    private val ownerUserUseCase: FetchPhotosFromUserUseCase
) : ViewModel() {

    val photoUrl: String by lazy {
        detailActivityArgs.photoUrl
    }

    val description: String by lazy {
        detailActivityArgs.photoDescription
    }

    val descriptionVisibility: Int by lazy {
        if (detailActivityArgs.photoDescription.isEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    val date: String by lazy {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = formatter.parse(detailActivityArgs.photoDate)
        formatter.format(date!!)
    }

    val userFullName: String by lazy {
        detailActivityArgs.ownerFullName
    }

    val userProfilePhoto: String by lazy {
        detailActivityArgs.ownerProfilePhotoUrl
    }

    val color by lazy {
        detailActivityArgs.photoColor
    }

    private val loadingStates: Map<LoadingState, Int> = mapOf(
        LoadingState.IS_LOADING to 0,
        LoadingState.IS_LOADED to 1
    )
    val loadingState: MutableLiveData<Int> =
        MutableLiveData(loadingStates.getValue(LoadingState.IS_LOADED))

    val downloadStats: MutableLiveData<Int> = MutableLiveData(0)

    val likeStats: MutableLiveData<Int> = MutableLiveData(0)

    val photos: MutableLiveData<List<Photo>> = MutableLiveData(emptyList())

    init {
        viewModelScope.launch {
            loadingState.value = loadingStates.getValue(LoadingState.IS_LOADING)
            fetchStatistics()
            fetchUserPhotos()
            loadingState.value = loadingStates.getValue(LoadingState.IS_LOADED)
        }
    }

    private suspend fun fetchStatistics() {
        val result = withContext(Dispatchers.IO) {
            statisticsUseCase.invoke(detailActivityArgs.photoId)
        }

        downloadStats.value = result.download
        likeStats.value = result.likes
    }

    private suspend fun fetchUserPhotos() {
        val result = withContext(Dispatchers.IO) {
            ownerUserUseCase.invoke(detailActivityArgs.ownerUsername)
        }

        if (result.isEmpty()) {
            return
        }

        photos.value = result
    }
}