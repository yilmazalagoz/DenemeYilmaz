package com.EmpatikAndroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Casper on 13.9.2015.
 */
public class KategoriHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView categoryName;
    public ImageView categoryPhoto;
    private Context context;
    public WebView webView;

    public KategoriHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        itemView.setOnClickListener(this);
        categoryName = (TextView) itemView.findViewById(R.id.category_name);
        categoryPhoto = (ImageView) itemView.findViewById(R.id.category_photo);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Photo ID : " + getPosition(), Toast.LENGTH_SHORT).show();

        switch (getPosition()){
            case 0:
                MainActivity.addedListKategori.add(new KategoriObject("Yasam", R.drawable.one));
                break;
            case 1:
                MainActivity.addedListKategori.add(new KategoriObject("Finans", R.drawable.two));
                break;
            case 2:
                MainActivity.addedListKategori.add(new KategoriObject("Teknoloji", R.drawable.three));
                break;
            case 3:
                MainActivity.addedListKategori.add(new KategoriObject("Kültür-Sanat", R.drawable.four));
                break;
            case 4:
                MainActivity.addedListKategori.add(new KategoriObject("Eğitim", R.drawable.five));
                break;
        }

    }
}
