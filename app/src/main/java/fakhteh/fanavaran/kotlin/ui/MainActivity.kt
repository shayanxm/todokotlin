package fakhteh.fanavaran.kotlin.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fakhteh.fanavaran.kotlin.R
import fakhteh.fanavaran.kotlin.database.*
import fakhteh.fanavaran.kotlin.di.component.DaggerServiceaApplicationComonent
import fakhteh.fanavaran.kotlin.di.modules.ApplicationContextModule
import fakhteh.fanavaran.kotlin.model.Prioritys
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private var mDb: WeatherDataBase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread
    @Inject
    lateinit var AppContext: Context
    private val mUiHandler = Handler()

    companion object {
        val tranfserTag = "fakhteh.fanavaran.kotlin.tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workerThreadStarter()
//        DaggerServiceaApplicationComonentbuilder()
//            .builder()
//            .applicationContextModule(new ApplicationContextModule(this))
//            .build()
//            .inject(this);Da
        val applicationContextModule=ApplicationContextModule(applicationContext)
        DaggerServiceaApplicationComonent.builder().applicationContextModule(applicationContextModule).build().injectActivity(this)




        mDb = WeatherDataBase.getInstance(AppContext)

        show_task_page.setOnClickListener(View.OnClickListener { unit ->

            val task = Runnable {
                val weatherData =
                    mDb?.weatherDataDao()?.getAll()
                mUiHandler.post({
                    if (weatherData == null || weatherData?.size == 0) {
                        // showToast("No data in cache..!!", Toast.LENGTH_SHORT)
                    } else {
                        val sizeL = weatherData.size
                        Log.e("database", "S:$sizeL")
                    }
                })
            }
            mDbWorkerThread.postTask(task)
            goToDialogFragment()
        })
        add_to_list.setOnClickListener(View.OnClickListener { unit ->


            insertToDataBase(task_title.text.toString(), convertRbToInT())

        }

        )
    }

    private fun insertToDataBase(title: String, prio: Int) {
        var weatherData = WeatherData(null, title, prio)
        insertWeatherDataInDb(weatherData)
    }

    private fun goToDialogFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val previous = supportFragmentManager.findFragmentByTag(tranfserTag)
        if (previous != null) {
            transaction.remove(previous)
        }
        transaction.addToBackStack(null)

        val dialogFragment =
            TaskDoalogFragment.newintance("parameter")
        dialogFragment.show(transaction, tranfserTag)
    }

    private fun workerThreadStarter() {
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
    }


    private fun convertRbToInT(): Int {
        if (low_prio_RB.isChecked) return Prioritys.LOW.prioNum
        if (med_prio_RB.isChecked) return Prioritys.MEDIUM.prioNum
        if (hg_prio_RB.isChecked) return Prioritys.HIGH.prioNum

        Toast.makeText(this@MainActivity, "you can alsow enter a priority ", Toast.LENGTH_SHORT)
        return Prioritys.UNDIFINED.prioNum

    }

    private fun insertWeatherDataInDb(weatherData: WeatherData) {
        val task = Runnable { mDb?.weatherDataDao()?.insert(weatherData) }
        mDbWorkerThread.postTask(task)
    }

}