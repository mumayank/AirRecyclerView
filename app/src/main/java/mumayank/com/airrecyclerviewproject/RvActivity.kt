package mumayank.com.airrecyclerviewproject

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.rv_activity.*
import mumayank.com.airrecyclerview.AirRv

class RvActivity : AppCompatActivity() {

    val strings = ArrayList<String>()
    var airRv: AirRv? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rv_activity)
        setupRv()

        fillListButton.setOnClickListener {
            emptyListButton.performClick()
            for (i in 1..10) {
                strings.add(i.toString())
            }
            airRv?.notifyDataSetChanged()
        }

        emptyListButton.setOnClickListener {
            strings.clear()
            airRv?.notifyDataSetChanged()
        }
    }

    private fun setupRv() {
        // hint: these vars can be used for multiple view types
        val VIEW_TYPE_1 = 0
        val VIEW_TYPE_2 = 1

        airRv = AirRv(object: AirRv.Callback {
            override fun getAppContext(): Context? {
                return this@RvActivity
            }

            override fun getLayoutManager(appContext: Context?): RecyclerView.LayoutManager? {
                return LinearLayoutManager(appContext)
            }

            override fun getRvHolderViewGroup(): ViewGroup? {
                return rvHolder
            }

            override fun getEmptyView(): View? {
                return emptyTV
            }

            override fun getSize(): Int? {
                return strings.size
            }

            override fun getViewType(position: Int): Int? {
                return 0
                // hint: you may return different view type val using position param
            }

            override fun getViewLayoutId(viewType: Int): Int? {
                return R.layout.item
                // hint: you may return different view layout val using viewType param
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return CustomViewHolder(view)
                // hint: you may return different view layout val using viewType param
            }

            override fun getBindView(viewHolder: RecyclerView.ViewHolder, viewType: Int, position: Int) {
                val customViewHolder = viewHolder as CustomViewHolder
                val string = strings[position]

                customViewHolder.textView.text = string
                customViewHolder.parentLayout.setOnClickListener {
                    Toast.makeText(this@RvActivity, string, Toast.LENGTH_SHORT).show()
                }
                // hint: you may init different view holders using viewType param
            }

        })

        // hint: airRv.rvAdapter can be used later to perform notifyDataSetChanged()
    }

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val parentLayout: LinearLayout = view.parentLayout
        val textView: TextView = view.textView
    }
}
