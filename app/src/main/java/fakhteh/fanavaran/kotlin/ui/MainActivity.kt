package fakhteh.fanavaran.kotlin.ui

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fakhteh.fanavaran.kotlin.R
import fakhteh.fanavaran.kotlin.model.DataBase
import fakhteh.fanavaran.kotlin.model.Prioritys
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
            DataBase.priorityOfTaskList.add(convertRbToIn())
            Log.e(
                "testing",
                DataBase.taskList!!.size.toString() + "\n" + DataBase.taskList!!.get(0)
            )
        }

        )
    }

    private fun convertRbToIn(): Prioritys {
if(low_prio_RB.isChecked)return Prioritys.LOW
    if(med_prio_RB.isChecked)return Prioritys.MEDIUM
        if(hg_prio_RB.isChecked)return Prioritys.HIGH
        Toast.makeText(this@MainActivity,"you can alsow enter a priority ",Toast.LENGTH_SHORT)
        return Prioritys.UNDIFINED

    }

}