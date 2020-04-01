package fr.cedriccreusot.domain.detail.usecase

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import fr.cedriccreusot.domain.detail.repository.GetPhotosFromUserException
import fr.cedriccreusot.domain.detail.repository.GetPhotosFromUserRepository
import fr.cedriccreusot.domain.utils.DataMock
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class FetchPhotosFromUserUseCaseImplTestFetchPhotosUseCaseTest : StringSpec() {

    private lateinit var repository: GetPhotosFromUserRepository
    private lateinit var useCase: FetchPhotosFromUserUseCase

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        repository = mock(GetPhotosFromUserRepository::class.java)
        useCase = FetchPhotosFromUserUseCase.create(repository)
    }

    init {
        val userNameParam = "userName1"

        """
        Given the username is empty
        When we're trying to fetch the photo
        Then we should throw an IllegalArgumentException.
    """ {
            val result = runCatching {
                useCase.invoke("")
            }

            verifyZeroInteractions(repository)
            assertThat(result.exceptionOrNull()).isNotNull()
            assertThat(result.exceptionOrNull()).isInstanceOf(IllegalArgumentException::class.java)
        }

        """
        Given the user want to see the photos from a user
        When we're fetching them
        And the repository throw an Exception
        Then we should return an empty list of Photo.
    """ {
            given(repository.photos(userNameParam)).willThrow(GetPhotosFromUserException::class.java)

            val result = useCase.invoke(userNameParam)

            assertThat(result).isNotNull()
            assertThat(result).isEmpty()
            verify(repository).photos(userNameParam)
        }

        """
        Given the user want to see the photos from a user
        When we're fetching them, the repository return a list of Photo
        Then we should return that list of Photo.
    """ {
            val expectedList = listOf(
                DataMock.photo(1, 1),
                DataMock.photo(2, 1),
                DataMock.photo(2, 1),
                DataMock.photo(2, 1)
            )
            given(repository.photos(userNameParam)).willReturn(
                expectedList
            )

            val result = useCase.invoke(userNameParam)

            assertThat(result).isNotNull()
            assertThat(result).isNotEmpty()
            assertThat(result).isEqualTo(expectedList)
            verify(repository).photos(userNameParam)
        }
    }
}