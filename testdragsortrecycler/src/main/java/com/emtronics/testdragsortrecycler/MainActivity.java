package com.emtronics.testdragsortrecycler;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emtronics.dragsortrecycler.DragSortRecycler;
import com.emtronics.testdragsortrecycler.R;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String TAG = "MainActivity";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        final ArrayList<Integer> items = new ArrayList<>();

        for (int n=0;n<100;n++)
        {
            items.add(n);
        }

        final CustomAdapter adapter = new CustomAdapter(items,this);
        adapter.setHasStableIds(true);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setItemAnimator(null);

        DragSortRecycler dragSortRecycler = new DragSortRecycler();
        dragSortRecycler.setViewHandleId(R.id.imageView);
        dragSortRecycler.setFloatingAlpha(0.4f);
        dragSortRecycler.setFloatingBgColor(0x800000FF);
        dragSortRecycler.setAutoScrollSpeed(0.3f);
        dragSortRecycler.setAutoScrollWindow(0.1f);

        dragSortRecycler.setItemMoveInterface(new DragSortRecycler.ItemMovedInterface() {
            @Override
            public void moveElement(int from, int to) {
                Log.d(TAG, "moveElement " + from + " to " + to);
                Integer item = items.remove(from);
                items.add(to,item);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.addItemDecoration(dragSortRecycler);
        recyclerView.addOnItemTouchListener(dragSortRecycler);
        recyclerView.setOnScrollListener(dragSortRecycler.getScrollListener());
    }



    public static class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>
    {
        private Context context;

        // The items to display in your RecyclerView
        private ArrayList<Integer> items;

        public static class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView text;
            ImageView iv;
            public ViewHolder(View itemView)
            {
                super(itemView);

                text = (TextView) itemView.findViewById(R.id.counter_textView);


                iv = (ImageView)itemView.findViewById(R.id.imageView);

            }
        }

        public CustomAdapter(ArrayList<Integer> items, Context context)
        {
            this.items = items;
            this.context = context;

        }

        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {

            Integer origPos = items.get(position);
            holder.text.setText("Item :" + origPos);

            //Make every 5th one bigger
            float scale = holder.iv.getResources().getDisplayMetrics().density;
            int pixels;

            if ((origPos % 5) == 0) {
                pixels = (int) (100 * scale + 0.5f);
            }
            else
            {
                pixels = (int) (50* scale + 0.5f);
            }
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.iv.getLayoutParams();
            params.height = (int) pixels;
            holder.iv.setLayoutParams(params);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

    }
}
