package com.example.sinh.whateats.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sinh.whateats.R;
import com.example.sinh.whateats.events.KeywordSubmitEvent;
import com.example.sinh.whateats.models.googleplace.GooglePlaceResponse;
import com.example.sinh.whateats.models.googleplace.Result;
import com.example.sinh.whateats.network.GooglePlaceApi;
import com.example.sinh.whateats.network.GooglePlaceServiceGenerator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class GooglePlaceResultFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private GooglePlaceApi googlePlaceApi;
    public List<Result> resultList = new ArrayList<>();
    private GooglePlaceResultRecyclerViewAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GooglePlaceResultFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GooglePlaceResultFragment newInstance(int columnCount) {
        GooglePlaceResultFragment fragment = new GooglePlaceResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_googleplace_result_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            mAdapter = new GooglePlaceResultRecyclerViewAdapter(this.getContext(), resultList, mListener);
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }

    @Subscribe
    public void onEvent(KeywordSubmitEvent event) {
        googlePlaceApi = GooglePlaceServiceGenerator.createService(GooglePlaceApi.class);
        Call<GooglePlaceResponse> call = googlePlaceApi.searchNearbyPlace(event.getmLocation(), event.getmQuery(), "distance");
        call.enqueue(new Callback<GooglePlaceResponse>() {
            @Override
            public void onResponse(Call<GooglePlaceResponse> call, Response<GooglePlaceResponse> response) {
                Log.d("Success" , String.valueOf(response.body().getResults().size()));
                resultList.clear();
                resultList.addAll(response.body().getResults());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GooglePlaceResponse> call, Throwable t) {
                Log.d("Failure", t.getMessage());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
