package com.example.sinh.whateats.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sinh.whateats.R;
import com.example.sinh.whateats.models.foursquare.Venue;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Venue} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FoursquareResultRecyclerViewAdapter extends RecyclerView.Adapter<FoursquareResultRecyclerViewAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Venue> mVenueList;
    private final OnListFragmentInteractionListener mListener;

    public FoursquareResultRecyclerViewAdapter(Context mContext, List<Venue> items, OnListFragmentInteractionListener listener) {
        this.mContext = mContext;
        this.mVenueList = items;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_foursquare_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mVenueList.get(position);
        String icon = mVenueList.get(position).getCategories().get(0).getIcon().getPrefix()
                + "64" + mVenueList.get(position).getCategories().get(0).getIcon().getSuffix();
        Glide.with(mContext)
                .load(icon)
                .into(holder.mIcon);
        holder.mName.setText(mVenueList.get(position).getName());
        holder.mAddress.setText(mVenueList.get(position).getLocation().getAddress());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onVenueListItemClicked(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVenueList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIcon;
        public final TextView mName;
        public final TextView mAddress;
        public Venue mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIcon = (ImageView) view.findViewById(R.id.icon);
            mName = (TextView) view.findViewById(R.id.name);
            mAddress = (TextView) view.findViewById(R.id.address);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mAddress.getText() + "'";
        }
    }
}
