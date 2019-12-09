package fakhteh.fanavaran.kotlin.ui


import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fakhteh.fanavaran.kotlin.Adapters.ToDoAdapter
import fakhteh.fanavaran.kotlin.R
import fakhteh.fanavaran.kotlin.database.DbWorkerThread
import fakhteh.fanavaran.kotlin.database.WeatherData
import fakhteh.fanavaran.kotlin.database.WeatherDataBase
import fakhteh.fanavaran.kotlin.model.DataBase
import fakhteh.fanavaran.kotlin.model.Prioritys
import kotlinx.android.synthetic.main.fragment_task_doalog.*

/**
 * A simple [Fragment] subclass.
 */
class TaskDoalogFragment : DialogFragment() {
    private lateinit var adapter: ToDoAdapter
    private var mDb: WeatherDataBase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread
 var mainLis=arrayListOf<String>()
    private val mUiHandler = Handler()
    companion object {
        fun newintance(tag: String): DialogFragment {
            val taskDoalogFragment = TaskDoalogFragment();

            return taskDoalogFragment

        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_task_doalog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val thread = Thread {
            val weatherData =
                mDb?.weatherDataDao()?.getAll()
            if (weatherData == null || weatherData?.size == 0) {
                // showToast("No data in cache..!!", Toast.LENGTH_SHORT)
            } else {
                val sizeL = weatherData.size
                mainLis= convertDataToStringArray(weatherData as ArrayList<WeatherData>)
                Log.e("database", "S:$sizeL")
            }
        }
    thread.start()
        task_list_rv.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
//            mDbWorkerThread = DbWorkerThread("dbWorkerThread")
//            mDbWorkerThread.start()
//            mDb = WeatherDataBase.getInstance(requireContext())
//            val task = Runnable {
//                val weatherData =
//                    mDb?.weatherDataDao()?.getAll()
//                mUiHandler.post({
//                    if (weatherData == null || weatherData?.size == 0) {
//                        // showToast("No data in cache..!!", Toast.LENGTH_SHORT)
//                    } else {
//                        val sizeL=weatherData.size
//                        Log.e("database","S:$sizeL")
//                    }
//                })
//            }
//            mDbWorkerThread.postTask(task)


            val sizeL = mainLis.size
            Log.e("database", "S:$sizeL")
            adapter = ToDoAdapter(   mainLis as ArrayList<String>)
        }

    }

    private fun convertDataToStringArray(mainLis: ArrayList<WeatherData>):ArrayList<String> {
        val res= arrayListOf<String>()
        for (i in mainLis) { res.add(element = i.title) }
        return res
    }



}
