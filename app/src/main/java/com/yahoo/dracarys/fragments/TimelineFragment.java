package com.yahoo.dracarys.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.yahoo.dracarys.R;
import com.yahoo.dracarys.adapters.LineItemAdapter;
import com.yahoo.dracarys.interfaces.OnFragmentInteractionListener;
import com.yahoo.dracarys.models.BookLineItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TimelineFragment extends Fragment implements OnFragmentInteractionListener {
    private static final String POSITION = "position";

    // TODO: Rename and change types of parameters
    private int position;
    private TextView tvPagePosition;
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private LineItemAdapter lineItemAdapter;
    List<BookLineItem> bookLineItems;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TimelineFragment.
     */
    public static TimelineFragment newInstance(int position) {
        TimelineFragment fragment = new TimelineFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public TimelineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_timeline, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
//            tvPagePosition.setText("Page " +bundle.getInt("position"));
        }

        if(bookLineItems==null) {
            bookLineItems = new ArrayList<BookLineItem>();
            final ParseUser user = ParseUser.getCurrentUser();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Locker");
            query.orderByDescending("updatedAt");
            query.setLimit(100);
            query.whereEqualTo("state", 1);
            query.whereEqualTo("displaystate", 1);
            query.whereNotEqualTo("userPointer", user);
            query.include("userPointer");
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> lockerList, ParseException e) {
                    if (e == null) {
                        Log.d("LOCKER", "Retrieved " + lockerList.size() + " items");
                        for (ParseObject parseObject : lockerList) {
                            BookLineItem bookLineItem = new BookLineItem();
                            bookLineItem.setAuthor(parseObject.getString("author"));
                            bookLineItem.setImageUrl(parseObject.getString("smallimageurl"));
                            bookLineItem.setTitle(parseObject.getString("title"));
                            bookLineItem.setState(parseObject.getInt("state"));
                            bookLineItem.setDisplaystate(parseObject.getInt("displaystate"));
                            bookLineItem.setEan(parseObject.getString("ean"));
                            bookLineItem.setUsername(parseObject.getParseUser("userPointer").getUsername());
                            Date date =  parseObject.getCreatedAt();
                            bookLineItem.setAge(getAge(date));
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

        recyclerView = (RecyclerView) layout.findViewById(R.id.timeline_rcview);
        lineItemAdapter = new LineItemAdapter(getActivity(), bookLineItems);
        recyclerView.setAdapter(lineItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        lineItemAdapter.notifyDataSetChanged();
        return layout;
    }

    public String getAge(Date date) {
        //add logic for converting to expected format
        try {
            Date now = new Date();

            long diff = now.getTime() - date.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            if(diffDays >0){
                return Long.toString(diffDays) + "d";
            }
            if(diffHours >0 ){
                return Long.toString(diffHours)+ "h";
            }
            if(diffMinutes>0){
                return Long.toString(diffMinutes)+ "m";
            }
            if(diffSeconds>0){
                return Long.toString(diffMinutes)+ "s";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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
