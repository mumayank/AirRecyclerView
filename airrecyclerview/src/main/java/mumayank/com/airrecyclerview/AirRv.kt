package mumayank.com.airrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class AirRv(
    callback: Callback
){
    var callback: Callback? = null
    val rvAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    val rv: RecyclerView

    interface Callback {
        fun aGetAppContext(): Context?
        fun bGetLayoutManager(appContext: Context?): RecyclerView.LayoutManager?
        fun cGetRvHolderViewGroup(): ViewGroup?
        fun dGetSize(): Int?
        fun eGetViewType(position: Int): Int?
        fun fGetViewLayoutId(parent: ViewGroup, viewType: Int): Int?
        fun gGetViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder
        fun hGetBindView(viewHolder: RecyclerView.ViewHolder, viewType: Int, position: Int)
    }

    init {
        callback.cGetRvHolderViewGroup()?.removeAllViews()

        rvAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            override fun getItemViewType(position: Int): Int {
                return callback.eGetViewType(position) ?: 0
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return callback.gGetViewHolder(LayoutInflater.from(callback.aGetAppContext()).inflate(callback.fGetViewLayoutId(parent, viewType) ?: 0, parent, false), viewType)
            }

            override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
                return callback.hGetBindView(viewHolder, callback.eGetViewType(position) ?: 0, position)
            }

            override fun getItemCount(): Int {
                return callback.dGetSize() ?: 0
            }

        }

        val rvView = LayoutInflater.from(callback.aGetAppContext()).inflate(R.layout.rv, callback.cGetRvHolderViewGroup(), false)
        callback.cGetRvHolderViewGroup()?.addView(rvView)
        rv = rvView.findViewById(R.id.rv)
        rv.layoutManager = callback.bGetLayoutManager(callback.aGetAppContext())
        rv.adapter = rvAdapter
        this.callback = callback
    }

    fun notifyDataSetChanged() {
        rv.adapter?.notifyDataSetChanged()
    }
}