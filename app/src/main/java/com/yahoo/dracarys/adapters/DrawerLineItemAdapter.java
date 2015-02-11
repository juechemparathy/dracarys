package com.yahoo.dracarys.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yahoo.dracarys.R;
import com.yahoo.dracarys.activities.AddActivity;
import com.yahoo.dracarys.activities.SearchActivity;
import com.yahoo.dracarys.models.DrawerLineItem;

import java.util.List;

/**
 * Created by jue on 1/31/15.
 */
public class DrawerLineItemAdapter extends RecyclerView.Adapter<DrawerLineItemAdapter.DrawerLineItemViewHolder> {


    private LayoutInflater layoutInflater;
    private List<DrawerLineItem> data;
    private Context context;

    public DrawerLineItemAdapter(Context context, List<DrawerLineItem> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public DrawerLineItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View lineItemView = layoutInflater.inflate(R.layout.drawer_lineitem, parent, false);
        DrawerLineItemViewHolder viewHolder = new DrawerLineItemViewHolder(lineItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DrawerLineItemViewHolder holder, int position) {
        DrawerLineItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    //ViewHolder for this adapter
    class DrawerLineItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        public DrawerLineItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_drawer);
            icon = (ImageView) itemView.findViewById(R.id.iv_drawer);
            icon.setOnClickListener(this);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getPosition();
            //Handle this more elegantly
            if (position == 0) {
                context.startActivity(new Intent(context, SearchActivity.class));
            }if (position == 1) {
                context.startActivity(new Intent(context, AddActivity.class));
            } else {
                Toast.makeText(context, "Clicked on position " + position, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
