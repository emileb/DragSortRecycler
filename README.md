DragSortRecycler
================

DragSortRecycler allows you to easily add drag-and-drop functionaly to your existing RecyclerView.

The library consists of a single Java file which you connect to your RecyclerView - NO need to change or extend the RecyclerView itself, and NO need to extend your Adapter.


![demo](Screenshots/demo.gif)

## Features
* Quick and easy to integrate
* Very smooth switching of items
* Select a view resource ID in the view to use as the 'handle'
* ..Or choose an area on the left of the list to use as the 'handle'
* Change auto scroll speed and distance from the top and bottom of list
* Choose the floating views alpha and background color
* Works with varying size of views

## Limitations
* Currently only works with vertical lists, horizontal lists later
* RecycleView ItemAnimator must be set to null (possibly a custom animation will work)

## Integration
Example here: https://github.com/emileb/DragSortRecycler/blob/master/testdragsortrecycler/src/main/java/com/emtronics/testdragsortrecycler/MainActivity.java


Connecting to your RecyclerView
```java
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setItemAnimator(null);
        
        DragSortRecycler dragSortRecycler = new DragSortRecycler();
        dragSortRecycler.setViewHandleId(R.id.imageView); //View you wish to use as the handle
        
        dragSortRecycler.setOnItemMovedListener(new DragSortRecycler.OnItemMovedListener() {
            @Override
            public void onItemMoved(int from, int to) {
                Log.d(TAG, "onItemMoved " + from + " to " + to);
            }
        });

        recyclerView.addItemDecoration(dragSortRecycler);
        recyclerView.addOnItemTouchListener(dragSortRecycler);
        recyclerView.setOnScrollListener(dragSortRecycler.getScrollListener());
```

If you use this in your project let me know and I'll compile a list for this page.

## Methods

```java
setViewHandleId(int)
```
(Optional, use this or setLeftDragArea) Set the resource ID of the view you wish to use as the handle (the thing in the list item you drag)
<hr>

```java
setLeftDragArea(int)
```
(Optional, use this or setViewHandleId) Set the number of pixels from the left hand side of the list to use as the drag area. You will need to calculate this releative to the devices screen density.
<hr>

```java
setFloatingAlpha(float)
```
Set how transparent the 'float' view should be when you drag. Range is 0 (invisible) to 1 (opqaue)
<hr>

```java
setFloatingBgColor(int)
```
(Optional) Set the background color and alpha of the floating item, format is normal ARGB
<hr>

```java
setAutoScrollSpeed(float)
```
How fast it auto scrolls when you get to the top or bottom of the screen. Should acheive similar results across devices.
<hr>

```java
setAutoScrollWindow(float)
```
Sets where it starts to autoscroll, this is a fraction of the total height of the RecyclerView. So a value of 0.1 will mean that it will start scrolling at the bottom 10% and top 90% of the view.
<hr>






Please note this is a first release, if I have done something wrong please let me know! Thank you.
