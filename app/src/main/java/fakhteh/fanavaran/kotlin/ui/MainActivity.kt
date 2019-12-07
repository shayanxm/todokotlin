package fakhteh.fanavaran.kotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fakhteh.fanavaran.kotlin.R
import fakhteh.fanavaran.kotlin.model.DataBase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object {
        val tranfserTag = "fakhteh.fanavaran.kotlin.tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        show_task_page.setOnClickListener(View.OnClickListener { unit ->
            val transaction = supportFragmentManager.beginTransaction()
            val previous = supportFragmentManager.findFragmentByTag(tranfserTag)
            if (previous != null) {
                transaction.remove(previous)
            }
            transaction.addToBackStack(null)

            val dialogFragment =
                TaskDoalogFragment.newintance("parameter")
            dialogFragment.show(transaction, tranfserTag)
        })
add_to_list.setOnClickListener(View.OnClickListener { unit ->
    DataBase.taskList?.add(task_title.text.toString())
Log.e("testing",DataBase.taskList!!.size.toString() +"\n"+DataBase.taskList!!.get(0))
}

)
    }

}