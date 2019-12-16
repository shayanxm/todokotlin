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

import fakhteh.fanavaran.kotlin.database.DbWorkerThread
import fakhteh.fanavaran.kotlin.database.WeatherData
import fakhteh.fanavaran.kotlin.database.WeatherDataBase
import fakhteh.fanavaran.kotlin.di.component.DaggerDataBaseComponent
import fakhteh.fanavaran.kotlin.di.modules.ApplicationContextModule
import kotlinx.android.synthetic.main.fragment_task_doalog.*
import kotlinx.android.synthetic.main.todo_list_row.view.*
import javax.inject.Inject
import android.view.WindowManager
import android.content.DialogInterface
import android.widget.EditText
import android.R
import androidx.appcompat.app.AlertDialog


/**
 * A simple [Fragment] subclass.
 */
class TaskDoalogFragment : DialogFragment() {
    private lateinit var adapter: ToDoAdapter
    @Inject
    lateinit var mDb: WeatherDataBase
    var mainLis = arrayListOf<WeatherData>()
    var changedName: String = ""

    companion object {
        fun newintance(tag: String): DialogFragment {
            val taskDoalogFragment = TaskDoalogFragment()

            return taskDoalogFragment

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerDataBaseComponent.builder()
            .applicationContextModule(ApplicationContextModule(requireContext().applicationContext))
            .build().injectFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(fakhteh.fanavaran.kotlin.R.layout.fragment_task_doalog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val thread = Thread {
            // mDb = WeatherDataBase.getInstance(requireContext())
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
                adapter.onItemClickx = { pos ->

                    val thread = Thread {
                        Log.e("onIemCLcik", " clicked on $pos")
                        mainLis.get(pos).id?.let { mDb?.weatherDataDao()?.deleteWithId(it) }
                        Log.e("onIemCLcik", " thread on ${Thread.currentThread().getName()}")
//                        adapter =
//                            ToDoAdapter(mDb?.weatherDataDao()?.getAll() as ArrayList<WeatherData>)
                        mainLis = (mDb?.weatherDataDao()?.getAll() as ArrayList<WeatherData>)
//                   adapter!!.notifyDataSetChanged()
                        adapter.update(
                            (mDb?.weatherDataDao()?.getAll() as ArrayList<WeatherData>), adapter
                        )
                    }
                    thread.start()
                    task_list_rv.adapter!!.notifyDataSetChanged()
                }
                adapter.onEditClick = { pos ->
                    alertEditTextKeyboardShown(pos)

                }
                task_list_rv.adapter = adapter

            }
        }
        thread.start()
        task_list_rv.layoutManager = LinearLayoutManager(activity)


    }

    fun alertEditTextKeyboardShown(pos: Int) {

        // creating the EditText widget programatically
        val editText = EditText(activity)

        // create the AlertDialog as final
        val dialog = AlertDialog.Builder(requireContext())
            .setMessage("متن جدید را وارد کنید.")

            // .setTitle("تعیین ادرس")
            .setView(editText)

            // Set the action buttons
            .setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, id ->
                    // StaticDataGenerator.customerAddress = editText.text.toString()
                    //customerAddTv.setText(StaticDataGenerator.customerAddress)
                    changedName = editText.text.toString()


                    val thread = Thread {
                        var itemToEdit = mainLis.get(pos)
                        itemToEdit.title = changedName
//                        mainLis.get(pos)?.let { mDb?.weatherDataDao()?.editWithObj(it) }

                        mDb?.weatherDataDao()?.editWithObj(itemToEdit)
                        mainLis = (mDb?.weatherDataDao()?.getAll() as ArrayList<WeatherData>)

                        adapter.update(
                            (mDb?.weatherDataDao()?.getAll() as ArrayList<WeatherData>), adapter
                        )
                    }
                    thread.start()
                    task_list_rv.adapter!!.notifyDataSetChanged()
                })

            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                // removes the AlertDialog in the screen
            })
            .create()

        // set the focus change listener of the EditText
        // this part will make the soft keyboard automaticall visible
        editText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                dialog.getWindow()
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            }
        }

        dialog.show()

    }

}
