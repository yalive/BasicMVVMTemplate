package com.yabahddou.di

import android.content.Context
import com.yabahddou.ui.home.HomeViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    // Expose view models (will be created by dagger)
    val homeViewModel: HomeViewModel
}