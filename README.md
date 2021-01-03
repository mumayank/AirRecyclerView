
![alt text](https://github.com/mumayank/AirRecyclerView/blob/master/image.png "Logo")

# AirRecyclerView
An android library written in Kotlin which makes implementing RecyclerView in your project a piece of cake!

###### (method counts ~50, size ~50KB)

[![](https://jitpack.io/v/mumayank/AirRecyclerView.svg)](https://jitpack.io/#mumayank/AirRecyclerView)

## Usage

```kotlin
// hint: these val can be used for multiple view types
val VIEW_TYPE_1 = 0
val VIEW_TYPE_2 = 1

val airRv = AirRv(object: AirRv.Callback {
    override fun getAppContext(): Context? {
        return this@MainActivity
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
            Toast.makeText(this@MainActivity, string, Toast.LENGTH_SHORT).show()
        }
        // hint: you may init different view holders using viewType param
    }

})

// hint: airRv.rvAdapter can be used later to perform notifyDataSetChanged()
```

#### Note: This library is 100% Java interoperable.

## Setup
Add this line in your root build.gradle at the end of repositories:

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' } // this line
  }
}
  ```
Add this line in your app build.gradle:
```gradle
dependencies {
  implementation 'com.github.mumayank:AirRecyclerView:LATEST_VERSION' // this line
}
```
where LATEST_VERSION is [![](https://jitpack.io/v/mumayank/AirRecyclerView.svg)](https://jitpack.io/#mumayank/AirRecyclerView)

### Just for reference:

CustomViewHolder:
```kotlin
// hint: You may create your view holder classes easily like this
class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val parentLayout: LinearLayout = view.parentLayout
            val textView: TextView = view.textView
}
```

### Special note when using RV in RV

define your viewPool:

```kotlin
val viewPool = RecyclerView.RecycledViewPool()
```

And use it like this:

```kotlin
subAirRv.rv.setRecycledViewPool(viewPool)
```

### Special note when Infinite Scrolling

Sometimes  we need pagination to display results in the RV. The conventional approach is to show page number at the bottom of the page with the option to go to the previous or the next page. This approach sucks. Ideally, more content (next page) should automatically load.

Here's how to do it easily using AirRv:

Add a switch var
```kotlin
private var isLoading = false
```

Set up an onScrollListener:
```kotlin
airRv?.rv?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == (airRv?.rvAdapter?.itemCount as Int) - 1) {
                        // todo: load more data here and update your list
                        isLoading = true
                    }
                }
            }
        })```

When the data is done loading, simply turn the switch var off
```kotlin
isLoading = false
```

Additionally, here's how you should implement infinite scroll
1. have a page no count
2. have a boolean var tracking if end of the list has reached
3. have a boolean var tracking if the content is loading (as mentioned above)

Have 3 view types:
1. Your regular item
2. A load more / progress item (added in the end)
3. An item denoting end of the list

Your list size will be:
```kotlin
override fun dGetSize(): Int? {
	return if (rvList.size == 0) 0 else rvList.size + 1
}
```

You can return view type as:
```kotlin
			override fun eGetViewType(position: Int): Int? {
                return when {
                    position != rvList.size -> {
                        ViewType.ITEM.index
                    }
                    isEndOfListReached -> {
                        ViewType.END_OF_LIST.index
                    }
                    else -> {
                        ViewType.LOAD_MORE.index
                    }
                }
            }
```
### If using view binding
Enable view binding
```gradle
android {
    buildFeatures {
        viewBinding true
    }
}
```
Your custom view holder class simply becomes:
```kotlin
class CustomViewHolder(val binding: InfiniteRvItemBinding) : RecyclerView.ViewHolder(binding.root)
```
When defining AirRv,
```kotlin
var parents: ViewGroup? = null
```
Define it as:
```kotlin
			override fun fGetViewLayoutId(parent: ViewGroup, viewType: Int): Int? {
                parents = parent
                // rest of the code
            }
```
And define your view holder as:
```kotlin
			override fun gGetViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return  ItemViewHolder(
                        	InfiniteRvItemBinding.inflate(
                            	layoutInflater,
                            	parents,
                            	false
                        )
            }
```
