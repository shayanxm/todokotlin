package fakhteh.fanavaran.kotlin.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface WeatherDataDao {

    @Query("SELECT * from weatherData")
    fun getAll(): List<WeatherData>

    @Insert(onConflict = REPLACE)
    fun insert(weatherData: WeatherData)

    @Query("DELETE from weatherData")
    fun deleteAll()
    @Query("Delete from weatherData  WHERE id = :taskId ")
    fun deleteWithId( taskId:Long)
}