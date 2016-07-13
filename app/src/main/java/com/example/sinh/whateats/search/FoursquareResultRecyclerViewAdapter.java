package com.example.sinh.whateats.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sinh.whateats.R;
import com.example.sinh.whateats.models.foursquare.Item;
import com.example.sinh.whateats.models.foursquare.PhotosResponse;
import com.example.sinh.whateats.models.foursquare.Venue;
import com.example.sinh.whateats.network.FoursquareApi;
import com.example.sinh.whateats.network.FoursquareServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Venue} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FoursquareResultRecyclerViewAdapter extends RecyclerView.Adapter<FoursquareResultRecyclerViewAdapter.ViewHolder> {

    private final Context mContext;
    private List<Venue> mVenueList = new ArrayList<>();
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
        holder.mName.setText(holder.mItem.getName());
        holder.mAddress.setText(holder.mItem.getLocation()
                .getFormattedAddress().toString()
                .replace("[","").replace("]",""));
        getFourSquarePhoto(holder.mImage, holder.mItem);
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

    private void getFourSquarePhoto(final ImageView iv, Venue v) {
        FoursquareApi foursquareApi = FoursquareServiceGenerator.createService(FoursquareApi.class);
        Call<PhotosResponse> photosResponseCall = foursquareApi.getPhotos(v.getId());
        photosResponseCall.enqueue(new Callback<PhotosResponse>() {
            @Override
            public void onResponse(Call<PhotosResponse> call, Response<PhotosResponse> response) {
                List<Item> itemList = response.body().getResponse().getPhotos().getItems();
                if (itemList.isEmpty()) {
                    iv.setImageResource(R.drawable.ic_place_holder_2);
                } else {
                    Item i = itemList.get(0);
                    String url= i.getPrefix()
                            + "original" + i.getSuffix();
                    Glide.with(mContext)
                            .load(url)
                            .placeholder(R.drawable.ic_place_holder_2)
                            .error(R.drawable.ic_place_holder_2)
                            .crossFade()
                            .into(iv);
                }
            }

            @Override
            public void onFailure(Call<PhotosResponse> call, Throwable t) {
                Log.e("GET_PHOTO", "Some error when get foursquare photos");
                iv.setImageResource(R.drawable.ic_place_holder_2);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImage;
        public final TextView mName;
        public final TextView mAddress;
        public Venue mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImage = (ImageView) view.findViewById(R.id.image);
            mName = (TextView) view.findViewById(R.id.name);
            mAddress = (TextView) view.findViewById(R.id.address);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mAddress.getText() + "'";
        }
    }
}
