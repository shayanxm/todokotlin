package fakhteh.fanavaran.kotlin.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import fakhteh.fanavaran.kotlin.database.DbWorkerThread
import fakhteh.fanavaran.kotlin.database.WeatherData
import fakhteh.fanavaran.kotlin.database.WeatherDataBase
import fakhteh.fanavaran.kotlin.di.component.DaggerDataBaseComponent
import fakhteh.fanavaran.kotlin.di.modules.ApplicationContextModule
import fakhteh.fanavaran.kotlin.model.Prioritys
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.sql.StatementEvent

class MainViewModel : ViewModel() {
    var topTitle = ObservableField<String>("ToDoApp")
    var editTextValut = ObservableField<String>("")
    var lowChecked =ObservableField<Boolean>(false)
    var midChecked=ObservableField<Boolean>(false)
    var highChecked =ObservableField<Boolean>(false)

    @Inject
    lateinit var mdb: WeatherDataBase
    private lateinit var mDbWorkerThread: DbWorkerThread

    init {
//    val applicationContextModule = ApplicationContextModule(context.applicationContext)
//    DaggerDataBaseComponent.builder().applicationContextModule(applicationContextModule).build()
    }

    fun syncTitleToEditText() {
        topTitle = editTextValut
    }

    fun onAddToDbClicked() {
        Log.e("onClick", "clicked" + editTextValut.get())
        insertToDataBase(editTextValut.get().toString(), convertRbToInT())

    }

    private fun insertToDataBase(title: String, prio: Int) {
        var weatherData = WeatherData(null, title, prio)
        insertWeatherDataInDb(weatherData)
    }

    private fun insertWeatherDataInDb(weatherData: WeatherData) {
        val thread = Thread {
            mdb.weatherDataDao()?.insert(weatherData)
        }
        thread.start()
    }

    fun getmdb(mdb: WeatherDataBase): WeatherDataBase {
        return mdb

    }


    private fun convertRbToInT(): Int {
        if (lowChecked.get()!!) return Prioritys.LOW.prioNum
        if (midChecked.get()!!) return Prioritys.MEDIUM.prioNum
        if (highChecked.get()!!) return Prioritys.HIGH.prioNum

     //   Toast.makeText(this@MainActivity, "you can alsow enter a priority ", Toast.LENGTH_SHORT)
        return Prioritys.UNDIFINED.prioNum

    }



}