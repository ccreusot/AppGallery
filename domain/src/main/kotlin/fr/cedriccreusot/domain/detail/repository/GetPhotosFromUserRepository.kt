package fr.cedriccreusot.domain.detail.repository

import fr.cedriccreusot.domain.model.Photo

interface GetPhotosFromUserRepository {
    @Throws(GetPhotosFromUserException::class)
    fun photos(userName: String): List<Photo>
}

class GetPhotosFromUserException : Exception()