package com.yahoo.dracarys.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yahoo.dracarys.R;
import com.yahoo.dracarys.adapters.FavoriteLineItemAdapter;
import com.yahoo.dracarys.interfaces.OnFragmentInteractionListener;
import com.yahoo.dracarys.models.BookLineItem;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment implements OnFragmentInteractionListener {
    private static final String POSITION = "position";

    // TODO: Rename and change types of parameters
    private int position;
    private TextView tvPagePosition;
    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private FavoriteLineItemAdapter lineItemAdapter;
    List<BookLineItem> bookLineItems;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NotificationFragment.
     */
    public static FavoriteFragment newInstance(int position) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public FavoriteFragment() {
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
        View layout = inflater.inflate(R.layout.fragment_favorite, container, false);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            tvPagePosition.setText("Page " +bundle.getInt("position"));
        }

        if(bookLineItems==null) {
            bookLineItems = new ArrayList<BookLineItem>();
//            final ParseUser user = ParseUser.getCurrentUser();
//            ParseQuery<ParseObject> query = ParseQuery.getQuery("Favorites");
//            query.include("lockerPointer");
//            query.include("userPointer");
//            query.whereEqualTo("userPointer", user);
//            query.findInBackground(new FindCallback<ParseObject>() {
//                public void done(List<ParseObject> lockerList, ParseException e) {
//                    if (e == null) {
//                        Log.d("LOCKER", "Retrieved " + lockerList.size() + " items");
//                        for (ParseObject parseObject : lockerList) {
//                            ParseObject lockerObj = parseObject.getParseObject("lockerPointer");
//                            BookLineItem bookLineItem = new BookLineItem();
//                            bookLineItem.setAuthor(lockerObj.getString("author"));
//                            bookLineItem.setImageUrl(lockerObj.getString("smallimageurl"));
//                            bookLineItem.setTitle(lockerObj.getString("title"));
//                            bookLineItem.setUsername(parseObject.getParseUser("userPointer").getUsername());
//                            bookLineItem.setAge(lockerObj.getString("createdAt"));
//                            bookLineItem.setEan(lockerObj.getString("ean"));
//                            bookLineItem.setParseBookObject(parseObject);
//                            bookLineItems.add(bookLineItem);
//                        }
//                        lineItemAdapter.notifyDataSetChanged();
//                    } else {
//                        Log.d("LOCKER", "Error: " + e.getMessage());
//                    }
//                }
//            });
        }

        recyclerView = (RecyclerView) layout.findViewById(R.id.notification_timeline_rcview);
        lineItemAdapter = new FavoriteLineItemAdapter(getActivity(), bookLineItems);
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
