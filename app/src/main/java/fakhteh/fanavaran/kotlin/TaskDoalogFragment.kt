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
fun newIntance( tag:String):DialogFragment {
    val taskDoalogFragment = TaskDoalogFragment();

    return taskDoalogFragment

}}
//        fun newInstance(someInt: Int): MyFragment {
//            val myFragment = MyFragment()
//
//            val args = Bundle()
//            args.putInt("someInt", someInt)
//            myFragment.setArguments(args)
//
//            return myFragment
//        }
//}
//
//    fun newInstance(inComingId: Int): SubCategoryFragment {
//
//        val args = Bundle()
//        args.putInt(CATEGORY_ID, inComingId)
//        val fragment = SubCategoryFragment()
//        fragment.setArguments(args)
//        return fragment
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var view :View = inflater.inflate(R.layout.fragment_task_doalog, container, false)

        return view
    }


}
