package fr.cedriccreusot.domain.detail.usecase

import fr.cedriccreusot.domain.detail.repository.GetPhotosFromUserRepository
import fr.cedriccreusot.domain.model.Photo

interface FetchPhotosFromUserUseCase {

    fun invoke(userName: String): List<Photo>

    companion object {
        fun create(repository: GetPhotosFromUserRepository): FetchPhotosFromUserUseCase =
            FetchPhotosFromUserUseCaseImpl(repository)
    }
}

internal class FetchPhotosFromUserUseCaseImpl(private val repository: GetPhotosFromUserRepository) :
    FetchPhotosFromUserUseCase {

    override fun invoke(userName: String): List<Photo> {
        require(userName.isNotEmpty())
        val result = runCatching {
            repository.photos(userName)
        }
        return result.getOrNull() ?: emptyList()
    }
}