package mumayank.com.airrecyclerview

import android.content.Context
import android.telecom.Call
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
        fun getAppContext(): Context?
        fun getLayoutManager(appContext: Context?): RecyclerView.LayoutManager?
        fun getRvHolderViewGroup(): ViewGroup?
        fun getEmptyView(): View?
        fun getSize(): Int?
        fun getViewType(position: Int): Int?
        fun getViewLayoutId(viewType: Int): Int?
        fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder
        fun getBindView(viewHolder: RecyclerView.ViewHolder, viewType: Int, position: Int)
    }

    init {
        callback.getRvHolderViewGroup()?.removeAllViews()

        rvAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            override fun getItemViewType(position: Int): Int {
                return callback.getViewType(position) ?: 0
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return callback.getViewHolder(LayoutInflater.from(callback.getAppContext()).inflate(callback.getViewLayoutId(viewType) ?: 0, parent, false), viewType)
            }

            override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
                return callback.getBindView(viewHolder, callback.getViewType(position) ?: 0, position)
            }

            override fun getItemCount(): Int {
                return callback.getSize() ?: 0
            }

        }

        val rvView = LayoutInflater.from(callback.getAppContext()).inflate(R.layout.rv, callback.getRvHolderViewGroup(), false)
        callback.getRvHolderViewGroup()?.addView(rvView)
        showOrHideEmptyView(callback.getEmptyView(), callback.getSize())
        rv = rvView.findViewById(R.id.rv)
        rv.layoutManager = callback.getLayoutManager(callback.getAppContext())
        rv.adapter = rvAdapter
        this.callback = callback
    }

    private fun showOrHideEmptyView(emptyView: View?, size: Int? = 0) {
        emptyView?.visibility = if (size == 0) View.VISIBLE else View.GONE
    }

    fun notifyDataSetChanged() {
        rv.adapter?.notifyDataSetChanged()
        showOrHideEmptyView(callback?.getEmptyView(), callback?.getSize())
    }
}