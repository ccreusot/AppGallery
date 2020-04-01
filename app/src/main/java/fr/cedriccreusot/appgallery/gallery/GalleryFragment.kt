package fr.cedriccreusot.appgallery.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.cedriccreusot.appgallery.databinding.FragmentGalleryBinding
import fr.cedriccreusot.appgallery.gallery.viewmodel.GalleryViewModel
import fr.cedriccreusot.appgallery.router.NavigationRouter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class GalleryFragment : Fragment() {

    private val galleryViewModel: GalleryViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return FragmentGalleryBinding.inflate(inflater, container, false).apply {
            viewModel = galleryViewModel
            router = NavigationRouter(findNavController())
            lifecycleOwner = this@GalleryFragment
        }.root
    }
}
