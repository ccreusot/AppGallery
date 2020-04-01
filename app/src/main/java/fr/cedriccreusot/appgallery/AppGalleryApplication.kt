package fr.cedriccreusot.appgallery

import android.app.Application
import fr.cedriccreusot.appgallery.detail.DetailActivityArgs
import fr.cedriccreusot.appgallery.detail.viewmodel.DetailViewModel
import fr.cedriccreusot.appgallery.gallery.viewmodel.GalleryViewModel
import fr.cedriccreusot.data_adapter.detail.GetPhotosFromUserRepositoryAdapter
import fr.cedriccreusot.data_adapter.detail.GetStatisticsFromPhotoRepositoryAdapter
import fr.cedriccreusot.data_adapter.gallery.GetPhotosRepositoryAdapter
import fr.cedriccreusot.data_adapter.retrofit.UnsplashService
import fr.cedriccreusot.domain.detail.repository.GetPhotosFromUserRepository
import fr.cedriccreusot.domain.detail.repository.GetStatisticsFromPhotoRepository
import fr.cedriccreusot.domain.detail.usecase.FetchPhotoStatisticsUseCase
import fr.cedriccreusot.domain.detail.usecase.FetchPhotosFromUserUseCase
import fr.cedriccreusot.domain.gallery.repository.GetPhotosRepository
import fr.cedriccreusot.domain.gallery.usecase.FetchPhotosUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.text.SimpleDateFormat

class AppGalleryApplication : Application() {
    private val appModule = module {

        single { UnsplashService.createService(getProperty("server_url")) }

        single<GetPhotosRepository> {
            GetPhotosRepositoryAdapter(getProperty("authorization"), get())
        }

        single<GetPhotosFromUserRepository> {
            GetPhotosFromUserRepositoryAdapter(getProperty("authorization"), get())
        }

        single<GetStatisticsFromPhotoRepository> {
            GetStatisticsFromPhotoRepositoryAdapter(getProperty("authorization"), get())
        }

        single { FetchPhotosUseCase.create(get()) }
        single { FetchPhotosFromUserUseCase.create(get()) }
        single { FetchPhotoStatisticsUseCase.create(get()) }
        viewModel { GalleryViewModel(get()) }
        viewModel { (args: DetailActivityArgs) -> DetailViewModel(args, get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppGalleryApplication)
            androidFileProperties()
            modules(appModule)
        }
    }
}