package mumayank.com.airrecyclerview

import android.app.Activity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

/*class AirRecyclerView {

    interface AirRecyclerViewCallbacks {
        fun getViewType(position: Int): Int
        fun getViewLayout(viewType: Int): Int
        fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder
        fun getBindView(viewHolder: RecyclerView.ViewHolder, viewType: Int, position: Int)
        fun getSize(): Int
    }

    companion object {

        fun initAndGetAdapter(
            activity: Activity,
            recyclerView: RecyclerView,
            airRecyclerViewCallbacks: AirRecyclerViewCallbacks,
            layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        ): RecyclerView.Adapter<RecyclerView.ViewHolder>
        {
            val rvAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

                override fun getItemViewType(position: Int): Int {
                    return airRecyclerViewCallbacks.getViewType(position)
                }

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                    val view = LayoutInflater.from(activity).inflate(airRecyclerViewCallbacks.getViewLayout(viewType), parent, false)
                    return airRecyclerViewCallbacks.getViewHolder(view, viewType)
                }

                override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                    airRecyclerViewCallbacks.getBindView(holder, airRecyclerViewCallbacks.getViewType(position), position)
                }

                override fun getItemCount(): Int {
                    return airRecyclerViewCallbacks.getSize()
                }

            }

            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = rvAdapter
            return rvAdapter
        }

    }

}*/














/*
Examples:
 */


/*
View holder class:

class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val parentLayout = view.parentLayout
    val textView = view.textView
}
 */



/*
Layout managers:

LinearLayoutManager(activity)
LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)
GridLayoutManager(activity, spanCount)GridLayoutManager(activity, spanCount, LinearLayoutManager.HORIZONTAL, false)
GridLayoutManager(activity, spanCount, LinearLayoutManager.HORIZONTAL, true)
StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL)
 */