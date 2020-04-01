package fr.cedriccreusot.domain.model

data class Photo(val id: String,
                 val description: String,
                 val urlPhoto: String,
                 val user: UserPhoto,
                 val collections: List<CollectionPhoto>)