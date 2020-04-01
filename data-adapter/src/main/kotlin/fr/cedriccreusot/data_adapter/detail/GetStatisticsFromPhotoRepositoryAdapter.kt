package fr.cedriccreusot.data_adapter.detail

import fr.cedriccreusot.data_adapter.extensions.toDomainStatistics
import fr.cedriccreusot.data_adapter.retrofit.UnsplashService
import fr.cedriccreusot.domain.detail.repository.GetStatisticsFromPhotoException
import fr.cedriccreusot.domain.detail.repository.GetStatisticsFromPhotoRepository
import fr.cedriccreusot.domain.model.Statistics

class GetStatisticsFromPhotoRepositoryAdapter(private val token: String, private val service: UnsplashService) : GetStatisticsFromPhotoRepository {
    override fun statistics(photoId: String): Statistics {
        val result = runCatching {
            service.statistics(token, photoId).execute()
        }
        return result.run {
            if (isFailure) {
                throw GetStatisticsFromPhotoException()
            }
            getOrNull()?.body().run {
                this?.toDomainStatistics()
            } ?: Statistics(0, 0, 0)
        }
    }
}