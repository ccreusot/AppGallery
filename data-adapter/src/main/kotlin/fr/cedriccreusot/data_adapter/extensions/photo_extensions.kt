package fr.cedriccreusot.data_adapter.extensions

import fr.cedriccreusot.data_adapter.retrofit.model.Photo
import fr.cedriccreusot.data_adapter.retrofit.model.User
import fr.cedriccreusot.data_adapter.retrofit.model.UserCollection
import fr.cedriccreusot.domain.model.CollectionPhoto
import fr.cedriccreusot.domain.model.UserPhoto

typealias DomainPhoto = fr.cedriccreusot.domain.model.Photo

fun Photo.toDomainPhoto(): DomainPhoto = DomainPhoto(
    id = id ?: "",
    description = description ?: "",
    smallPhoto = urls?.small ?: "",
    regularPhoto = urls?.regular ?: "",
    date = createdAt ?: "",
    color = color ?: "",
    user = user.let { it?.toUserPhoto() ?: UserPhoto("", "", "", "") },
    collections = currentUserCollections.map { collection ->
        collection.toCollectionPhoto()
    }
)

fun User.toUserPhoto(): UserPhoto =
    UserPhoto(
        id = id ?: "",
        userName = username ?: "",
        fullName = name ?: "",
        profilePhotoUrl = profileImage.small ?: ""
    )


fun UserCollection.toCollectionPhoto(): CollectionPhoto = CollectionPhoto(
    id = id ?: 0,
    title = title ?: ""
)