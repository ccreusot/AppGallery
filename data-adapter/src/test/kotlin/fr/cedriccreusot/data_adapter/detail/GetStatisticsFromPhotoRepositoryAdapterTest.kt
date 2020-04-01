package fr.cedriccreusot.data_adapter.detail

import com.google.common.truth.Truth.assertThat
import fr.cedriccreusot.data_adapter.mocks.ResponsesGsonMocks
import fr.cedriccreusot.data_adapter.mocks.UnsplashServiceMocks
import fr.cedriccreusot.data_adapter.retrofit.UnsplashService
import fr.cedriccreusot.domain.detail.repository.GetStatisticsFromPhotoException
import fr.cedriccreusot.domain.model.Statistics
import io.kotest.core.spec.style.StringSpec

class GetStatisticsFromPhotoRepositoryAdapterTest : StringSpec({
    val authorizationHead = "authorization"
    val photoId = "photoId"

    """
            Given the current repository
            When we're get the photos for the username given
            And there is an error with the web service
            Then it should throw an IOException
        """ {
        val serviceFailedResponse: UnsplashService =
            UnsplashServiceMocks.createServiceThatFail()

        val repository =
            GetStatisticsFromPhotoRepositoryAdapter(authorizationHead, serviceFailedResponse)

        val result = runCatching {
            repository.statistics(photoId)
        }

        assertThat(result.exceptionOrNull()).isNotNull()
        assertThat(result.exceptionOrNull()).isInstanceOf(GetStatisticsFromPhotoException::class.java)
    }

    """
            Given the current repository
            When we're get the photos for the username given
            Then it should return a new list of Photo
        """ {

        val service =
            UnsplashServiceMocks.createServiceWithResponses(statistics = ResponsesGsonMocks.createStatistics())

        val repository = GetStatisticsFromPhotoRepositoryAdapter(authorizationHead, service)

        val result = runCatching {
            repository.statistics(photoId)
        }

        assertThat(result.isSuccess).isNotNull()
        assertThat(result.getOrNull()).isEqualTo(
            Statistics(download = 49771, views = 5165988, likes = 263)
        )
    }
})