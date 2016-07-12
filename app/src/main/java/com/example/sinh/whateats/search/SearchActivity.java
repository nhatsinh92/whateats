package com.example.sinh.whateats.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.sinh.whateats.R;
import com.example.sinh.whateats.detail.DetailActivity;
import com.example.sinh.whateats.events.KeywordSubmitEvent;
import com.example.sinh.whateats.maps.MapsActivity;
import com.example.sinh.whateats.models.foursquare.Venue;
import com.example.sinh.whateats.models.googleplace.Result;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements OnListFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /**
     * The result {@link Fragment} for each {@link ViewPager} item
     */
    private FoursquareResultFragment foursquareResultFragment;
    private GooglePlaceResultFragment googlePlaceResultFragment;

    /**
     * The {@link SearchView}
     */
    private SearchView searchView;

    /**
     * The extra names that put to {@link Intent}
     */
    public final static String RESULT_ITEM = "com.example.sinh.whateats.search.RESULT_ITEM";
    public final static String VENUE_ITEM = "com.example.sinh.whateats.search.VENUE_ITEM";
    public final static String RESULT_LIST = "com.example.sinh.whateats.search.RESULT_LIST";
    public final static String VENUE_LIST = "com.example.sinh.whateats.search.VENUE_LIST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(SearchActivity.this, MapsActivity.class);
                List<Venue> venueList = foursquareResultFragment.venueList;
                intent.putParcelableArrayListExtra(VENUE_LIST, (ArrayList<? extends Parcelable>) venueList);
                List<Result> resultList = googlePlaceResultFragment.resultList;
                intent.putParcelableArrayListExtra(RESULT_LIST, (ArrayList<? extends Parcelable>) resultList);
                startActivity(intent);
            }
        });

    }


    private SearchView.OnQueryTextListener mQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            EventBus.getDefault().post(new KeywordSubmitEvent(query));
            searchView.clearFocus();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(mQueryTextListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResultListItemClicked(Result item) {
        Log.d("Item Clicked", item.getName());
        Intent i = new Intent(SearchActivity.this, DetailActivity.class);
        i.putExtra(RESULT_ITEM, item);
        List<Venue> venueList = foursquareResultFragment.venueList;
        i.putParcelableArrayListExtra(VENUE_LIST, (ArrayList<? extends Parcelable>) venueList);
        List<Result> resultList = googlePlaceResultFragment.resultList;
        i.putParcelableArrayListExtra(RESULT_LIST, (ArrayList<? extends Parcelable>) resultList);
        startActivity(i);
    }

    @Override
    public void onVenueListItemClicked(Venue item) {
        Log.d("Item Clicked", item.getName());
        Intent i = new Intent(SearchActivity.this, DetailActivity.class);
        i.putExtra(VENUE_ITEM, item);
        List<Venue> venueList = foursquareResultFragment.venueList;
        i.putParcelableArrayListExtra(VENUE_LIST, (ArrayList<? extends Parcelable>) venueList);
        List<Result> resultList = googlePlaceResultFragment.resultList;
        i.putParcelableArrayListExtra(RESULT_LIST, (ArrayList<? extends Parcelable>) resultList);
        startActivity(i);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0: {
                    googlePlaceResultFragment = GooglePlaceResultFragment.newInstance(1);
                    return googlePlaceResultFragment;
                }
                case 1: {
                    foursquareResultFragment = FoursquareResultFragment.newInstance(2);
                    return foursquareResultFragment;
                }

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "GOOGLE PLACE";
                case 1:
                    return "FOURSQUARE";
            }
            return null;
        }
    }
}
