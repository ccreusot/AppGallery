package fr.cedriccreusot.appgallery.common.bindingadapters

import android.widget.ViewFlipper
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("photo:src")
fun photoSrc(imageView: AppCompatImageView, url: String) {
    Picasso.get()
        .load(url)
        .into(imageView)
}

@BindingAdapter("loading:state")
fun loadingState(viewFlipper: ViewFlipper, loadingState: Int) {
    viewFlipper.displayedChild = loadingState
}
