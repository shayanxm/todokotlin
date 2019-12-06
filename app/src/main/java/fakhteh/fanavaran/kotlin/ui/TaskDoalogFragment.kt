package fakhteh.fanavaran.kotlin.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fakhteh.fanavaran.kotlin.Adapters.ToDoAdapter
import fakhteh.fanavaran.kotlin.R
import fakhteh.fanavaran.kotlin.model.DataBase
import kotlinx.android.synthetic.main.fragment_task_doalog.*

/**
 * A simple [Fragment] subclass.
 */
class TaskDoalogFragment : DialogFragment() {
    private lateinit var adapter: ToDoAdapter

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
        task_list_rv.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = ToDoAdapter(DataBase.taskList as ArrayList<String>)
        }

    }




}
