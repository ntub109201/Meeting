package com.example.myapplication2.ui.maybelike.adpter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.myapplication2.ui.maybelike.GoogleAPIResponseDataInterface;
import com.example.myapplication2.R;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

public class ListViewAdapter extends BaseSwipeAdapter {
    private FragmentActivity fragmentActivity;
    //private GoogleAPIResponse googleAPIResponse = null;
    private GoogleAPIResponseDataInterface iface= null;
    public ListViewAdapter(FragmentActivity fragmentActivity, GoogleAPIResponseDataInterface googleAPIResponseDataInterface) {
        this.fragmentActivity = fragmentActivity;
        this.iface = googleAPIResponseDataInterface;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        View v = LayoutInflater.from(fragmentActivity).inflate(R.layout.listview_item, null);
        SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                //YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
                // do some animate stuff
            }
        });
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(fragmentActivity, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        v.findViewById(R.id.map_detail_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a;
                double b;
                a = iface.getLat(position);
                b = iface.getLng(position);
                //Toast.makeText(mContext, "click detail"+String.valueOf(position)+": "+a+ ", "+b, Toast.LENGTH_SHORT).show();
                Uri gmmIntentUri = Uri.parse("geo:"+a+","+b+"?z=20");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                fragmentActivity.startActivity(mapIntent);
            }
        });
        v.findViewById(R.id.map_navigation_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a;
                double b;
                a = iface.getLat(position);
                b = iface.getLng(position);
                //Toast.makeText(mContext, "click navigation"+String.valueOf(position), Toast.LENGTH_SHORT).show();
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+a+","+b+"&mode=w&avoid=hf");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                fragmentActivity.startActivity(mapIntent);
            }
        });
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void fillValues(int position, View convertView) {
        TextView place_name = convertView.findViewById(R.id.store_name_textView);
        ImageView place_image = convertView.findViewById(R.id.store_image);
        TextView place_distance = convertView.findViewById(R.id.store_distance_textView);
        if (this.iface != null){
            place_name.setText(this.iface.getPlaceName(position));
            place_image.setImageBitmap(this.iface.getPlacePhoto(position));
            String text = this.iface.getPlaceDistance(position) + "m";
            place_distance.setText(text);
        }
    }

    @Override
    public int getCount() {
        return this.iface.getItemsCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
