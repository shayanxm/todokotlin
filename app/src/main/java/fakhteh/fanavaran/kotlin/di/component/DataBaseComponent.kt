package fakhteh.fanavaran.kotlin.di.component

import dagger.Component
import fakhteh.fanavaran.kotlin.di.modules.ApplicationContextModule
import fakhteh.fanavaran.kotlin.di.modules.DataBaseModule
import fakhteh.fanavaran.kotlin.di.scopes.CustomApplicationScope
import fakhteh.fanavaran.kotlin.ui.MainActivity
import fakhteh.fanavaran.kotlin.ui.TaskDoalogFragment

@CustomApplicationScope
@Component(modules = [DataBaseModule::class,ApplicationContextModule::class])
interface DataBaseComponent {
    public fun injectActivity(mainActivity: MainActivity)
    fun injectFragment(DialogFragment:TaskDoalogFragment)

}