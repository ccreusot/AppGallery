package fr.cedriccreusot.data_adapter.gallery

import fr.cedriccreusot.data_adapter.extensions.toDomainPhoto
import fr.cedriccreusot.data_adapter.retrofit.UnsplashService
import fr.cedriccreusot.domain.gallery.repository.GetPhotosException
import fr.cedriccreusot.domain.gallery.repository.GetPhotosRepository
import fr.cedriccreusot.domain.model.Photo

class GetPhotosRepositoryAdapter(private val token: String, private val service: UnsplashService) : GetPhotosRepository {
    override fun photos(): List<Photo> {
        val result = runCatching {
            service.photos(token).execute()
        }
        return result.run {
            if (isFailure) {
                throw GetPhotosException()
            }
            getOrNull()?.body()?.map { photo ->
                photo.toDomainPhoto()
            } ?: emptyList()
        }
    }
}