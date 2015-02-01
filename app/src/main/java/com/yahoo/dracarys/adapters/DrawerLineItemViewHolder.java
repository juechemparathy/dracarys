package com.yahoo.dracarys.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yahoo.dracarys.R;

/**
 * Created by jue on 1/31/15.
 */
public class DrawerLineItemViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView icon;
    public DrawerLineItemViewHolder(View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.tv_drawer);
        icon=(ImageView)itemView.findViewById(R.id.iv_drawer);
    }
}
