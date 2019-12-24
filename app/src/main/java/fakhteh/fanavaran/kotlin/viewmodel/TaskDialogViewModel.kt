package fakhteh.fanavaran.kotlin.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import fakhteh.fanavaran.kotlin.Adapters.ToDoAdapter
import fakhteh.fanavaran.kotlin.database.WeatherData
import fakhteh.fanavaran.kotlin.database.WeatherDataBase
import fakhteh.fanavaran.kotlin.di.component.DaggerDataBaseComponent
import fakhteh.fanavaran.kotlin.di.modules.ApplicationContextModule
import javax.inject.Inject

class TaskDialogViewModel(context: Context) : ViewModel() {
    val postListAdapter: ToDoAdapter = ToDoAdapter()

    lateinit var mdb: WeatherDataBase

    init {
        mdb = WeatherDataBase.getInstance(context)!!


        loadList()
    }

    fun loadList() {
//        var mainList=mdb.weatherDataDao().getAll()
//        mainList.forEach { taskTitle.value.plus(it.title )}
        val thread = Thread {
            postListAdapter.updatePostList(mdb.weatherDataDao().getAll())
        }
        thread.start()



    }
}