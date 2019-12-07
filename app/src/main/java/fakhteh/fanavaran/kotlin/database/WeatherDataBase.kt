package fakhteh.fanavaran.kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(WeatherData::class), version = 1)
abstract class WeatherDataBase : RoomDatabase() {

    abstract fun weatherDataDao(): WeatherDataDao

    companion object {
        private var INSTANCE: WeatherDataBase? = null

        fun getInstance(context: Context): WeatherDataBase? {
            if (INSTANCE == null) {
                synchronized(WeatherDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        WeatherDataBase::class.java, "weather.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}