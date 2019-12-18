package fakhteh.fanavaran.kotlin.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import fakhteh.fanavaran.kotlin.database.DbWorkerThread
import fakhteh.fanavaran.kotlin.database.WeatherData
import fakhteh.fanavaran.kotlin.database.WeatherDataBase
import fakhteh.fanavaran.kotlin.model.Prioritys
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.sql.StatementEvent

class MainViewModel : ViewModel() {
    var topTitle = ObservableField<String>("ToDoApp")
    var editTextValut = ObservableField<String>("")
    @Inject
    lateinit var mdb: WeatherDataBase
    private lateinit var mDbWorkerThread: DbWorkerThread


    fun syncTitleToEditText() {
        topTitle = editTextValut
    }

    fun onAddToDbClicked() {
       Log.e("onClick","clicked"+editTextValut.get())
        insertToDataBase(editTextValut.get().toString(), 1)

    }

    private fun insertToDataBase(title: String, prio: Int) {
        var weatherData = WeatherData(null, title, prio)
        insertWeatherDataInDb(weatherData)
    }

    private fun insertWeatherDataInDb(weatherData: WeatherData) {
        val thread = Thread {
            val task = Runnable { mdb?.weatherDataDao()?.insert(weatherData) }
        }
        thread.start()
    }

    fun getmdb(mdb: WeatherDataBase): WeatherDataBase {
        return mdb

    }


//    private fun convertRbToInT(): Int {
//        if (low_prio_RB.isChecked) return Prioritys.LOW.prioNum
//        if (med_prio_RB.isChecked) return Prioritys.MEDIUM.prioNum
//        if (hg_prio_RB.isChecked) return Prioritys.HIGH.prioNum
//
//     //   Toast.makeText(this@MainActivity, "you can alsow enter a priority ", Toast.LENGTH_SHORT)
//        return Prioritys.UNDIFINED.prioNum
//
//    }

}