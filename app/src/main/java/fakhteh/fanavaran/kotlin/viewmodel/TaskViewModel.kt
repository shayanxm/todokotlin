package fakhteh.fanavaran.kotlin.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fakhteh.fanavaran.kotlin.Adapters.ToDoAdapter
import fakhteh.fanavaran.kotlin.database.WeatherData
import fakhteh.fanavaran.kotlin.database.WeatherDataBase
import fakhteh.fanavaran.kotlin.di.BaseViewModel
import kotlinx.android.synthetic.main.fragment_task_doalog.*
import javax.inject.Inject

class TaskViewModel(var context: Context) : ViewModel() {
    val postListAdapter: ToDoAdapter = ToDoAdapter()
    private val taskTitle = MutableLiveData<String>()
    private val taskPrio = MutableLiveData<Int>()
    @Inject
    lateinit var mdb: WeatherDataBase
    fun bind(weatherData: WeatherData) {
        taskTitle.value = weatherData.title
        taskPrio.value = weatherData.prio
    }
    init {
        loadList()
    }

    fun getPostTitle(): MutableLiveData<String> {
        return taskTitle
    }

    fun getPostBody(): MutableLiveData<Int> {
        return taskPrio
    }
    fun loadList(){
//        var mainList=mdb.weatherDataDao().getAll()
//        mainList.forEach { taskTitle.value.plus(it.title )}
postListAdapter.updatePostList(mdb.weatherDataDao().getAll())
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