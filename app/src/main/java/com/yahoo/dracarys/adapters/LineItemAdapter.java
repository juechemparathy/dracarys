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
import com.yahoo.dracarys.models.BookLineItem;

import java.util.List;

/**
 * Created by jue on 1/31/15.
 */
public class LineItemAdapter extends RecyclerView.Adapter<LineItemAdapter.LineItemViewHolder> {


    private LayoutInflater layoutInflater;
    private List<BookLineItem> data;
    private Context context;

    public LineItemAdapter(Context context, List<BookLineItem> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public LineItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View lineItemView = layoutInflater.inflate(R.layout.book_lineitem, parent, false);
        LineItemViewHolder viewHolder = new LineItemViewHolder(lineItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LineItemViewHolder holder, int position) {
        BookLineItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());
        holder.username.setText(current.getUsername());
        holder.author.setText(current.getAuthor());
        holder.age.setText(current.getAge());
        holder.star.setImageResource(current.getStar());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    //ViewHolder for this adapter
    class LineItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;
        ImageView star;
        ImageView addtomine;
        ImageView requestLoan;
        TextView username;
        TextView author;
        TextView age;

        public LineItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            username = (TextView) itemView.findViewById(R.id.tv_username);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            age = (TextView) itemView.findViewById(R.id.tv_age);
            icon = (ImageView) itemView.findViewById(R.id.iv_drawer);
            star = (ImageView) itemView.findViewById(R.id.iv_star);
            addtomine = (ImageView) itemView.findViewById(R.id.iv_addtomine);
            requestLoan = (ImageView) itemView.findViewById(R.id.iv_requestLoan);

            icon.setOnClickListener(this);
            title.setOnClickListener(this);
            username.setOnClickListener(this);
            author.setOnClickListener(this);
            age.setOnClickListener(this);
            star.setOnClickListener(this);
            addtomine.setOnClickListener(this);
            requestLoan.setOnClickListener(this);
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