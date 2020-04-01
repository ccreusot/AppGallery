package fr.cedriccreusot.domain.gallery.repository

import fr.cedriccreusot.domain.model.Photo

interface GetPhotosRepository {
    @Throws(GetPhotosException::class)
    fun photos() : List<Photo>
}

class GetPhotosException : Exception()