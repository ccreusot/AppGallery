package fr.cedriccreusot.appgallery.gallery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.cedriccreusot.appgallery.common.state.LoadingState
import fr.cedriccreusot.domain.gallery.usecase.FetchPhotosUseCase
import fr.cedriccreusot.domain.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GalleryViewModel(private val useCase: FetchPhotosUseCase) : ViewModel() {
    private val loadingStates: Map<LoadingState, Int> = mapOf(
        LoadingState.IS_LOADING to 0,
        LoadingState.IS_EMPTY to 1,
        LoadingState.IS_LOADED to 2
    )
    val loadingState: MutableLiveData<Int> = MutableLiveData(loadingStates.getValue(LoadingState.IS_LOADING))

    val photos: MutableLiveData<List<Photo>> by lazy {
        fetchPhotos()
        MutableLiveData<List<Photo>>(emptyList())
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            loadingState.value = loadingStates[LoadingState.IS_LOADING]
            val result = withContext(Dispatchers.IO) {
                useCase.invoke()
            }

            loadingState.value = loadingStates[LoadingState.IS_LOADED]
            if(result.isEmpty()) {
                loadingState.value = loadingStates[LoadingState.IS_EMPTY]
            }
            photos.value = result
        }
    }
}