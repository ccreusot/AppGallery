package fr.cedriccreusot.data_adapter.retrofit.model

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id") val id : String?,
    @SerializedName("created_at") val createdAt : String?,
    @SerializedName("updated_at") val updatedAt : String?,
    @SerializedName("width") val width : Int?,
    @SerializedName("height") val height : Int?,
    @SerializedName("color") val color : String?,
    @SerializedName("likes") val likes : Int?,
    @SerializedName("liked_by_user") val likedByUser : Boolean?,
    @SerializedName("description") val description : String?,
    @SerializedName("user") val user : User?,
    @SerializedName("current_user_collections") val currentUserCollections : List<UserCollection>,
    @SerializedName("urls") val urls : Urls?,
    @SerializedName("links") val links : Links?
)

data class UserCollection(
    @SerializedName("id") val id : Int?,
    @SerializedName("title") val title : String?,
    @SerializedName("published_at") val publishedAt : String?,
    @SerializedName("updated_at") val updatedAt : String?,
    @SerializedName("cover_photo") val coverPhoto : String?,
    @SerializedName("user") val user : String?
)

data class Links(
    @SerializedName("self") val self : String?,
    @SerializedName("html") val html : String?,
    @SerializedName("download") val download : String?,
    @SerializedName("download_location") val downloadLocation : String?
)

data class Urls(
    @SerializedName("raw") val raw : String?,
    @SerializedName("full") val full : String?,
    @SerializedName("regular") val regular : String?,
    @SerializedName("small") val small : String?,
    @SerializedName("thumb") val thumb : String?
)

data class User(
    @SerializedName("id") val id : String?,
    @SerializedName("username") val username : String?,
    @SerializedName("name") val name : String?,
    @SerializedName("portfolio_url") val portfolioUrl : String?,
    @SerializedName("bio") val bio : String?,
    @SerializedName("location") val location : String?,
    @SerializedName("total_likes") val totalLikes : Int?,
    @SerializedName("total_photos") val totalPhotos : Int?,
    @SerializedName("total_collections") val totalCollections : Int?,
    @SerializedName("instagram_username") val instagramUsername : String?,
    @SerializedName("twitter_username") val twitterUsername : String?,
    @SerializedName("profile_image") val profileImage : ImageProfile,
    @SerializedName("links") val links : Links
)

data class ImageProfile(
    @SerializedName("small") val small : String?,
    @SerializedName("medium") val medium : String?,
    @SerializedName("large") val large : String?
)

