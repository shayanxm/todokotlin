package fakhteh.fanavaran.kotlin.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

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
//    @Query("UPDATE from weatherData  WHERE id = :taskId ")
//    fun editeWithId( taskId:Long)
    @Update
    fun editWithObj( wather:WeatherData)


}