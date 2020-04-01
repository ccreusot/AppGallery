package fr.cedriccreusot.appgallery.common.gallery.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.cedriccreusot.appgallery.R
import fr.cedriccreusot.appgallery.common.gallery.adapter.GalleryAdapter
import fr.cedriccreusot.appgallery.common.gallery.adapter.GalleryDecorator
import fr.cedriccreusot.appgallery.router.NavigationRouter
import fr.cedriccreusot.domain.model.Photo

@BindingAdapter("gallery:photos", "gallery:navigation")
fun galleryRecyclerView(recyclerView: RecyclerView, photos: List<Photo>, router: NavigationRouter) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = GalleryAdapter(router).apply {
            submitList(photos)
        }
        val spanCount = (recyclerView.layoutManager as GridLayoutManager).spanCount
        recyclerView.addItemDecoration(GalleryDecorator(spanCount, R.dimen.gallery_item_offset))
    } else {
        (recyclerView.adapter as GalleryAdapter).submitList(photos)
    }
}
