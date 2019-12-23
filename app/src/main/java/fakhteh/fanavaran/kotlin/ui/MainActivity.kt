package fakhteh.fanavaran.kotlin.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import fakhteh.fanavaran.kotlin.R
import fakhteh.fanavaran.kotlin.database.*
import fakhteh.fanavaran.kotlin.databinding.ActivityMainBinding
import fakhteh.fanavaran.kotlin.di.component.DaggerDataBaseComponent

import fakhteh.fanavaran.kotlin.di.modules.ApplicationContextModule
import fakhteh.fanavaran.kotlin.di.modules.DataBaseModule
import fakhteh.fanavaran.kotlin.model.Prioritys
import fakhteh.fanavaran.kotlin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import androidx.lifecycle.ViewModelProviders
import fakhteh.fanavaran.kotlin.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {
    //   private var mDb: WeatherDataBase? = null

    @Inject
    lateinit var AppContext: Context
    @Inject
    lateinit var mdb: WeatherDataBase

private lateinit var viewModel:MainViewModel

    lateinit var binding: ActivityMainBinding
    companion object {
        val tranfserTag = "fakhteh.fanavaran.kotlin.tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel=viewModel
        val applicationContextModule = ApplicationContextModule(applicationContext)
val taskViewModel=TaskViewModel()
        DaggerDataBaseComponent.builder().applicationContextModule(applicationContextModule).build().injectViewModel(viewModel)
        DaggerDataBaseComponent.builder().applicationContextModule(applicationContextModule).build().injectTaskVm(taskViewModel)
        show_task_page.setOnClickListener(View.OnClickListener { unit ->
            goToDialogFragment()
        })
    }

    private fun goToDialogFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val previous = supportFragmentManager.findFragmentByTag(tranfserTag)
        if (previous != null) {
            transaction.remove(previous)
        }
        transaction.addToBackStack(null)

        val dialogFragment =
            TaskDoalogFragment.newintance("parameter")
        dialogFragment.show(transaction, tranfserTag)
    }

}