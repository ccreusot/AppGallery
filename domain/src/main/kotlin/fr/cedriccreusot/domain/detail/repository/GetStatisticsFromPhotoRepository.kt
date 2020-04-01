package fr.cedriccreusot.domain.detail.repository

import fr.cedriccreusot.domain.model.Statistics
import java.lang.Exception

interface GetStatisticsFromPhotoRepository {
    @Throws(GetStatisticsFromPhotoException::class)
    fun statistics(photoId : String) : Statistics
}

class GetStatisticsFromPhotoException: Exception()