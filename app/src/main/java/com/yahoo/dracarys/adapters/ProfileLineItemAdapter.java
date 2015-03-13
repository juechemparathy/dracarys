package com.yahoo.dracarys.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.parse.ParseObject;
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
    AlertDialog.Builder builder;


    public ProfileLineItemAdapter(Context context, List<BookLineItem> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
        builder = new AlertDialog.Builder(context);

    }

    public void setBookLineItemList(List<BookLineItem> data) {
        this.data = data;
        notifyItemRangeChanged(0, data.size());
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
        if (current.getImageUrl() != null) {
            imageLoader.get(current.getImageUrl(), new ImageLoader.ImageListener() {
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
        if(current.getState()==0){
            holder.lend.setImageResource(R.drawable.book_private);
        }else{
            holder.lend.setImageResource(R.drawable.book_public);
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
//                @Override
//                public void onClick(View view) {
////                    LendActionFragment fragment = new LendActionFragment();
////                    fragment.setBookLineItem(data.get(getPosition()));
////                    FragmentManager fm = ((Activity)context).getFragmentManager();
////                    fragment.show(fm,"Lend Action");
//
//                    //Update book status - state and displaystate
//
//
//                }

                @Override
                public void onClick(View view) {
                    final BookLineItem bookLineItem = data.get(getPosition());
                    final ParseObject lockerObj = bookLineItem.getParseBookObject();
                    builder.setTitle("Update status");
                    builder.setMessage(bookLineItem.getTitle());
                    builder.setCancelable(true)
                            .setPositiveButton("Private", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    lockerObj.put("state", 0);
                                    lockerObj.saveInBackground();
                                }
                            })
                            .setNegativeButton("Public", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    lockerObj.put("state", 1);
                                    lockerObj.saveInBackground();
                                }
                            })
                            .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }

        @Override
        public void onClick(View view) {
            int position = getPosition();
            if (view == icon) {
               // Toast.makeText(context, "Position - " + position, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
