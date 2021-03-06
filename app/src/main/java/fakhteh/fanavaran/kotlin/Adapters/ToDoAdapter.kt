package fakhteh.fanavaran.kotlin.Adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fakhteh.fanavaran.kotlin.R
import fakhteh.fanavaran.kotlin.model.DataBase
import fakhteh.fanavaran.kotlin.model.Prioritys
import kotlinx.android.synthetic.main.todo_list_row.view.*

class ToDoAdapter(private val mainList: ArrayList<String>) :
    RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        // private var photo: Photo? = null
        private var name: String? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }

        fun bindView(name: String, position: Int) {
//            this.photo = photo
//            Picasso.with(view.context).load(photo.url).into(view.itemImage)
//            view.itemDate.text = photo.humanDate
//            view.itemDescription.text = photo.explanation
//
            this.name = name
            view.row_name.text = name
            when (DataBase.priorityOfTaskList.get(position)) {
                Prioritys.HIGH -> view.setBackgroundColor(Color.RED)
                Prioritys.MEDIUM -> view.setBackgroundColor(Color.YELLOW)
                Prioritys.LOW -> view.setBackgroundColor(Color.GREEN)
                Prioritys.UNDIFINED -> view.setBackgroundColor(Color.GRAY)

            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.MyViewHolder {
        val inflatedView = parent.inflate(R.layout.todo_list_row, false)
        return MyViewHolder(inflatedView)
    }

    override fun getItemCount() = mainList.size

    override fun onBindViewHolder(holder: ToDoAdapter.MyViewHolder, position: Int) {


        val itemPhoto = mainList[position]
        holder.bindView(itemPhoto, position)

    }

    // lateinit var mainList :List<String>
    lateinit var context: Context


}