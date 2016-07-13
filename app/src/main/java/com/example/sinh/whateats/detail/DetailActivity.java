package com.example.sinh.whateats.detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.example.sinh.whateats.R;
import com.example.sinh.whateats.maps.MapsActivity;
import com.example.sinh.whateats.models.foursquare.Timeframe;
import com.example.sinh.whateats.models.foursquare.Venue;
import com.example.sinh.whateats.models.foursquare.Item;
import com.example.sinh.whateats.models.foursquare.PhotosResponse;
import com.example.sinh.whateats.models.foursquare.VenueResponse;
import com.example.sinh.whateats.models.googleplace.AddressComponent;
import com.example.sinh.whateats.models.googleplace.Photo;
import com.example.sinh.whateats.models.googleplace.PlaceResponse;
import com.example.sinh.whateats.models.googleplace.PlaceResult;
import com.example.sinh.whateats.models.googleplace.Result;
import com.example.sinh.whateats.network.FoursquareApi;
import com.example.sinh.whateats.network.FoursquareServiceGenerator;
import com.example.sinh.whateats.network.GooglePlaceApi;
import com.example.sinh.whateats.network.GooglePlaceServiceGenerator;
import com.example.sinh.whateats.search.SearchActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private ImagePagerAdapter mImagePagerAdapter;

    /**
     * The {@link ViewPager} that will host the image list.
     */
    private ViewPager mViewPager;

    private List<Fragment> mImageFragmentList = new ArrayList<>();
    private GooglePlaceApi googlePlaceApi;
    private FoursquareApi foursquareApi;

    private TextView textViewPlaceName;
    private TextView textViewReviews;
    private TextView textViewPhotos;
    private LinearLayout checkInCountContainer;
    private LinearLayout visitCountContainer;
    private TextView textViewCheckIn;
    private TextView textViewVisit;
    private ImageView imageViewRating;
    private TextView textViewStatus;
    private TextView textViewOpeningHours;
    private LinearLayout buttonCall;
    private ImageView imageViewStaticMap;
    private TextView textViewFormattedAddress;
    private TextView textViewFormattedPhone;

    private Venue venue;
    private Result result;
    private List<Venue> venueList;
    private List<Result> resultList;

    /**
     * Permission
     */
    private static final String[] CALL_PERM = {
            Manifest.permission.CALL_PHONE,
    };
    private static final int CALL_PHONE_REQUEST_CODE = 1338;

    private void displayDetail(Result result) {
        if (result == null) {
            return;
        }

        googlePlaceApi = GooglePlaceServiceGenerator.createService(GooglePlaceApi.class);
        Call<PlaceResponse> placeResponseCall = googlePlaceApi.getPlaceDetail(result.getPlaceId());
        placeResponseCall.enqueue(new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                PlaceResult placeResult = response.body().getResult();

                if (placeResult.getPhotos().isEmpty()) {
                    mImageFragmentList.add(ImageFragment.newInstance(null));
                    mImagePagerAdapter.notifyDataSetChanged();
                } else {
                    for (Photo photo : placeResult.getPhotos()) {
                        Call<ResponseBody> responseBodyCall = googlePlaceApi.getPhoto(photo.getPhotoReference(),
                                photo.getHeight(), photo.getWidth());
                        responseBodyCall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> responseBodyCall, Response<ResponseBody> response) {
                                String url = response.raw().request().url().toString();
                                Log.d("GET_PHOTO", url);
                                mImageFragmentList.add(ImageFragment.newInstance(url));
                                mImagePagerAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e("GET_PHOTO", "Some error when get google place photos");
                            }
                        });
                    }
                }

                String streetName = null;
                for (AddressComponent a : placeResult.getAddressComponents()) {
                    if (a.getTypes().contains("route")) {
                        streetName = a.getShortName();
                        break;
                    } else if (a.getTypes().contains("administrative_area_level_2")) {
                        streetName = a.getShortName();
                    }
                }
                getSupportActionBar().setTitle(placeResult.getName());
                if (streetName == null) {
                    textViewPlaceName.setText(placeResult.getName());
                } else {
                    textViewPlaceName.setText(placeResult.getName() + " - " + streetName);
                }
                textViewReviews.setText(String.valueOf(placeResult.getReviews().size()));
                textViewPhotos.setText(String.valueOf(placeResult.getPhotos().size()));
                String ratings = placeResult.getRating() == null ? "0" : String.valueOf(placeResult.getRating());
                TextDrawable drawable = TextDrawable.builder()
                        .beginConfig()
                        .withBorder(1) /* thickness in px */
                        .fontSize(50) /* size in px */
                        .bold()
                        .endConfig()
                        .buildRound(ratings, Color.parseColor("#35B833"));
                imageViewRating.setImageDrawable(drawable);
                if (placeResult.getOpeningHours() != null) {
                    String status = placeResult.getOpeningHours().getOpenNow() ? "OPENING" : "CLOSING";
                    textViewStatus.setText(status);
                    if (!placeResult.getOpeningHours().getWeekdayText().isEmpty()) {
                        String openingHours = "";
                        for (String s : placeResult.getOpeningHours().getWeekdayText()) {
                            openingHours += s + "\n";
                        }
                        textViewOpeningHours.setText(openingHours);
                    }
                }
                String staticMapUrl = "https://maps.googleapis.com/maps/api/staticmap?"
                        + "scale=2&size=640x320&zoom=18&markers=color:red|label:C|"
                        + placeResult.getGeometry().getLocation().getLat() + ","
                        + placeResult.getGeometry().getLocation().getLng();
                Glide.with(DetailActivity.this)
                        .load(staticMapUrl)
                        .into(imageViewStaticMap);
                if (placeResult.getFormattedAddress() != null) {
                    textViewFormattedAddress.setText(placeResult.getFormattedAddress());
                }
                if (placeResult.getFormattedPhoneNumber() != null) {
                    textViewFormattedPhone.setText(placeResult.getFormattedPhoneNumber());
                }
            }

            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {

            }
        });
    }

    private void displayDetail(final Venue venue) {
        if (venue == null) {
            return;
        }
        foursquareApi = FoursquareServiceGenerator.createService(FoursquareApi.class);
        Call<PhotosResponse> photosResponseCall = foursquareApi.getPhotos(venue.getId());
        photosResponseCall.enqueue(new Callback<PhotosResponse>() {
            @Override
            public void onResponse(Call<PhotosResponse> call, Response<PhotosResponse> response) {
                List<Item> itemList = response.body().getResponse().getPhotos().getItems();
                Log.d("GET_PHOTO", String.valueOf(itemList.size()));
                textViewPhotos.setText(String.valueOf(itemList.size()));
                if (itemList.isEmpty()) {
                    mImageFragmentList.add(ImageFragment.newInstance(null));
                } else {
                    for (Item i : itemList) {
                        String url = i.getPrefix() + "original" + i.getSuffix();
                        mImageFragmentList.add(ImageFragment.newInstance(url));
                    }
                }
                mImagePagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PhotosResponse> call, Throwable t) {
                Log.e("GET_PHOTO", "Some error when get foursquare photos");
            }
        });

        Call<VenueResponse> venueResponseCall = foursquareApi.getVenue(venue.getId());
        venueResponseCall.enqueue(new Callback<VenueResponse>() {
            @Override
            public void onResponse(Call<VenueResponse> call, Response<VenueResponse> response) {
                Venue venueResult = response.body().getResponse().getVenue();
                getSupportActionBar().setTitle(venueResult.getName());
                textViewPlaceName.setText(venueResult.getName());
                if (venueResult.getStats().getTipCount() != null) {
                    textViewReviews.setText(String.valueOf(venueResult.getStats().getTipCount()));
                }
                if (venueResult.getStats().getCheckinsCount() != null) {
                    checkInCountContainer.setVisibility(View.VISIBLE);
                    textViewCheckIn.setText(String.valueOf(venueResult.getStats().getCheckinsCount()));
                }
                if (venueResult.getStats().getVisitsCount() != null) {
                    visitCountContainer.setVisibility(View.VISIBLE);
                    textViewVisit.setText(String.valueOf(venueResult.getStats().getVisitsCount()));
                }
                String ratings = venueResult.getRating() == null ? "0" : String.valueOf(venueResult.getRating());
                TextDrawable drawable = TextDrawable.builder()
                        .beginConfig()
                        .withBorder(1) /* thickness in px */
                        .fontSize(50) /* size in px */
                        .bold()
                        .endConfig()
                        .buildRound(ratings, Color.parseColor("#35B833"));
                imageViewRating.setImageDrawable(drawable);
                if (venueResult.getPopular() != null) {
                    String status = venueResult.getPopular().getIsOpen() ? "OPENING" : "CLOSING";
                    textViewStatus.setText(status);
                    String openingHours = "";
                    for (Timeframe tf : venueResult.getPopular().getTimeframes()) {
                        if (tf.getIncludesToday() == null) {
                            openingHours += tf.toString() + "\n";
                        }
                    }
                    textViewOpeningHours.setText(openingHours);
                }
                String staticMapUrl = "https://maps.googleapis.com/maps/api/staticmap?"
                        + "scale=2&size=640x320&zoom=18&markers=color:red|label:C|"
                        + venueResult.getLocation().getLat() + ","
                        + venueResult.getLocation().getLng();
                Glide.with(DetailActivity.this)
                        .load(staticMapUrl)
                        .into(imageViewStaticMap);
                if (!venueResult.getLocation().getFormattedAddress().isEmpty()) {
                    textViewFormattedAddress.setText(venueResult.getLocation().getFormattedAddress().toString()
                            .replace("[", "")
                            .replace("]", ""));
                }
                if (venueResult.getContact().getFormattedPhone() != null) {
                    textViewFormattedPhone.setText(venueResult.getContact().getFormattedPhone());
                }
            }

            @Override
            public void onFailure(Call<VenueResponse> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        result = intent.getExtras().getParcelable(SearchActivity.RESULT_ITEM);
        displayDetail(result);

        venue = intent.getExtras().getParcelable(SearchActivity.VENUE_ITEM);
        displayDetail(venue);

        venueList = intent.getParcelableArrayListExtra(SearchActivity.VENUE_LIST);
        resultList = intent.getParcelableArrayListExtra(SearchActivity.RESULT_LIST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the image fragment
        mImagePagerAdapter = new ImagePagerAdapter(getSupportFragmentManager(), mImageFragmentList);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.image_container);
        mViewPager.setAdapter(mImagePagerAdapter);

        // Create & set up place detail
        textViewPlaceName = (TextView) findViewById(R.id.name);
        textViewReviews = (TextView) findViewById(R.id.reviews);
        textViewPhotos = (TextView) findViewById(R.id.photos);
        imageViewRating = (ImageView) findViewById(R.id.rating);
        textViewStatus = (TextView) findViewById(R.id.status);
        textViewOpeningHours = (TextView) findViewById(R.id.opening_hours);
        buttonCall = (LinearLayout) findViewById(R.id.button_call);
        imageViewStaticMap = (ImageView) findViewById(R.id.static_map);
        textViewFormattedAddress = (TextView) findViewById(R.id.formatted_address);
        textViewFormattedPhone = (TextView) findViewById(R.id.formatted_phone);
        textViewCheckIn = (TextView) findViewById(R.id.check_in);
        checkInCountContainer = (LinearLayout) findViewById(R.id.check_in_count_container);
        textViewVisit = (TextView) findViewById(R.id.visit);
        visitCountContainer = (LinearLayout) findViewById(R.id.visit_count_container);

        result = getIntent().getExtras().getParcelable(SearchActivity.RESULT_ITEM);
        displayDetail(result);

        venue = getIntent().getExtras().getParcelable(SearchActivity.VENUE_ITEM);
        displayDetail(venue);

        venueList = getIntent().getParcelableArrayListExtra(SearchActivity.VENUE_LIST);
        resultList = getIntent().getParcelableArrayListExtra(SearchActivity.RESULT_LIST);

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!textViewFormattedPhone.getText().equals("XXX-XXX-XXXX")) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + textViewFormattedPhone.getText()));
                    if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        ActivityCompat.requestPermissions(DetailActivity.this, CALL_PERM, CALL_PHONE_REQUEST_CODE);
                        return;
                    }
                    startActivity(callIntent);
                }else {
                    Toast.makeText(DetailActivity.this, "Phone number not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageViewStaticMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailActivity.this, MapsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                i.putExtra(SearchActivity.RESULT_ITEM, result);
                i.putExtra(SearchActivity.VENUE_ITEM, venue);
                i.putParcelableArrayListExtra(SearchActivity.VENUE_LIST, (ArrayList<? extends Parcelable>) venueList);
                i.putParcelableArrayListExtra(SearchActivity.RESULT_LIST, (ArrayList<? extends Parcelable>) resultList);
                startActivity(i);
            }
        });

        // Set up action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CALL_PHONE_REQUEST_CODE) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + textViewFormattedPhone.getText()));
                startActivity(callIntent);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A {@link FragmentStatePagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class ImagePagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mFragmentList;

        public ImagePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.mFragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
