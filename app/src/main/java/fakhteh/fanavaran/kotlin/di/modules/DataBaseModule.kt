package fakhteh.fanavaran.kotlin.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import fakhteh.fanavaran.kotlin.database.WeatherData
import fakhteh.fanavaran.kotlin.database.WeatherDataBase
import fakhteh.fanavaran.kotlin.di.qualifier.ApplicationContextQuailfier

@Module
class DataBaseModule {
    @Provides
    fun dataBaseProvider(@ApplicationContextQuailfier context:Context):WeatherDataBase{
        return  WeatherDataBase.getInstance(context)!!
    }
}