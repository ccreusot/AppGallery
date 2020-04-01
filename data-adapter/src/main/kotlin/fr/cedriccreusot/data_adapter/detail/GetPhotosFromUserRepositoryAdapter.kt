package fr.cedriccreusot.data_adapter.detail

import fr.cedriccreusot.data_adapter.extensions.toDomainPhoto
import fr.cedriccreusot.data_adapter.retrofit.UnsplashService
import fr.cedriccreusot.domain.detail.repository.GetPhotosFromUserException
import fr.cedriccreusot.domain.detail.repository.GetPhotosFromUserRepository
import fr.cedriccreusot.domain.model.Photo

class GetPhotosFromUserRepositoryAdapter(private val token: String, private val service: UnsplashService) : GetPhotosFromUserRepository {
    override fun photos(userName: String): List<Photo> {
        val result = runCatching {
            service.userPhotos(token, userName).execute()
        }
        return result.run {
            if (isFailure) {
                throw GetPhotosFromUserException()
            }
            getOrNull()?.body()?.map { photo ->
                photo.toDomainPhoto()
            } ?: emptyList()
        }
    }
}