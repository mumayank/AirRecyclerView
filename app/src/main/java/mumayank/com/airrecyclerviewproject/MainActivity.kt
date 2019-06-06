package mumayank.com.airrecyclerviewproject

import android.app.Activity
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
import mumayank.com.airrecyclerview.AirRv

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        setupRv()
    }

    private fun setupRv() {
        val strings = ArrayList<String>()
        for (i in 1..100) {
            strings.add(i.toString())
        }

        // hint: these vars can be used for multiple view types
        val VIEW_TYPE_1 = 0
        val VIEW_TYPE_2 = 1

        val airRv = AirRv(object: AirRv.Callback {
            override fun getRvHolderViewGroup(): ViewGroup? {
                return rvHolder
            }

            override fun getEmptyView(): View? {
                return emptyTV
            }

            override fun getLayoutManager(appContext: Context?): RecyclerView.LayoutManager? {
                return LinearLayoutManager(appContext)
            }

            override fun getAppContext(): Context {
                return this@MainActivity.applicationContext
            }
            override fun getViewType(position: Int): Int {
                return 0
                // hint: you may return different view type vals using position param
            }

            override fun getViewLayoutId(viewType: Int): Int {
                return R.layout.item
                // hint: you may return different view layout vals using viewType param
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return CustomViewHolder(view)
                // hint: you may return different custom view holder vals using viewType param
            }

            override fun getBindView(viewHolder: RecyclerView.ViewHolder, viewType: Int, position: Int) {
                val customViewHolder = viewHolder as CustomViewHolder
                val string = strings[position]

                customViewHolder.textView.text = string
                customViewHolder.parentLayout.setOnClickListener {
                    Toast.makeText(this@MainActivity, string, Toast.LENGTH_SHORT).show()
                }
                // hint: you may init different view holders using viewType param
            }

            override fun getSize(): Int {
                return strings.size
            }

        })

        // hint: airRv.rvAdapter can be used later to perform notifyDataSetChanged()
    }

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val parentLayout: LinearLayout = view.parentLayout
        val textView: TextView = view.textView
    }

}
