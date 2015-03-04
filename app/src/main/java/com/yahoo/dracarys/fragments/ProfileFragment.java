package com.yahoo.dracarys.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.yahoo.dracarys.R;
import com.yahoo.dracarys.activities.CameraActivity;
import com.yahoo.dracarys.adapters.ProfileLineItemAdapter;
import com.yahoo.dracarys.helpers.VolleySingleton;
import com.yahoo.dracarys.interfaces.OnFragmentInteractionListener;
import com.yahoo.dracarys.models.BookLineItem;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment implements OnFragmentInteractionListener {
    private static final String POSITION = "position";
    private final int REQUEST_CODE = 20;

    // TODO: Rename and change types of parameters
    private int position;
    private TextView tvPagePosition;
    private OnFragmentInteractionListener mListener;

    private ImageView ivProfileImage;
    private TextView tvName;
    private TextView tvTagLine;
    private TextView tvFollowers;
    private TextView tvFollowing;
    private TextView tvLendRequests;
    private String userScreenName = ""; // 0 means self
    ImageLoader imageLoader;
    VolleySingleton volleySingleton;
    String imageUrl;

    private RecyclerView recyclerView;
    private ProfileLineItemAdapter lineItemAdapter;
    List<BookLineItem> bookLineItems;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance(int position) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
        }

        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();

    }


    private void loadProfileInfo() {
        //Fetch data from  parse
        //1.User main  info
        //2.User timeline info
        final ParseUser user = ParseUser.getCurrentUser();
        tvName.setText(user.getUsername());
        tvTagLine.setText((String) user.get("tagline"));

        //find an image
//        ivProfileImage.setImageBitmap();
        //

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Locker");
        query.whereEqualTo("userObjId", user.getObjectId());
        query.include("userPointer");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> lockerList, ParseException e) {
                if (e == null) {
                    Log.d("LOCKER", "Retrieved " + lockerList.size() + " items");
                    if (lockerList != null && lockerList.size() > 0) {
                        imageUrl = lockerList.get(0).getString("smallimageurl");
                    } else {

                    }
                }
            }
        });

        if (imageUrl != null) {
            imageLoader.get(imageUrl, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    ivProfileImage.setImageBitmap(response.getBitmap());

                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    //fall back to default image
                }
            });
        } else {
            ivProfileImage.setImageResource(R.drawable.book_profile);
        }


        query = ParseQuery.getQuery("Follower");
        query.whereEqualTo("follower", ParseUser.getCurrentUser());
        query.whereEqualTo("status", 1);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> followerList, ParseException e) {
                if (e == null) {
                    tvFollowers.setText(followerList.size() + " followers ");
                } else {
                    Log.d("LOCKER", "Error: " + e.getMessage());
                }
            }
        });

        query = ParseQuery.getQuery("Follower");
        query.whereEqualTo("following", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> followingList, ParseException e) {
                if (e == null) {
                    tvFollowing.setText(followingList.size() + " following");
                } else {
                    Log.d("LOCKER", "Error: " + e.getMessage());
                }
            }
        });

        query = ParseQuery.getQuery("Lend");
        query.whereEqualTo("lenderPointer", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> followingList, ParseException e) {
                if (e == null) {
                    tvLendRequests.setText(followingList.size() + " active loans");
                } else {
                    Log.d("LOCKER", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_profile, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            tvPagePosition.setText("Page " + bundle.getInt("position"));
        }

        ivProfileImage = (ImageView) layout.findViewById(R.id.ivProfileImage);
        ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Click your picture!",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), CameraActivity.class);
                //i.putExtra("mode", 2); // pass arbitrary data to launched activity
                startActivity(i);
            }
        });

        tvName = (TextView) layout.findViewById(R.id.tvName);
        tvTagLine = (TextView) layout.findViewById(R.id.tvTagLine);
        tvFollowers = (TextView) layout.findViewById(R.id.tvFollowers);
        tvFollowing = (TextView) layout.findViewById(R.id.tvFollowing);
        tvLendRequests = (TextView) layout.findViewById(R.id.tvLendRequests);
        loadProfileInfo();

        if (bookLineItems == null) {
            bookLineItems = new ArrayList<BookLineItem>();
            final ParseUser user = ParseUser.getCurrentUser();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Locker");
            query.whereEqualTo("userPointer", user);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> lockerList, ParseException e) {
                    if (e == null) {
                        Log.d("LOCKER", "Retrieved " + lockerList.size() + " items");
                        for (ParseObject parseObject : lockerList) {
                            BookLineItem bookLineItem = new BookLineItem();
                            bookLineItem.setAuthor(parseObject.getString("author"));
                            bookLineItem.setImageUrl(parseObject.getString("smallimageurl"));
                            bookLineItem.setTitle(parseObject.getString("title"));
                            bookLineItem.setUsername(parseObject.getParseUser("userPointer").getUsername());
                            bookLineItem.setAge(parseObject.getString("createdAt"));
                            bookLineItem.setEan(parseObject.getString("ean"));
                            bookLineItem.setParseBookObject(parseObject);
                            bookLineItems.add(bookLineItem);
                            lineItemAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Log.d("LOCKER", "Error: " + e.getMessage());
                    }
                }
            });
        }

        recyclerView = (RecyclerView) layout.findViewById(R.id.usertimeline_rcview);
        lineItemAdapter = new ProfileLineItemAdapter(getActivity(), bookLineItems);
        recyclerView.setAdapter(lineItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return layout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
