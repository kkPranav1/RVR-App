package com.matrimony.rvrmatrimony.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.matrimony.rvrmatrimony.MainDashBoardViewModel
import com.matrimony.rvrmatrimony.uicode.onboarding.LoginAccountViewModel
import com.matrimony.rvrmatrimony.uicode.onboarding.LoginAppViewModel
import com.matrimony.rvrmatrimony.uicode.onboarding.RegisterStep2ViewModel
import com.matrimony.rvrmatrimony.uicode.onboarding.TestScreenViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}


@MapKey
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)


@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelBindingsClass {

    @Binds
    @IntoMap
    @ViewModelKey(MainDashBoardViewModel::class)
    internal abstract fun postMainViewModel(viewModel: MainDashBoardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginAppViewModel::class)
    internal abstract fun postLoginAppViewModel(viewModel: LoginAppViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginAccountViewModel::class)
    internal abstract fun postLoginAccountViewModel(viewModel: LoginAccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterStep2ViewModel::class)
    internal abstract fun postRegisterStep2ViewModel(viewModel: RegisterStep2ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TestScreenViewModel::class)
    internal abstract fun postTestScreenViewModel(viewModel: TestScreenViewModel): ViewModel

}