package fr.cedriccreusot.appgallery.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import fr.cedriccreusot.appgallery.databinding.ActivityDetailBinding
import fr.cedriccreusot.appgallery.detail.viewmodel.DetailViewModel
import fr.cedriccreusot.appgallery.router.NavigationRouter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : AppCompatActivity() {

    private val args: DetailActivityArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModel { parametersOf(args) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityDetailBinding.inflate(layoutInflater).apply {
            viewModel = detailViewModel
            router = NavigationRouter(context = this@DetailActivity)
            lifecycleOwner = this@DetailActivity
        }.root)
    }
}