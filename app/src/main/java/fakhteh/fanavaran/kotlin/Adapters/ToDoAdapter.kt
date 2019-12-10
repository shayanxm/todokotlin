package fakhteh.fanavaran.kotlin.Adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fakhteh.fanavaran.kotlin.R
import fakhteh.fanavaran.kotlin.database.WeatherData
import fakhteh.fanavaran.kotlin.model.Prioritys
import kotlinx.android.synthetic.main.todo_list_row.view.*

class ToDoAdapter(private val mainList: ArrayList<WeatherData>) :
    RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        // private var photo: Photo? = null
        private var name: WeatherData? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }



        fun bindView(name: WeatherData, position: Int) {

            this.name = name
            view.row_name.text = name.title

            when (name.prio) {
                Prioritys.HIGH.prioNum -> view.setBackgroundColor(Color.RED)
                Prioritys.MEDIUM.prioNum -> view.setBackgroundColor(Color.YELLOW)
                Prioritys.LOW.prioNum -> view.setBackgroundColor(Color.GREEN)
                Prioritys.UNDIFINED.prioNum -> view.setBackgroundColor(Color.GRAY)

            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.MyViewHolder {
        val inflatedView = parent.inflate(R.layout.todo_list_row, false)
        return MyViewHolder(inflatedView)
    }

    override fun getItemCount() = mainList.size

    override fun onBindViewHolder(holder: ToDoAdapter.MyViewHolder, position: Int) {


        val weatherData = mainList[position]
        holder.bindView(weatherData, position)

    }

    // lateinit var mainList :List<String>
    lateinit var context: Context


}