package app.softxperttask.view.carsDataList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.softxperttask.R
import app.softxperttask.databinding.ItemCarsListBinding
import app.softxperttask.model.carsDataResponse.Data
import java.util.*

/**
 * A custom adapter to use with the RecyclerView widget.
 */
class CarsListAdapter(private val mContext: Context, private var modelList: ArrayList<Data>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mItemClickListener: OnItemClickListener? =
        null

    fun updateList(modelList: ArrayList<Data>) {
        this.modelList = modelList
        notifyDataSetChanged()
    }

    fun addDataToExist(mDataSet: List<Data>?) {
        this.modelList.addAll(mDataSet!!)
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemCarsListBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_cars_list, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {

        //Here you can fill your row view
        if (holder is ViewHolder) {
            val model = getItem(position)
            val genericViewHolder =
                holder
           genericViewHolder.bind(model)
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    private fun getItem(position: Int): Data {
        return modelList[position]
    }

    interface OnItemClickListener {
        fun onItemClick(
            view: View?,
            position: Int,
            model: Data?
        )
    }

    inner class ViewHolder(val binding: ItemCarsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Data){
            binding.root.setOnClickListener {
                mItemClickListener?.onItemClick(
                    it,
                    adapterPosition,
                    modelList[adapterPosition]
                )
            }

            binding.carItem = model
            binding.executePendingBindings()

        }
    }

}