package com.yahoo.dracarys.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.yahoo.dracarys.R;
import com.yahoo.dracarys.helpers.VolleySingleton;
import com.yahoo.dracarys.models.BookLineItem;

import java.util.List;

/**
 * Created by jue on 1/31/15.
 */
public class ProfileLineItemAdapter extends RecyclerView.Adapter<ProfileLineItemAdapter.LineItemViewHolder> {


    private LayoutInflater layoutInflater;
    private List<BookLineItem> data;
    private Context context;
    VolleySingleton volleySingleton;
    ImageLoader imageLoader;

    public ProfileLineItemAdapter(Context context, List<BookLineItem> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    public void setBookLineItemList(List<BookLineItem> data){
        this.data=data;
        notifyItemRangeChanged(0,data.size());
    }

    @Override
    public LineItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View lineItemView = layoutInflater.inflate(R.layout.book_profile_lineitem, parent, false);
        LineItemViewHolder viewHolder = new LineItemViewHolder(lineItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final LineItemViewHolder holder, int position) {
        BookLineItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());
        holder.username.setText(current.getUsername());
        holder.author.setText(current.getAuthor());
        holder.age.setText(current.getAge());
        if(current.getImageUrl()!=null){
            imageLoader.get(current.getImageUrl(),new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.icon.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    //fall back to default image
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    //ViewHolder for this adapter
    class LineItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;
        TextView username;
        TextView author;
        TextView age;
        ImageView lend;

        public LineItemViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            username = (TextView) itemView.findViewById(R.id.tv_username);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            age = (TextView) itemView.findViewById(R.id.tv_age);
            icon = (ImageView) itemView.findViewById(R.id.iv_drawer);
            lend = (ImageView) itemView.findViewById(R.id.iv_lend_accept_reject);


            icon.setOnClickListener(this);
            title.setOnClickListener(this);
            username.setOnClickListener(this);
            lend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Updated loan status.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View view) {
            int position = getPosition();
            if(view == icon){
                Toast.makeText(context, "Position - " + position, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
