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
import com.example.sinh.whateats.models.googleplace.Photo;
import com.example.sinh.whateats.models.googleplace.Result;
import com.example.sinh.whateats.network.GooglePlaceApi;
import com.example.sinh.whateats.network.GooglePlaceServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Result} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class GooglePlaceResultRecyclerViewAdapter extends RecyclerView.Adapter<GooglePlaceResultRecyclerViewAdapter.ViewHolder> {

    private final Context mContext;
    private List<Result> mResultList = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;

    public GooglePlaceResultRecyclerViewAdapter(Context context, List<Result> items, OnListFragmentInteractionListener listener) {
        this.mContext = context;
        this.mResultList = items;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_googleplace_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mResultList.get(position);
        holder.mName.setText(holder.mItem.getName());
        holder.mAddress.setText(holder.mItem.getVicinity());
        getPhotoAndSet(holder.mImage, holder.mItem);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onResultListItemClicked(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    private void getPhotoAndSet(final ImageView iv, Result r) {
        GooglePlaceApi googlePlaceApi = GooglePlaceServiceGenerator.createService(GooglePlaceApi.class);
        if (r.getPhotos() == null) {
            iv.setImageResource(R.drawable.ic_place_holder);
        }else if (r.getPhotos().isEmpty()) {
            iv.setImageResource(R.drawable.ic_place_holder);
        } else {
            Photo p = r.getPhotos().get(0);
            Call<ResponseBody> responseBodyCall = googlePlaceApi.getPhoto(p.getPhotoReference(),
                    p.getHeight(), p.getWidth());
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> responseBodyCall, Response<ResponseBody> response) {
                    String url = response.raw().request().url().toString();
                    Glide.with(mContext)
                            .load(url)
                            .placeholder(R.drawable.ic_place_holder)
                            .error(R.drawable.ic_place_holder)
                            .crossFade()
                            .into(iv);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("GET_PHOTO", "Some error when get google place photos");
                    iv.setImageResource(R.drawable.ic_place_holder);
                }
            });
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImage;
        public final TextView mName;
        public final TextView mAddress;
        public Result mItem;

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
