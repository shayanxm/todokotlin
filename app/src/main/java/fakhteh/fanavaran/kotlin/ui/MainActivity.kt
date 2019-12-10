package fakhteh.fanavaran.kotlin.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fakhteh.fanavaran.kotlin.R
import fakhteh.fanavaran.kotlin.database.*
import fakhteh.fanavaran.kotlin.model.DataBase
import fakhteh.fanavaran.kotlin.model.Prioritys
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mDb: WeatherDataBase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread

    private val mUiHandler = Handler()
    companion object {
        val tranfserTag = "fakhteh.fanavaran.kotlin.tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        mDb = WeatherDataBase.getInstance(this)

        show_task_page.setOnClickListener(View.OnClickListener { unit ->

            val task = Runnable {
                val weatherData =
                    mDb?.weatherDataDao()?.getAll()
                mUiHandler.post({
                    if (weatherData == null || weatherData?.size == 0) {
                        // showToast("No data in cache..!!", Toast.LENGTH_SHORT)
                    } else {
                        val sizeL=weatherData.size
                        Log.e("database","S:$sizeL")
                    }
                })
            }
            mDbWorkerThread.postTask(task)
            val transaction = supportFragmentManager.beginTransaction()
            val previous = supportFragmentManager.findFragmentByTag(tranfserTag)
            if (previous != null) {
                transaction.remove(previous)
            }
            transaction.addToBackStack(null)

            val dialogFragment =
                TaskDoalogFragment.newintance("parameter")
            dialogFragment.show(transaction, tranfserTag)
        })
        add_to_list.setOnClickListener(View.OnClickListener { unit ->
            DataBase.taskList?.add(task_title.text.toString())
            DataBase.priorityOfTaskList.add(convertRbToIn())

            var weatherData=WeatherData(null,task_title.text.toString(),  convertRbToInT())
            insertWeatherDataInDb(weatherData)
            Log.e(
                "testing",
                DataBase.taskList!!.size.toString() + "\n" + DataBase.taskList!!.get(0)
            )
        }

        )
    }

    private fun convertRbToIn(): Prioritys {
if(low_prio_RB.isChecked)return Prioritys.LOW
    if(med_prio_RB.isChecked)return Prioritys.MEDIUM
        if(hg_prio_RB.isChecked)return Prioritys.HIGH
        Toast.makeText(this@MainActivity,"you can alsow enter a priority ",Toast.LENGTH_SHORT)
        return Prioritys.UNDIFINED

    }
    private fun convertRbToInT(): Int {
        if(low_prio_RB.isChecked)return  Prioritys.LOW.prioNum
        if(med_prio_RB.isChecked)return Prioritys.MEDIUM.prioNum
        if(hg_prio_RB.isChecked)return Prioritys.HIGH.prioNum

        Toast.makeText(this@MainActivity,"you can alsow enter a priority ",Toast.LENGTH_SHORT)
        return Prioritys.UNDIFINED.prioNum

    }
    private fun insertWeatherDataInDb(weatherData: WeatherData) {
        val task = Runnable { mDb?.weatherDataDao()?.insert(weatherData) }
        mDbWorkerThread.postTask(task)
    }
}