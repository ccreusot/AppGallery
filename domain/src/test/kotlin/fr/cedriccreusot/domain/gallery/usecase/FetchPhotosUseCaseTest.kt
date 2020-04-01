package fr.cedriccreusot.domain.gallery.usecase;

import com.google.common.truth.Truth.assertThat
import fr.cedriccreusot.domain.utils.DataMock
import fr.cedriccreusot.domain.gallery.repository.GetPhotosException
import fr.cedriccreusot.domain.gallery.repository.GetPhotosRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class FetchPhotosUseCaseTest : StringSpec() {

    private lateinit var repository: GetPhotosRepository
    private lateinit var useCase: FetchPhotosUseCase


    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        repository = mock(GetPhotosRepository::class.java)
        useCase = FetchPhotosUseCase.create(repository)
    }

    init {
        """
        Given the user want to see the photos from the first page
        When we're fetching them
        And the repository throw an Exception
        Then we should return an empty list of Photo.
    """ {

            given(repository.photos()).willThrow(GetPhotosException::class.java)

            val result = useCase.invoke()

            assertThat(result).isNotNull()
            assertThat(result).isEmpty()
            verify(repository).photos()
        }

        """
        Given the user want to see the photos from the first page
        When we're fetching them, the repository return a list of Photo
        Then we should return that list of Photo.
    """ {
            val expectedList = listOf(
                DataMock.photo(1, 1),
                DataMock.photo(2, 1),
                DataMock.photo(2, 1)
            )
            given(repository.photos()).willReturn(
                expectedList
            )

            val result = useCase.invoke()

            assertThat(result).isNotNull()
            assertThat(result).isNotEmpty()
            assertThat(result).isEqualTo(expectedList)
            verify(repository).photos()
        }
    }
}