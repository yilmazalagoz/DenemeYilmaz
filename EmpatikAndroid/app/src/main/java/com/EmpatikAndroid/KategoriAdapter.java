package com.EmpatikAndroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Casper on 13.9.2015.
 */
public class KategoriAdapter extends RecyclerView.Adapter<KategoriHolder> {

    private List<KategoriObject> kategoriList;
    private Context context;

    public KategoriAdapter(Context context, List<KategoriObject> kategoriList){
        this.context = context;
        this.kategoriList = kategoriList;
    }

    @Override
    public KategoriHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.solvent_list, null);
        KategoriHolder holder = new KategoriHolder(layoutView,context);
        return holder;
    }

    @Override
    public void onBindViewHolder(KategoriHolder holder, int position) {
        holder.categoryName.setText(kategoriList.get(position).getName());
        holder.categoryPhoto.setImageResource(kategoriList.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return this.kategoriList.size();
    }
}
