
![alt text](https://github.com/mumayank/AirRecyclerView/blob/master/image.png "Logo")

# AirRecyclerView
An android library written in Kotlin which makes implementing RecyclerView in your project a piece of cake!

###### (method counts ~50, size ~50KB)

[![](https://jitpack.io/v/mumayank/AirRecyclerView.svg)](https://jitpack.io/#mumayank/AirRecyclerView)

## Usage
```kotlin
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
```

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

That's all!
