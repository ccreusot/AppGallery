package fr.cedriccreusot.domain.detail.usecase

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import fr.cedriccreusot.domain.detail.repository.GetStatisticsFromPhotoException
import fr.cedriccreusot.domain.detail.repository.GetStatisticsFromPhotoRepository
import fr.cedriccreusot.domain.model.Statistics
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.lang.IllegalArgumentException


class FetchPhotoStatisticsUserCaseImplTest : StringSpec() {
    private lateinit var repository: GetStatisticsFromPhotoRepository
    private lateinit var useCase: FetchPhotoStatisticsUseCase

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        repository = mock(GetStatisticsFromPhotoRepository::class.java)
        useCase = FetchPhotoStatisticsUseCase.create(repository)
    }

    init {
        val photoIdParam = "photo1"

        """
        Given the photoId is empty
        When we're trying to fetch statistics about the photo
        Then we should throw an IllegalArgumentException
    """ {
            val result = runCatching {
                useCase.invoke("")
            }

            verifyZeroInteractions(repository)
            assertThat(result.exceptionOrNull()).isNotNull()
            assertThat(result.exceptionOrNull()).isInstanceOf(IllegalArgumentException::class.java)
        }

        """
        Given the photoId to retrieve statistics is ok
        When we're trying to fetch statistics about the photo
        And repository throw an Exception
        Then we should throw back that Exception
    """ {
            given(repository.statistics(photoIdParam)).willThrow(GetStatisticsFromPhotoException::class.java)

            val result = runCatching {
                useCase.invoke(photoIdParam)
            }

            verify(repository).statistics(photoIdParam)
            assertThat(result.exceptionOrNull()).isNotNull()
            assertThat(result.exceptionOrNull()).isInstanceOf(GetStatisticsFromPhotoException::class.java)
        }

        """
        Given the photoId to retrieve statistics is ok
        When we're trying to fetch statistics about the photo
        And repository return the statistics
        Then we should return them
    """ {
            val expectedStatistics = Statistics(15, 25, 10)
            given(repository.statistics(photoIdParam)).willReturn(expectedStatistics)

            val result = useCase.invoke(photoIdParam)

            verify(repository).statistics(photoIdParam)
            assertThat(result).isEqualTo(expectedStatistics)
        }
    }
}