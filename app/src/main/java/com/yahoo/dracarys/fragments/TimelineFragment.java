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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yahoo.dracarys.R;
import com.yahoo.dracarys.adapters.LineItemAdapter;
import com.yahoo.dracarys.helpers.AmazonFetcher;
import com.yahoo.dracarys.models.BookLineItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimelineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimelineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimelineFragment extends Fragment {
    private static final String POSITION = "position";

    // TODO: Rename and change types of parameters
    private int position;
    private TextView tvPagePosition;
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private LineItemAdapter lineItemAdapter;
    private Map<String, String> timelineResult;
    List<BookLineItem> bookLineItems = new ArrayList<BookLineItem>();


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


        if(timelineResult==null) {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            String url = "http://theagiledirector.com/getRest_v3.php?isbn=9780133930153";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("DEBUG", "Calling AmazonFetcher");
                    timelineResult = AmazonFetcher.parseXMLInput(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequest);

        }


        if (timelineResult != null && timelineResult.size() > 0) {
            for (int i = 0; i < 20; i++) {
                BookLineItem bookLineItem = new BookLineItem();
                bookLineItem.setAuthor(timelineResult.get("author"));
                bookLineItem.setImageUrl(timelineResult.get("smallImageUrl"));
                bookLineItem.setTitle(timelineResult.get("title"));
                bookLineItem.setUsername("Jue Chemparathy");
                bookLineItem.setAge("3h");
                bookLineItems.add(bookLineItem);
            }
        }


        recyclerView = (RecyclerView) layout.findViewById(R.id.timeline_rcview);
        lineItemAdapter = new LineItemAdapter(getActivity(), bookLineItems);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
