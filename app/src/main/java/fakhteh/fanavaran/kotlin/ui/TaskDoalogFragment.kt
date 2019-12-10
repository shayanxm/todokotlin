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
import kotlinx.android.synthetic.main.fragment_task_doalog.*

/**
 * A simple [Fragment] subclass.
 */
class TaskDoalogFragment : DialogFragment() {
    private lateinit var adapter: ToDoAdapter
    private var mDb: WeatherDataBase? = null
    var mainLis = arrayListOf<WeatherData>()

    companion object {
        fun newintance(tag: String): DialogFragment {
            val taskDoalogFragment = TaskDoalogFragment();

            return taskDoalogFragment

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_task_doalog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val thread = Thread {
            mDb = WeatherDataBase.getInstance(requireContext())
            val weatherData =
                mDb?.weatherDataDao()?.getAll()
            if (weatherData == null || weatherData?.size == 0) {
                // showToast("No data in cache..!!", Toast.LENGTH_SHORT)
                Log.e("databasex", "onnull")
            } else {
                val sizeL = weatherData.size
                mainLis = weatherData as ArrayList<WeatherData>
                adapter = ToDoAdapter(mainLis as ArrayList<WeatherData>)
                Log.e("onIemCLcik", " out thread on ${Thread.currentThread().getName()}")
                adapter.onItemClick = { pos, view ->
                    val thread = Thread {
                        Log.e("onIemCLcik", " clicked on $pos")
                        mainLis.get(pos).id?.let { mDb?.weatherDataDao()?.deleteWithId(it) }
                        Log.e("onIemCLcik", " thread on ${Thread.currentThread().getName()}")
//                        adapter =
//                            ToDoAdapter(mDb?.weatherDataDao()?.getAll() as ArrayList<WeatherData>)
                        mainLis = (mDb?.weatherDataDao()?.getAll() as ArrayList<WeatherData>)
//                   adapter!!.notifyDataSetChanged()
                        adapter.update(
                            (mDb?.weatherDataDao()?.getAll() as ArrayList<WeatherData>), adapter)
                    }
                    thread.start()
                    task_list_rv.adapter!!.notifyDataSetChanged()
                }
                task_list_rv.adapter = adapter

            }
        }
        thread.start()
        task_list_rv.layoutManager = LinearLayoutManager(activity)


    }


}
