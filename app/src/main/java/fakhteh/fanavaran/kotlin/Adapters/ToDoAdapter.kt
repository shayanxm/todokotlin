package fakhteh.fanavaran.kotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fakhteh.fanavaran.kotlin.R
import fakhteh.fanavaran.kotlin.database.WeatherData
import fakhteh.fanavaran.kotlin.databinding.TaskListRowBinding
import fakhteh.fanavaran.kotlin.viewmodel.TaskViewModel

class ToDoAdapter :
    RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {
    private lateinit var mainList:List<WeatherData>

    public var onItemClickx: ((pos: Int) -> Unit)? = null
    public var onEditClick: ((pos: Int) -> Unit)? = null

    var onItemClick: ((pos: Int, view: View) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.MyViewHolder {

        val binding: TaskListRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.task_list_row, parent, false)
      //  val binding: ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return MyViewHolder(binding)
    }



override fun onBindViewHolder(holder: ToDoAdapter.MyViewHolder, position: Int) {

    holder.bind(mainList[position])
//        val weatherData = mainList[position]
//        holder.bindView(weatherData, position)

//        holder.itemView.setOnClickListener {
//            Log.e("delete", "detelte it ${weatherData.id}")
//
//            val v: View = holder.itemView
//
//            onItemClick?.invoke(position, v)
//        }
}

    override fun getItemCount(): Int {
        return if(::mainList.isInitialized) mainList.size else 0
    }
    fun updatePostList(postList:List<WeatherData>){
        this.mainList = postList
        notifyDataSetChanged()
    }
  inner  class MyViewHolder(private val binding:TaskListRowBinding) : RecyclerView.ViewHolder(binding.root) {

        //2
       // private var view: View = v
        // private var photo: Photo? = null
        private var name: WeatherData? = null
      private val viewModel = TaskViewModel()

        //3

        //4
//        override fun onClick(v: View) {
//            Log.d("RecyclerView", "CLICK!")
//        }
//
//
//        fun bindView(name: WeatherData, position: Int) {
//
//viewModel.bind(name)
//            this.name = name
//            binding.
//         //   view.row_name.text = name.title
//        view.row_delete_item.setOnClickListener {
//           // Log.e("delete", "detelte it ${weatherData.id}")
//
//          //  val v: View = holder.itemView
//
//            onItemClickx?.invoke(position)
//        }
//            view.row_edit_item.setOnClickListener {
//                // Log.e("delete", "detelte it ${weatherData.id}")
//
//                //  val v: View = holder.itemView
//
//                onEditClick?.invoke(position)
//            }
//            when (name.prio) {
//                Prioritys.HIGH.prioNum -> view.setBackgroundColor(Color.RED)
//                Prioritys.MEDIUM.prioNum -> view.setBackgroundColor(Color.YELLOW)
//                Prioritys.LOW.prioNum -> view.setBackgroundColor(Color.GREEN)
//                Prioritys.UNDIFINED.prioNum -> view.setBackgroundColor(Color.GRAY)
//
//            }
//        }
//
//    }




 //   override fun getItemCount() = mainList.size
//    fun update(modelList:ArrayList<WeatherData>,adapter: ToDoAdapter) {
//     mainList = modelList
//     // adapter!!.notifyDataSetChanged()
// }
    fun bind(post:WeatherData){
        viewModel.bind(post)

        binding.viewModel = viewModel
    }
    // lateinit var mainList :List<String>
    lateinit var context: Context


}}