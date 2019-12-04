package fakhteh.fanavaran.kotlin


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

/**
 * A simple [Fragment] subclass.
 */
class TaskDoalogFragment : DialogFragment() {
    companion object{
fun newintance( tag:String):DialogFragment {
    val taskDoalogFragment = TaskDoalogFragment();

    return taskDoalogFragment

}
}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var view :View = inflater.inflate(R.layout.fragment_task_doalog, container, false)

        return view
    }


}
