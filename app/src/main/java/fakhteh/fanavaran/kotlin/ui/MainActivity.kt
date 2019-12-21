package fakhteh.fanavaran.kotlin.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import fakhteh.fanavaran.kotlin.R
import fakhteh.fanavaran.kotlin.database.*
import fakhteh.fanavaran.kotlin.databinding.ActivityMainBinding
import fakhteh.fanavaran.kotlin.di.component.DaggerDataBaseComponent

import fakhteh.fanavaran.kotlin.di.modules.ApplicationContextModule
import fakhteh.fanavaran.kotlin.di.modules.DataBaseModule
import fakhteh.fanavaran.kotlin.model.Prioritys
import fakhteh.fanavaran.kotlin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import androidx.lifecycle.ViewModelProviders
class MainActivity : AppCompatActivity() {
    //   private var mDb: WeatherDataBase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread
    @Inject
    lateinit var AppContext: Context
    @Inject
    lateinit var mdb: WeatherDataBase
    private val mUiHandler = Handler()
private lateinit var viewModel:MainViewModel
    lateinit var binding: ActivityMainBinding
    companion object {
        val tranfserTag = "fakhteh.fanavaran.kotlin.tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel=viewModel
        workerThreadStarter()
//        DaggerServiceaApplicationComonentbuilder()
//            .builder()
//            .applicationContextModule(new ApplicationContextModule(this))
//            .build()
//            .inject(this);Da

        val applicationContextModule = ApplicationContextModule(applicationContext)
        val database=DataBaseModule()
        //DaggerServiceaApplicationComonent.builder().applicationContextModule(applicationContextModule).build().injectActivity(this)
        DaggerDataBaseComponent.builder().applicationContextModule(applicationContextModule).build().injectActivity(this)
        DaggerDataBaseComponent.builder().applicationContextModule(applicationContextModule).build().injectViewModel(viewModel)
viewModel.getmdb(mdb)
//DaggerDataBaseComponent.builder().build()

        //  mdb = WeatherDataBase.getInstance(AppContext)

        show_task_page.setOnClickListener(View.OnClickListener { unit ->

            val task = Runnable {
                val weatherData =
                    mdb?.weatherDataDao()?.getAll()
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
//        add_to_list.setOnClickListener(View.OnClickListener { unit ->
//
//
//            insertToDataBase(task_title.text.toString(), convertRbToInT())
//
//        }

      //  )
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
        val task = Runnable { mdb?.weatherDataDao()?.insert(weatherData) }
        mDbWorkerThread.postTask(task)
    }

}