package fakhteh.fanavaran.kotlin.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import fakhteh.fanavaran.kotlin.di.qualifier.ApplicationContextQuailfier
import fakhteh.fanavaran.kotlin.di.scopes.CustomApplicationScope
@Module
class ApplicationContextModule(@ApplicationContextQuailfier private var contextd: Context)  {

//lateinit var conti:Context
//    init {
//        this.context = context.applicationContext
//    }
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
val contexti :Context?=null


    //@ApplicationContextQuailfier
    @Provides

  @CustomApplicationScope
    fun provideContext() :Context= contextd
}