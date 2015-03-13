package com.yahoo.dracarys.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.yahoo.dracarys.R;
import com.yahoo.dracarys.data.LockerEnum;
import com.yahoo.dracarys.helpers.VolleySingleton;
import com.yahoo.dracarys.models.BookLineItem;

import java.util.List;

/**
 * Created by jue on 1/31/15.
 */
public class FavoriteLineItemAdapter extends RecyclerView.Adapter<FavoriteLineItemAdapter.LineItemViewHolder> {


    private LayoutInflater layoutInflater;
    private List<BookLineItem> data;
    private Context context;
    VolleySingleton volleySingleton;
    ImageLoader imageLoader;
    AlertDialog.Builder builder;


    public FavoriteLineItemAdapter(Context context, List<BookLineItem> data) {
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

        ParseFile selfieFile = current.getParseBookObject().getParseUser("userPointer").getParseFile("selfie");
        Bitmap selfie = null;
        if (selfieFile != null) {
            try {
                selfie = BitmapFactory.decodeByteArray(selfieFile.getData(), 0, selfieFile.getData().length);
                if (selfie != null) {
                    holder.iv_follow.setImageBitmap(selfie);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
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
            addtomine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final BookLineItem bookLineItem = data.get(getPosition());
                    final ParseObject bookObject = bookLineItem.getParseBookObject();
                    builder.setTitle("Add to my library");
                    builder.setMessage(bookLineItem.getTitle())
                            .setCancelable(true)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Locker");
                                    query.whereEqualTo("ean", bookObject.get("ean"));
                                    query.whereEqualTo("userPointer", ParseUser.getCurrentUser());
                                    query.findInBackground(new FindCallback<ParseObject>() {
                                        public void done(List<ParseObject> dataList, ParseException e) {

                                            if (e != null) {
                                                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                                Log.d("score", "Retrieved " + dataList.size() + " scores");
                                            }
                                            if (dataList.size() == 0) {
                                                ParseObject lockerItem = new ParseObject("Locker");
                                                lockerItem.put("title", bookLineItem.getTitle());
                                                lockerItem.put("ean", bookLineItem.getEan());
                                                lockerItem.put("smallimageurl", bookLineItem.getImageUrl());
                                                lockerItem.put("author", bookLineItem.getAuthor());
                                                lockerItem.put("userPointer", ParseUser.getCurrentUser());
                                                lockerItem.put("state", 1);
                                                lockerItem.put("displaystate", 1);
                                                lockerItem.saveInBackground();

                                                //change the star
                                                notifyDataSetChanged();
                                                Toast.makeText(context, "Added to your locker.", Toast.LENGTH_SHORT).show();
                                                Log.d("Locker", "Retrieved " + dataList.size() + " scores");
                                            } else {
                                                Toast.makeText(context, "Already in your locker.", Toast.LENGTH_SHORT).show();
                                                Log.d("Locker", "Retrieved " + dataList.size() + " scores");
                                            }

                                        }
                                    });
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });

            requestLoan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ParseUser lendeePointer = ParseUser.getCurrentUser();
                    final BookLineItem bookLineItem = data.get(getPosition());
                    final ParseUser lenderPointer = bookLineItem.getParseBookObject().getParseUser("userPointer");
                    final ParseObject lockerPointer = bookLineItem.getParseBookObject();
                    builder.setTitle("Email " + lenderPointer.getUsername());
                    builder.setMessage("Request to loan the book - " + bookLineItem.getTitle())
                            .setCancelable(true)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Lend");
                                    query.whereEqualTo("lenderPointer", lenderPointer);
                                    query.whereEqualTo("lendeePointer", lendeePointer);
                                    query.whereEqualTo("lockerPointer", lockerPointer);
                                    query.findInBackground(new FindCallback<ParseObject>() {
                                        public void done(List<ParseObject> dataList, ParseException e) {

                                            if (e != null) {
                                                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                                Log.d("score", "Retrieved " + dataList.size() + " scores");
                                            }
                                            if (dataList.size() == 0) {
                                                ParseObject favoriteObject = new ParseObject("Lend");
                                                favoriteObject.put("lendeePointer", lendeePointer);
                                                favoriteObject.put("lenderPointer", lenderPointer);
                                                favoriteObject.put("lockerPointer", lockerPointer);
                                                favoriteObject.put("status", LockerEnum.LendState.LEND_REQUESTED.getStatus());
                                                favoriteObject.saveInBackground();
                                                notifyDataSetChanged();
                                                Toast.makeText(context, "Loan requested.", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(context, "Already requested loan.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });

            iv_follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ParseUser followerPointer = ParseUser.getCurrentUser();
                    final BookLineItem bookLineItem = data.get(getPosition());
                    final ParseUser userPointer = bookLineItem.getParseBookObject().getParseUser("userPointer");
                    builder.setTitle("Follow " + userPointer.getUsername());
                    builder.setCancelable(true)
                            .setPositiveButton("Follow", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Follower");
                                    query.whereEqualTo("follower", userPointer);
                                    query.whereEqualTo("following", followerPointer);
                                    query.findInBackground(new FindCallback<ParseObject>() {
                                        public void done(List<ParseObject> dataList, ParseException e) {

                                            if (e != null) {
                                                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                                Log.d("score", "Retrieved " + dataList.size() + " scores");
                                            }
                                            if (dataList.size() == 0) {
                                                ParseObject favoriteObject = new ParseObject("Follower");
                                                favoriteObject.put("follower", userPointer);
                                                favoriteObject.put("following", followerPointer);
                                                favoriteObject.put("status", "A");
                                                favoriteObject.saveInBackground();
                                                notifyDataSetChanged();
                                                Toast.makeText(context, "Following " + bookLineItem.getUsername() + " now.", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(context, "Already following " + bookLineItem.getUsername(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            })
                            .setNegativeButton("View Profile", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
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
                Toast.makeText(context, "Position - " + position, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
