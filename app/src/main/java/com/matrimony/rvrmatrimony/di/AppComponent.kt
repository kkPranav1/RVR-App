package com.matrimony.rvrmatrimony.di

import dagger.Component

@Component(modules = [ViewModelBindingsClass::class, PersistenceModule::class, NetworkModule::class])
abstract class AppComponent {
}