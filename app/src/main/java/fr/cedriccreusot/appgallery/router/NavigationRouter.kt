package fr.cedriccreusot.appgallery.router

import android.content.Context
import android.content.Intent
import androidx.navigation.NavController
import fr.cedriccreusot.appgallery.detail.DetailActivity
import fr.cedriccreusot.appgallery.gallery.GalleryFragmentDirections
import fr.cedriccreusot.domain.model.Photo

class NavigationRouter(
    private val navController: NavController? = null,
    private val context: Context? = null
) {
    fun openDetail(photo: Photo): Unit {
        val action = GalleryFragmentDirections.actionNavigationHomeToDetailActivity(
            photoId = photo.id,
            photoDate = photo.date,
            photoDescription = photo.description,
            photoColor = photo.color,
            photoUrl = photo.regularPhoto,
            ownerUsername = photo.user.userName,
            ownerFullName = photo.user.fullName,
            ownerProfilePhotoUrl = photo.user.profilePhotoUrl
        )

        if (navController != null) {
            navController.navigate(action)
        } else context?.startActivity(
            Intent(context, DetailActivity::class.java).addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            ).putExtras(action.arguments)
        )
    }
}