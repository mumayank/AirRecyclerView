
![alt text](https://github.com/mumayank/AirRecyclerView/blob/master/image.png "Logo")

# AirRecyclerView
An android library written in Kotlin which makes implementing RecyclerView in your project a piece of cake!

###### (method counts ~50, size ~50KB)

[![](https://jitpack.io/v/mumayank/AirRecyclerView.svg)](https://jitpack.io/#mumayank/AirRecyclerView)

## Usage
```kotlin
// hint: these vars can be used for multiple view types
val VIEW_TYPE_1 = 0
val VIEW_TYPE_2 = 1

val airRv = AirRv(object: AirRv.Callback {
override fun getLayoutManager(): RecyclerView.LayoutManager {
    return LinearLayoutManager(this@MainActivity)
}

override fun getAppContext(): Context {
    return this@MainActivity.applicationContext
}

override fun getParentLayoutViewGroup(): ViewGroup {
    return findViewById(R.id.parentLayout) as ViewGroup
}

override fun getViewType(position: Int): Int {
    return 0
    // hint: you may return different view type vals using position param
}

override fun getViewLayout(viewType: Int): Int {
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

#### Just for reference:

CustomViewHolder:
```kotlin
// hint: You may create your view holder classes easily like this
class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val parentLayout: LinearLayout = view.parentLayout
            val textView: TextView = view.textView
}
```

That's all!
