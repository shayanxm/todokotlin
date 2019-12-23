package fakhteh.fanavaran.kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fakhteh.fanavaran.kotlin.database.WeatherData
import fakhteh.fanavaran.kotlin.di.BaseViewModel
import kotlinx.android.synthetic.main.fragment_task_doalog.*

class TaskViewModel : ViewModel() {
    private val taskTitle = MutableLiveData<String>()
    private val taskPrio = MutableLiveData<Int>()

    fun bind(weatherData: WeatherData) {
        taskTitle.value = weatherData.title
        taskPrio.value = weatherData.prio
    }

    fun getPostTitle(): MutableLiveData<String> {
        return taskTitle
    }

    fun getPostBody(): MutableLiveData<Int> {
        return taskPrio
    }

    fun edtTask() {}
    fun deleteTask() {
        Log.e("delete testing", "delte task")

//        val thread = Thread {
//            Log.e("onIemCLcik", " clicked on $pos")
//
//            mainLis.get(pos).id?.let { mDb?.weatherDataDao()?.deleteWithId(it) }
//            Log.e("onIemCLcik", " thread on ${Thread.currentThread().getName()}")
////                        adapter =
////                            ToDoAdapter(mDb?.weatherDataDao()?.getAll() as ArrayList<WeatherData>)
//            mainLis = (mDb?.weatherDataDao()?.getAll() as ArrayList<WeatherData>)
////                   adapter!!.notifyDataSetChanged()
//            adapter.update(
//                (mDb?.weatherDataDao()?.getAll() as ArrayList<WeatherData>), adapter
//            )
//
//
//        }
//        thread.start()
//        task_list_rv.recycledViewPool.clear()
//        task_list_rv.adapter!!.notifyDataSetChanged()
    }
}