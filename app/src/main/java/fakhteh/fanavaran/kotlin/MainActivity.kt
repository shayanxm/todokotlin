package fakhteh.fanavaran.kotlin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.IdRes
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object{
        val tranfserTag="fakhteh.fanavaran.kotlin.tag"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
show_task_page.setOnClickListener(View.OnClickListener {
    unit->
    val transaction = supportFragmentManager.beginTransaction()
    val previous = supportFragmentManager.findFragmentByTag(tranfserTag)
    if (previous != null) {
        transaction.remove(previous)
    }
    transaction.addToBackStack(null)

    val dialogFragment = TaskDoalogFragment.newIntance("parameter")
    dialogFragment.show(transaction, tranfserTag)
})

    }
}