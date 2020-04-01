package fr.cedriccreusot.domain.model

data class Photo(
    val id: String,
    val description: String,
    val date: String,
    val color: String,
    val smallPhoto: String,
    val regularPhoto: String,
    val user: UserPhoto,
    val collections: List<CollectionPhoto>
)