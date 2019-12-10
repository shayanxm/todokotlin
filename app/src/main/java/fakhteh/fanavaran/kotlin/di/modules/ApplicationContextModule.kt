package fakhteh.fanavaran.kotlin.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import fakhteh.fanavaran.kotlin.di.qualifier.ApplicationContextQuailfier
import fakhteh.fanavaran.kotlin.di.scopes.CustomApplicationScope
@Module
class ApplicationContextModule(private var context: Context)  {

lateinit var conti:Context
    init {
        this.context = context.applicationContext
    }
//
//    @ApplicationContextQuailfier
//    @Provides
//    @CustomApplicationScope
//    fun provideContext() = context

//    override fun onCreate() {
//        super.onCreate()
//        conti=applicationContext
//
//    }



    //@ApplicationContextQuailfier
    @Provides
  @CustomApplicationScope
    fun provideContext() :Context= context.applicationContext
}