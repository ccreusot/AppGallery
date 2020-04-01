package fr.cedriccreusot.domain.detail.usecase

import fr.cedriccreusot.domain.detail.repository.GetStatisticsFromPhotoException
import fr.cedriccreusot.domain.detail.repository.GetStatisticsFromPhotoRepository
import fr.cedriccreusot.domain.model.Statistics

interface FetchPhotoStatisticsUseCase {
    @Throws(GetStatisticsFromPhotoException::class)
    fun invoke(photoId: String): Statistics

    companion object {
        fun create(repository: GetStatisticsFromPhotoRepository): FetchPhotoStatisticsUseCase =
            FetchPhotoStatisticsUserCaseImpl(repository)
    }
}

internal class FetchPhotoStatisticsUserCaseImpl(private val repository: GetStatisticsFromPhotoRepository) :
    FetchPhotoStatisticsUseCase {

    override fun invoke(photoId: String): Statistics {
        require(photoId.isNotEmpty())
        return repository.statistics(photoId)
    }
}