package fr.cedriccreusot.domain.utils

import fr.cedriccreusot.domain.model.Photo
import fr.cedriccreusot.domain.model.UserPhoto

object DataMock {
    fun photo(photoId: Int, userId: Int): Photo = Photo(
        id = "id$photoId",
        description = "description$photoId",
        urlPhoto = "url$photoId",
        collections = emptyList(),
        user = UserPhoto(id = "id$userId", userName = "userName$userId")
    )
}