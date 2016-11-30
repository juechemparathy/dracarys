package com.yahoo.dracarys.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yahoo.dracarys.R;
import com.yahoo.dracarys.activities.AddActivity;
import com.yahoo.dracarys.activities.LoginActivity;
import com.yahoo.dracarys.activities.MainActivity;
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
            title.setTextColor(Color.BLACK);
            icon.setOnClickListener(this);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getPosition();
            //Handle this more elegantly
            if (position == 0) {

            }else if (position == 1) {
                //Search
                context.startActivity(new Intent(context, SearchActivity.class));
            }else if (position == 2) {
                //Public Stream
                context.startActivity(new Intent(context, MainActivity.class));
            }else if (position == 3) {
                //Private Stream
                context.startActivity(new Intent(context, MainActivity.class));
            }else if (position == 4) {
                //Favorites
                context.startActivity(new Intent(context, MainActivity.class));
            }else if (position == 5) {
                //My Profile
                context.startActivity(new Intent(context, MainActivity.class));
            }else if (position == 6) {
                //Add ISBN
                context.startActivity(new Intent(context, AddActivity.class));
            }else if (position == 7) {
                //Scan ISBN
                context.startActivity(new Intent(context, AddActivity.class));
            }else if(position ==8){
//                ParseUser.logOut();
                Intent i = new Intent(context, LoginActivity.class);
                 //Was trying to remove the call stack on logout.- Ended up back button with ablack screen.
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            }
            else {
                Toast.makeText(context, "Damn, YBook!" + position, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
