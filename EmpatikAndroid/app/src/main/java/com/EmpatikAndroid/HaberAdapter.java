package com.EmpatikAndroid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Casper on 7.10.2015.
 */
public class HaberAdapter extends BaseAdapter {

    public LayoutInflater layoutInflater;
    public List<Haber> haberList;

    public HaberAdapter(Activity activity, List<Haber> haberList){
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.haberList = haberList;
    }

    @Override
    public int getCount() {
        return haberList.size();
    }

    @Override
    public Haber getItem(int position) {
        return haberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View satirView = layoutInflater.inflate(R.layout.solvent_newslist,null);
        TextView textView = (TextView) satirView.findViewById(R.id.news_baslik);
        ImageView imageView = (ImageView) satirView.findViewById(R.id.news_photo);

        textView.setText(haberList.get(position).getBaslik());
        switch (haberList.get(position).getResim()){
            case "one": imageView.setImageResource(R.drawable.one); break;
            case "two": imageView.setImageResource(R.drawable.two); break;
            case "three": imageView.setImageResource(R.drawable.three); break;
            case "four": imageView.setImageResource(R.drawable.four); break;
            case "five": imageView.setImageResource(R.drawable.five); break;
        }

        return satirView;
    }

}
