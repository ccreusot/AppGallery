package fr.cedriccreusot.domain.utils

import fr.cedriccreusot.domain.model.Photo
import fr.cedriccreusot.domain.model.UserPhoto

object DataMock {
    fun photo(photoId: Int, userId: Int): Photo = Photo(
        id = "id$photoId",
        description = "description$photoId",
        date = "date",
        color = "color",
        smallPhoto = "url$photoId",
        regularPhoto = "url$photoId",
        collections = emptyList(),
        user = UserPhoto(
            id = "id$userId",
            userName = "userName$userId",
            fullName = "fullName$userId",
            profilePhotoUrl = "profilePhotoUrl$userId"
        )
    )
}