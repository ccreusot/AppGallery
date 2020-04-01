package fr.cedriccreusot.appgallery.common.gallery.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.cedriccreusot.appgallery.R
import fr.cedriccreusot.appgallery.databinding.ItemListPhotoBinding
import fr.cedriccreusot.appgallery.router.NavigationRouter
import fr.cedriccreusot.domain.model.Photo
import kotlinx.android.synthetic.main.item_list_photo.view.*

class GalleryAdapter(private val router: NavigationRouter) : ListAdapter<Photo, PhotoViewHolder>(PhotoItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PhotoViewHolder(ItemListPhotoBinding.inflate(inflater, parent, false), router)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class PhotoItemCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}

class PhotoViewHolder(private val item: ItemListPhotoBinding, private val router: NavigationRouter): RecyclerView.ViewHolder(item.root) {
    fun  bind(photo: Photo) {
        item.photo = photo
        itemView.setOnClickListener {
            router.openDetail(photo)
        }
    }
}

class GalleryDecorator(val span: Int, @DimenRes val itemOffset: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val column = position % span
        val offset = view.resources.getDimensionPixelOffset(itemOffset)
        outRect.set(column * offset / span, offset, offset - (column + 1) * offset / span, 0)
    }
}