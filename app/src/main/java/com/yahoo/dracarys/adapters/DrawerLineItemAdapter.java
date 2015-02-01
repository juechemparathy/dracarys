package com.yahoo.dracarys.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yahoo.dracarys.R;
import com.yahoo.dracarys.models.DrawerLineItem;

import java.util.List;

/**
 * Created by jue on 1/31/15.
 */
public class DrawerLineItemAdapter extends RecyclerView.Adapter<DrawerLineItemViewHolder>{


    private LayoutInflater layoutInflater;
    private List<DrawerLineItem> data;
    public DrawerLineItemAdapter(Context context,List<DrawerLineItem> data){
        this.layoutInflater = LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public DrawerLineItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View lineItemView = layoutInflater.inflate(R.layout.drawer_lineitem,parent,false);
        DrawerLineItemViewHolder viewHolder = new DrawerLineItemViewHolder(lineItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DrawerLineItemViewHolder holder, int position) {
        DrawerLineItem current = data.get(position);
        holder.title.setText(current.getTitle());
//        holder.icon.setImageResource(current.getIconId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
