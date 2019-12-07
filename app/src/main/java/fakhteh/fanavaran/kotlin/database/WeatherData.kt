package fakhteh.fanavaran.kotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "weatherData")
data class WeatherData(@PrimaryKey(autoGenerate = true) var id: Long?,
                       @ColumnInfo(name = "title") var title: String,
                       @ColumnInfo(name = "prio") var prio: Int


){
    constructor():this(null,"",4)

}