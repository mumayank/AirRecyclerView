package mumayank.com.airrecyclerview

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class AirRv(
    callback: Callback
){
    val rvAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    val rv: RecyclerView

    interface Callback {
        fun getAppContext(): Context
        fun getParentLayoutViewGroup(): ViewGroup
        fun getLayoutManager(): RecyclerView.LayoutManager
        fun getViewType(position: Int): Int
        fun getViewLayout(viewType: Int): Int
        fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder
        fun getBindView(viewHolder: RecyclerView.ViewHolder, viewType: Int, position: Int)
        fun getSize(): Int
    }

    init {
        rvAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            override fun getItemViewType(position: Int): Int {
                return callback.getViewType(position)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return callback.getViewHolder(LayoutInflater.from(callback.getAppContext()).inflate(callback.getViewLayout(viewType), parent, false), viewType)
            }

            override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
                return callback.getBindView(viewHolder, callback.getViewType(position), position)
            }

            override fun getItemCount(): Int {
                return callback.getSize()
            }

        }

        val rvView = LayoutInflater.from(callback.getAppContext()).inflate(R.layout.rv, callback.getParentLayoutViewGroup(), false)
        callback.getParentLayoutViewGroup().addView(rvView)
        rv = rvView.findViewById(R.id.rv)
        rv.layoutManager = callback.getLayoutManager()
        rv.adapter = rvAdapter
    }
}