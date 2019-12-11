package fakhteh.fanavaran.kotlin.di.component

import dagger.Component
import fakhteh.fanavaran.kotlin.di.modules.ApplicationContextModule
import fakhteh.fanavaran.kotlin.di.modules.DataBaseModule


@Component(modules = [DataBaseModule::class])
interface DataBaseComponent {
}