package mumayank.com.airrecyclerviewproject

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.item.view.*
import mumayank.com.airrecyclerview.AirRecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val strings = ArrayList<String>()
        for (i in 1..100) {
            strings.add(i.toString())
        }

        val VIEW_TYPE_1 = 0
        val VIEW_TYPE_2 = 1

        val rvAdapter = AirRecyclerView.initAndGetAdapter(
            this,
            findViewById(R.id.rv),
            object: AirRecyclerView.AirRecyclerViewCallbacks {
                override fun getViewType(position: Int): Int {
                    return 0
                    // can also make use of multiple view types like this: if (position%3 == 0) { return VIEW_TYPE_1 } else { return VIEW_TYPE_2 }
                }

                override fun getViewLayout(viewType: Int): Int {
                    return R.layout.item
                    // can also make use of viewType
                }

                override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                    return CustomViewHolder(view)
                    // can also make use of viewType
                }

                override fun getBindView(viewHolder: RecyclerView.ViewHolder, viewType: Int, position: Int) {
                    val customViewHolder = viewHolder as CustomViewHolder
                    val string = strings[position]
                    customViewHolder.textView.text = string
                    // can also make use of viewType
                }

                override fun getSize(): Int {
                    return strings.size
                }

            }
        )

        // rvAdapter can be used later to perform notifyDataSetChanged()
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val parentLayout = view.parentLayout
        val textView = view.textView
    }
}
