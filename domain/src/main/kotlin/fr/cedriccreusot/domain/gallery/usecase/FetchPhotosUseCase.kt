package fr.cedriccreusot.domain.gallery.usecase

import fr.cedriccreusot.domain.model.Photo
import fr.cedriccreusot.domain.gallery.repository.GetPhotosRepository

interface FetchPhotosUseCase {

    fun invoke(): List<Photo>

    companion object {
        fun create(repository: GetPhotosRepository): FetchPhotosUseCase =
            FetchPhotosUseCaseImpl(repository)
    }
}

internal class FetchPhotosUseCaseImpl(private val repository: GetPhotosRepository) :
    FetchPhotosUseCase {

    override fun invoke(): List<Photo> {
        val result = runCatching {
            repository.photos()
        }
        return result.getOrNull() ?: emptyList()
    }
}