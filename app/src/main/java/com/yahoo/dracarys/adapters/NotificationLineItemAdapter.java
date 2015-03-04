package com.yahoo.dracarys.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.yahoo.dracarys.R;
import com.yahoo.dracarys.helpers.VolleySingleton;
import com.yahoo.dracarys.models.BookLineItem;

import java.util.List;

/**
 * Created by jue on 1/31/15.
 */
public class NotificationLineItemAdapter extends RecyclerView.Adapter<NotificationLineItemAdapter.LineItemViewHolder> {


    private LayoutInflater layoutInflater;
    private List<BookLineItem> data;
    private Context context;
    VolleySingleton volleySingleton;
    ImageLoader imageLoader;

    public NotificationLineItemAdapter(Context context, List<BookLineItem> data) {
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
        View lineItemView = layoutInflater.inflate(R.layout.book_notification_lineitem, parent, false);
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

        ParseFile selfieFile  = current.getParseBookObject().getParseUser("userPointer").getParseFile("selfie");
        Bitmap selfie = null;
        if(selfieFile!=null) {
            try {
                selfie = BitmapFactory.decodeByteArray(selfieFile.getData(), 0, selfieFile.getData().length);
                if (selfie != null) {
                    holder.iv_follow.setImageBitmap(selfie);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            holder.iv_follow.setImageResource(R.drawable.book_profile);
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
        ImageView addtomine;
        ImageView requestLoan;
        TextView username;
        TextView author;
        TextView age;
        ImageView iv_follow;


        public LineItemViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            username = (TextView) itemView.findViewById(R.id.tv_username);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            age = (TextView) itemView.findViewById(R.id.tv_age);
            icon = (ImageView) itemView.findViewById(R.id.iv_drawer);
            addtomine = (ImageView) itemView.findViewById(R.id.iv_addtomine);
            requestLoan = (ImageView) itemView.findViewById(R.id.iv_requestLoan);
            iv_follow = (ImageView) itemView.findViewById(R.id.iv_follow);

            icon.setOnClickListener(this);
            title.setOnClickListener(this);
            username.setOnClickListener(this);
            addtomine.setOnClickListener(this);
            requestLoan.setOnClickListener(this);
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
