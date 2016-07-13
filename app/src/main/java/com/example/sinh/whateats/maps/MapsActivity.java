package com.example.sinh.whateats.maps;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.sinh.whateats.R;
import com.example.sinh.whateats.detail.DetailActivity;
import com.example.sinh.whateats.models.direction.Bounds;
import com.example.sinh.whateats.models.direction.DirectionResponse;
import com.example.sinh.whateats.models.direction.Route;
import com.example.sinh.whateats.models.foursquare.Item;
import com.example.sinh.whateats.models.foursquare.PhotosResponse;
import com.example.sinh.whateats.models.foursquare.Venue;
import com.example.sinh.whateats.models.googleplace.Photo;
import com.example.sinh.whateats.models.googleplace.Result;
import com.example.sinh.whateats.network.FoursquareApi;
import com.example.sinh.whateats.network.FoursquareServiceGenerator;
import com.example.sinh.whateats.network.GooglePlaceApi;
import com.example.sinh.whateats.network.GooglePlaceServiceGenerator;
import com.example.sinh.whateats.search.SearchActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private Menu mOptionsMenu;

    private List<Venue> venueList = new ArrayList<>();
    private List<Result> resultList = new ArrayList<>();
    private Venue venue;
    private Result result;
    private HashMap<Marker, MarkerInformation> markers = new HashMap<>();
    private Marker selectedMarker;
    Polyline wrapperLine;
    Polyline line;

    /**
     * The {@link GoogleApiClient} to get last known location
     */
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        venueList = getIntent().getParcelableArrayListExtra(SearchActivity.VENUE_LIST);
        resultList = getIntent().getParcelableArrayListExtra(SearchActivity.RESULT_LIST);
        venue = getIntent().getExtras().getParcelable(SearchActivity.VENUE_ITEM);
        result = getIntent().getExtras().getParcelable(SearchActivity.RESULT_ITEM);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMapClickListener(this);

        populateMarkers();

        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    private void drawDirection() {
        if (mLastLocation == null) {
            Toast.makeText(this, "Getting your location. Please stay a while", Toast.LENGTH_SHORT).show();
            return;
        }
        GooglePlaceApi googlePlaceApi = GooglePlaceServiceGenerator.createService(GooglePlaceApi.class);
        String src = mLastLocation.getLatitude() + "," + mLastLocation.getLongitude();
        String dest = selectedMarker.getPosition().latitude + "," + selectedMarker.getPosition().longitude;
        Call<DirectionResponse> call = googlePlaceApi.getDirection(src, dest);
        call.enqueue(new Callback<DirectionResponse>() {
            @Override
            public void onResponse(Call<DirectionResponse> call, Response<DirectionResponse> response) {
                DirectionResponse directionResponse = response.body();
                drawPath(directionResponse.getRoutes().get(0));
                for (Marker m: markers.keySet()) {
                    if (!m.getId().equals(selectedMarker.getId())) {
                        m.setVisible(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<DirectionResponse> call, Throwable t) {

            }
        });
    }

    public void drawPath(Route route) {
        String encodedString = route.getOverviewPolyline().getPoints();
        List<LatLng> list = decodePoly(encodedString);
        wrapperLine = mMap.addPolyline(new PolylineOptions()
                .addAll(list)
                .width(16)
                .color(Color.parseColor("#000000"))
                .geodesic(true)
        );

        line = mMap.addPolyline(new PolylineOptions()
                .addAll(list)
                .width(12)
                .color(Color.parseColor("#05b1fb"))//Google maps blue color
                .geodesic(true)
        );

        Bounds b = route.getBounds();
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(new LatLng(b.getSouthwest().getLat(), b.getSouthwest().getLng()))
                .include(new LatLng(b.getNortheast().getLat(), b.getNortheast().getLng()))
                .build();

        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
        selectedMarker.hideInfoWindow();
    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng( (((double) lat / 1E5)),
                    (((double) lng / 1E5) ));
            poly.add(p);
        }

        return poly;
    }

    private void showMarkers() {
        for (Marker m: markers.keySet()) {
            m.setVisible(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (line != null) {
                    showMarkers();
                    line.remove();
                    wrapperLine.remove();
                    line = null;
                    wrapperLine = null;
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(selectedMarker.getPosition(), 13));
                    selectedMarker.showInfoWindow();
                }else {
                    this.onBackPressed();
                }
                return true;
            case R.id.direction:
                drawDirection();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mOptionsMenu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);

        if (venue != null || result != null) {
            showDirection(true);
        }

        return true;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        selectedMarker = marker;
        showDirection(true);
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        boolean newPlaceIsClick = isNewPlaceClicked(markers.get(marker));

        if ((venue != null || result != null) && !newPlaceIsClick) {
            this.onBackPressed();
        }else {
            Intent i = new Intent(MapsActivity.this, DetailActivity.class);
            MarkerInformation mi = markers.get(marker);
            if (mi.mResult != null) {
                for (Result r: resultList) {
                    if (r.getPlaceId().equals(mi.mId)) {
                        i.putExtra(SearchActivity.RESULT_ITEM, r);
                        break;
                    }
                }
            }
            if (mi.mVenue != null) {
                for (Venue v: venueList) {
                    if (v.getId().equals(mi.mId)) {
                        i.putExtra(SearchActivity.VENUE_ITEM, v);
                        break;
                    }
                }
            }
            i.putParcelableArrayListExtra(SearchActivity.VENUE_LIST, (ArrayList<? extends Parcelable>) venueList);
            i.putParcelableArrayListExtra(SearchActivity.RESULT_LIST, (ArrayList<? extends Parcelable>) resultList);
            if (newPlaceIsClick) {
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
                this.finish();
            }else {
                startActivity(i);
            }
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        selectedMarker = null;
        showDirection(false);
    }

    private void populateMarkers() {
        if (!resultList.isEmpty() && !venueList.isEmpty() && mMap != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            for (Result r: resultList) {
                com.example.sinh.whateats.models.googleplace.Location l = r.getGeometry().getLocation();
                LatLng latLng = new LatLng(l.getLat(), l.getLng());
                Marker m = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(r.getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                builder.include(latLng);
                markers.put(m, new MarkerInformation(r.getPlaceId(), r.getVicinity(), r));
            }
            for (Venue v : venueList) {
                com.example.sinh.whateats.models.foursquare.Location l = v.getLocation();
                LatLng latLng = new LatLng(l.getLat(),l.getLng());
                Marker m = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(v.getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                builder.include(latLng);
                markers.put(m, new MarkerInformation(v.getId(),
                        v.getLocation().getFormattedAddress().toString().replace("[", "").replace("]", "")
                        , v));
            }

            LatLngBounds bounds = builder.build();
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
            // Setting an info window adapter allows us to change the both the contents and look of the
            // info window.
            mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(markers));
            if (venue != null) {
                for (Marker m: markers.keySet()) {
                    if (markers.get(m).mId.equals(venue.getId())) {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m.getPosition(), 13));
                        m.showInfoWindow();
                        selectedMarker = m;
                        break;
                    }
                }
            }else if (result != null) {
                for (Marker m: markers.keySet()) {
                    if (markers.get(m).mId.equals(result.getPlaceId())) {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m.getPosition(), 13));
                        m.showInfoWindow();
                        selectedMarker = m;
                        break;
                    }
                }
            }
        }
    }

    private void showDirection(boolean b) {
        if (mOptionsMenu != null) {
            mOptionsMenu.getItem(0).setVisible(b);
        }
    }

    private boolean isNewPlaceClicked (MarkerInformation mi){
        if (venue != null) {
            if (!mi.mId.equals(venue.getId())) {
                return true;
            }
        }
        if (result != null) {
            if (!mi.mId.equals(result.getPlaceId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    public void handleNewLocation(Location location) {
        mLastLocation = location;
        Log.d("TAG", location.toString());
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        else {
            handleNewLocation(mLastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, 9000);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("TAG", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private View mView;
        private Marker markerShowingInfoWindow;
        private HashMap<Marker, MarkerInformation> markers = new HashMap<>();

        private ImageView placePhoto;
        private TextView placeName;
        private TextView address;

        public CustomInfoWindowAdapter(HashMap<Marker, MarkerInformation> markers) {
            this.markers = markers;
            mView = getLayoutInflater().inflate(R.layout.custom_info_content, null);
            placePhoto = (ImageView) mView.findViewById(R.id.place_photo);
            placeName = (TextView) mView.findViewById(R.id.place_name);
            address = (TextView) mView.findViewById(R.id.address);
        }

        private void getPhoto(final Context context, final ImageView iv, Result r) {
            GooglePlaceApi googlePlaceApi = GooglePlaceServiceGenerator.createService(GooglePlaceApi.class);
            if (r.getPhotos() == null) {
                iv.setImageResource(R.drawable.no_img_found);
            }else if (r.getPhotos().isEmpty()) {
                iv.setImageResource(R.drawable.no_img_found);
            } else {
                Photo p = r.getPhotos().get(0);
                Call<ResponseBody> responseBodyCall = googlePlaceApi.getPhoto(p.getPhotoReference(),
                        p.getHeight(), p.getWidth());
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> responseBodyCall, Response<ResponseBody> response) {
                        String url = response.raw().request().url().toString();
                        Glide.with(context)
                                .load(url)
                                .error(R.drawable.no_img_found)
                                .listener(new RequestListener<String, GlideDrawable>() {
                                    @Override
                                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                        if (markerShowingInfoWindow != null && markerShowingInfoWindow.isInfoWindowShown()) {
                                            markerShowingInfoWindow.hideInfoWindow();
                                            markerShowingInfoWindow.showInfoWindow();
                                        }
                                        return false;
                                    }
                                })
                                .into(iv);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("GET_PHOTO", "Some error when get google place photos");
                        iv.setImageResource(R.drawable.no_img_found);
                    }
                });
            }

        }

        private void getPhoto(final Context context, final ImageView iv, Venue v) {
            FoursquareApi foursquareApi = FoursquareServiceGenerator.createService(FoursquareApi.class);
            Call<PhotosResponse> photosResponseCall = foursquareApi.getPhotos(v.getId());
            photosResponseCall.enqueue(new Callback<PhotosResponse>() {
                @Override
                public void onResponse(Call<PhotosResponse> call, Response<PhotosResponse> response) {
                    List<Item> itemList = response.body().getResponse().getPhotos().getItems();
                    if (itemList.isEmpty()) {
                        iv.setImageResource(R.drawable.no_img_found);
                    } else {
                        Item i = itemList.get(0);
                        String url= i.getPrefix()
                                + "original" + i.getSuffix();
                        Glide.with(context)
                                .load(url)
                                .error(R.drawable.no_img_found)
                                .listener(new RequestListener<String, GlideDrawable>() {
                                    @Override
                                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                        if (markerShowingInfoWindow != null && markerShowingInfoWindow.isInfoWindowShown()) {
                                            markerShowingInfoWindow.hideInfoWindow();
                                            markerShowingInfoWindow.showInfoWindow();
                                        }
                                        return false;
                                    }
                                })
                                .into(iv);
                    }
                }

                @Override
                public void onFailure(Call<PhotosResponse> call, Throwable t) {
                    Log.e("GET_PHOTO", "Some error when get foursquare photos");
                    iv.setImageResource(R.drawable.no_img_found);
                }
            });
        }

        @Override
        public View getInfoWindow(Marker marker) {
            markerShowingInfoWindow = marker;
            MarkerInformation mi = markers.get(marker);
            placeName.setText(marker.getTitle());
            address.setText(mi.mAddress);
            if (mi.mResult != null) {
                getPhoto(MapsActivity.this, placePhoto, mi.mResult);
            }
            if (mi.mVenue != null) {
                getPhoto(MapsActivity.this, placePhoto, mi.mVenue);
            }

            return mView;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }

    private class MarkerInformation {

        public String mId;
        public String mAddress;
        public Result mResult;
        public Venue mVenue;

        public MarkerInformation(String id, String address, Result result) {
            this.mId = id;
            this.mAddress = address;
            this.mResult = result;
        }

        public MarkerInformation(String id, String address, Venue venue) {
            this.mId = id;
            this.mAddress = address;
            this.mVenue = venue;
        }
    }
}
