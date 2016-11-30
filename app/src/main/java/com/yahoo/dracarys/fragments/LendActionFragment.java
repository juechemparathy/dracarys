package com.yahoo.dracarys.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.yahoo.dracarys.R;
import com.yahoo.dracarys.models.BookLineItem;

/**
 * Created by jue on 3/2/15.
 */
public class LendActionFragment extends DialogFragment{

    private BookLineItem bookLineItem;

    public BookLineItem getBookLineItem() {
        return bookLineItem;
    }

    public void setBookLineItem(BookLineItem bookLineItem) {
        this.bookLineItem = bookLineItem;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_lendaction, null);
        builder.setView(view);
        builder.setTitle("Share your book!");
        builder
                .setMessage(getBookLineItem().getTitle() + " to "+getBookLineItem().getUsername())
                .setCancelable(false);
//                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        final ParseUser lenderPointer = ParseUser.getCurrentUser();
//                        final BookLineItem bookLineItem = getBookLineItem();
//                        final ParseObject lendeePointer = bookLineItem.getParseBookObject().getParseUser("userPointer");
//                        final ParseObject lockerPointer = bookLineItem.getParseBookObject();
//                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lend");
//                        query.whereEqualTo("lenderPointer", lenderPointer);
//                        query.whereEqualTo("lendeePointer", lendeePointer);
//                        query.whereEqualTo("lockerPointer", lockerPointer);
//                        query.findInBackground(new FindCallback<ParseObject>() {
//                            public void done(List<ParseObject> dataList, ParseException e) {
//
//                                if (e != null) {
//                                    Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();
//                                }
//                                if (dataList!=null && dataList.size()>0) {
//                                    ParseObject parseObject = dataList.get(0);
//
//                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Lend");
//                                    query.getInBackground(parseObject.getObjectId(), new GetCallback<ParseObject>() {
//                                        public void done(ParseObject lendObj, ParseException e) {
//                                            if (e == null) {
//                                                // Now let's update it with some new data. In this case, only cheatMode and score
//                                                // will get sent to the Parse Cloud. playerName hasn't changed.
//                                                lendObj.put("lendeePointer", lendeePointer);
//                                                lendObj.put("lenderPointer", lenderPointer);
//                                                lendObj.put("lockerPointer", lockerPointer);
//                                                lendObj.put("status", LockerEnum.LendState.LEND_ACCEPTED.getStatus());
//                                                lendObj.saveInBackground();
//                                            }
//                                        }
//                                    });
////                                    Toast.makeText(getActivity(), "Loan Accepted.", Toast.LENGTH_SHORT).show();
//                                } else {
////                                    Toast.makeText(getActivity(), "Something went wrong accepting loan request", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                })
//                .setNegativeButton("Reject", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        //update backend
//                        //notify list view.
//                        final ParseUser lenderPointer = ParseUser.getCurrentUser();
//                        final BookLineItem bookLineItem = getBookLineItem();
//                        final ParseObject lendeePointer = bookLineItem.getParseBookObject().getParseUser("userPointer");
//                        final ParseObject lockerPointer = bookLineItem.getParseBookObject();
//                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lend");
//                        query.whereEqualTo("lenderPointer", lenderPointer);
//                        query.whereEqualTo("lendeePointer", lendeePointer);
//                        query.whereEqualTo("lockerPointer", lockerPointer);
//                        query.findInBackground(new FindCallback<ParseObject>() {
//                            public void done(List<ParseObject> dataList, ParseException e) {
//
//                                if (e != null) {
//                                    Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();
//                                }
//                                if (dataList!=null && dataList.size()>0) {
//                                    ParseObject parseObject = dataList.get(0);
//
//                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Lend");
//                                    query.getInBackground(parseObject.getObjectId(), new GetCallback<ParseObject>() {
//                                        public void done(ParseObject lendObj, ParseException e) {
//                                            if (e == null) {
//                                                // Now let's update it with some new data. In this case, only cheatMode and score
//                                                // will get sent to the Parse Cloud. playerName hasn't changed.
//                                                lendObj.put("lendeePointer", lendeePointer);
//                                                lendObj.put("lenderPointer", lenderPointer);
//                                                lendObj.put("lockerPointer", lockerPointer);
//                                                lendObj.put("status", LockerEnum.LendState.LEND_REJECTED.getStatus());
//                                                lendObj.saveInBackground();
//                                            }
//                                        }
//                                    });
////                                    Toast.makeText(getDialog().getContext(), "Loan Rejected.", Toast.LENGTH_SHORT).show();
//                                } else {
////                                    Toast.makeText(getActivity(), "Something went wrong rejecting loan request", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                });

        Dialog dialog = builder.create();
        return dialog;
    }

}
