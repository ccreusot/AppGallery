package fr.cedriccreusot.data_adapter.detail

import com.google.common.truth.Truth.assertThat
import fr.cedriccreusot.data_adapter.mocks.ResponsesGsonMocks
import fr.cedriccreusot.data_adapter.mocks.UnsplashServiceMocks
import fr.cedriccreusot.data_adapter.retrofit.UnsplashService
import fr.cedriccreusot.domain.detail.repository.GetPhotosFromUserException
import fr.cedriccreusot.domain.model.CollectionPhoto
import fr.cedriccreusot.domain.model.UserPhoto
import io.kotest.core.spec.style.StringSpec

typealias DomainPhoto = fr.cedriccreusot.domain.model.Photo

class GetPhotosFromUserRepositoryAdapterTest : StringSpec({
    val authorizationHead = "authorization"
    val username = "username"

    """
            Given the current repository
            When we're get the photos for the username given
            And there is an error with the web service
            Then it should throw an IOException
        """ {
        val serviceFailedResponse: UnsplashService =
            UnsplashServiceMocks.createServiceThatFail()

        val repository = GetPhotosFromUserRepositoryAdapter(authorizationHead, serviceFailedResponse)

        val result = runCatching {
            repository.photos(username)
        }

        assertThat(result.exceptionOrNull()).isNotNull()
        assertThat(result.exceptionOrNull()).isInstanceOf(GetPhotosFromUserException::class.java)
    }

    """
            Given the current repository
            When we're get the photos for the username given
            Then it should return a new list of Photo
        """ {

        val service =
            UnsplashServiceMocks.createServiceWithResponses(userPhotos = ResponsesGsonMocks.createListPhotos())

        val repository = GetPhotosFromUserRepositoryAdapter(authorizationHead, service)

        val result = runCatching {
            repository.photos(username)
        }

        assertThat(result.isSuccess).isNotNull()
        assertThat(result.getOrNull()).isEqualTo(
            listOf(
                DomainPhoto(
                    id = "LBI7cgq3pbM",
                    description = "A man drinking a coffee.",
                    date = "2016-05-03T11:00:28-04:00",
                    color = "#60544D",
                    smallPhoto = "https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=400&fit=max",
                    regularPhoto = "https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=1080&fit=max",
                    collections = listOf(
                        CollectionPhoto(
                            id = 206,
                            title = "Makers: Cat and Ben"
                        )
                    ),
                    user = UserPhoto(
                        id = "pXhwzz1JtQU",
                        userName = "poorkane",
                        fullName = "Gilbert Kane",
                        profilePhotoUrl = "https://images.unsplash.com/face-springmorning.jpg?q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32"
                    )
                )
            )
        )
    }
})