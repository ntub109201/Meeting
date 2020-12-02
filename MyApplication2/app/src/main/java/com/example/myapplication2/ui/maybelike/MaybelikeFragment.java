package com.example.myapplication2.ui.maybelike;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;
import com.example.myapplication2.R;
import com.example.myapplication2.User.PersonalActivity;
import com.example.myapplication2.sqlReturn;
import com.example.myapplication2.ui.dashboard.Statistics;
import com.example.myapplication2.ui.home.HomeFragment;
import com.example.myapplication2.ui.maybelike.adpter.ListViewAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.maps.android.ui.IconGenerator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MaybelikeFragment extends Fragment implements OnMapReadyCallback,GoogleAPIResponseDataInterface {

    private ImageButton imBtnPersonal;

    //private Context mContext = getContext();
    private static final String TAG = MaybelikeFragment.class.getSimpleName();
    private static GoogleMap map;
    private MapView mapView;
    private UiSettings ui;
    private CameraPosition cameraPosition;

    private PlacesClient placesClient;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private final LatLng defaultLocation = new LatLng(25.0418903,121.5256203);
    private static final int DEFAULT_ZOOM = 17;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;
    private Location lastKnownLocation;
    private static final String KEY_LOCATION = "location";

    // GetSurroundingFeatures
    private final int defaultRadius = 1000;
    private int radius = -1;
    private final String defaultSearchType = "restaurant";
    private String searchType;
    // ---------------------------------
    private static JSONObject jObject = new JSONObject();
    // TabLayout
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private ArrayList<String> tab_ch = new ArrayList<>();
    private ArrayList<String> tab_type = new ArrayList<>();
    // Views
    private Button search_around_btn;
    // SwipeLayout
    private SwipeLayout swipeLayout;
    private ListView mListView;
    private ListViewAdapter mAdapter;
    private Map<String, Bitmap[]> bitmaps = new HashMap<>();

    private BottomSheetBehavior bottomSheetBehavior;
    private ArrayList<Marker> markerList;
    private Object lastClickedMarker;

    private DisplayMetrics dm = new DisplayMetrics();

    private String api_key;
    // count finished thread
    private int finished_thread_count;
    //----------------------
    // GetPlacePhoto
    private final int image_max_width = 300;
    private Map<String, Bitmap[]> place_photos = new HashMap<>();
    //----------------------
    // GetDistanceToPlace
    private Map<String, ArrayList<Integer>> place_distance = new HashMap<>();
    //----------------------
    private Thread getPlacePhoto;
    private Thread getDistanceToPlace;
    private Thread getSurroundingFeatures;
    // handler
    private Handler handler = new Handler(new Handler.Callback() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean handleMessage(@NonNull Message m) {
            String s = m.getData().getString("key")==null?"":m.getData().getString("key");
            Log.d(TAG, "handleMessage: "+s);
            assert s != null;
            switch (s){
                case "GetSurroundingFeatures":
                    try {
                        if(!jObject.getJSONObject(tabText).getString("status").equals("ZERO_RESULTS")){
                            finished_thread_count = 0;
                            getPlacePhoto = new Thread(new GetPlacePhoto());
                            getDistanceToPlace = new Thread(new GetDistanceToPlace());
                            getPlacePhoto.start();
                            getDistanceToPlace.start();
                        }else{
                            setListView();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case "photo_and_distance":
                    finished_thread_count++;
                    if(finished_thread_count>=2){
                        setListView();
                    }
            }
            return false;
        }
    });
    private void setListView(){
        mAdapter = new ListViewAdapter(getActivity(), googleAPIResponseDataInterface());
        Log.d(TAG, mAdapter.toString());
        mListView.setAdapter(mAdapter);
        mListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        mAdapter.setMode(Attributes.Mode.Single);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //((SwipeLayout)(mListView.getChildAt(position - mListView.getFirstVisiblePosition()))).open(true);
                map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(markerList.get(position).getPosition().latitude, markerList.get(position).getPosition().longitude), map.getCameraPosition().zoom, 0f, 0f)));
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("ListView", "OnTouch");
                return false;
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e("ListView", "onScrollStateChanged");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("ListView", "onItemSelected:" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Log.e("ListView", "onNothingSelected:");
            }
        });
        addMarker();
    }
    //private GoogleAPIResponse googleAPIResponse = new GoogleAPIResponse(getResources().getString(R.string.google_maps_key_web), lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_maybelike, container, false);
        api_key = getResources().getString(R.string.google_maps_key_web);
        if(HomeFragment.changeBtn == true){
            HomeFragment.changeBtn = false;
        }

        imBtnPersonal = root.findViewById(R.id.imBtnPersonal);
        imBtnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaybelikeFragment.super.getActivity(), PersonalActivity.class);
                intent.putExtra("pageId",4);
                startActivity(intent);
            }
        });
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        // get the bottom sheet view
        LinearLayout llBottomSheet = root.findViewById(R.id.bottom_sheet);
        // set bottom sheet height in percentlBottomSheet.getLayoutParams();
        llBottomSheet.getLayoutParams().height = dm.heightPixels*7/9;
//        params.height = dm.heightPixels*7/9;
//        llBottomSheet.setLayoutParams(params);
//        ViewGroup.LayoutParams params = l

        // init the bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

        // change the state of the bottom sheet
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setDraggable(false);
        // set the peek height
        bottomSheetBehavior.setPeekHeight(250);

        // set hideable or not
        bottomSheetBehavior.setHideable(false);



        Log.d(TAG, "onCreate: "+bottomSheetBehavior.getState());

//        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
//        placesClient = Places.createClient(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
        //        .findFragmentById(R.id.map);
        mapView = (MapView) root.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        //mapFragment.getMapAsync();

        float total_mood = sqlReturn.moodResult01+sqlReturn.moodResult02+sqlReturn.moodResult03+sqlReturn.moodResult04+sqlReturn.moodResult05,
                total_tag = sqlReturn.tagResult01+sqlReturn.tagResult02+sqlReturn.tagResult03+sqlReturn.tagResult04+sqlReturn.tagResult05;
        Statistics stat = new Statistics();
        if(total_mood==0&&total_tag==0){
            stat.getMoodAndTag();
            total_mood = sqlReturn.moodResult01+sqlReturn.moodResult02+sqlReturn.moodResult03+sqlReturn.moodResult04+sqlReturn.moodResult05;
            total_tag = sqlReturn.tagResult01+sqlReturn.tagResult02+sqlReturn.tagResult03+sqlReturn.tagResult04+sqlReturn.tagResult05;
        }else{
            stat.getTag();
        }
        if(total_mood!=0 && total_tag!=0){
            float judge = (sqlReturn.moodResult04+sqlReturn.moodResult05)/total_mood;
            if(judge >= 0.4){
                setTabList(sqlReturn.PersonalHobby);
            }else{
                if(sqlReturn.tagResult01>=sqlReturn.tagResult02&&sqlReturn.tagResult01>=sqlReturn.tagResult03&&sqlReturn.tagResult01>=sqlReturn.tagResult04&&sqlReturn.tagResult01>=sqlReturn.tagResult05){
                    setTabList("美食");
                }else if(sqlReturn.tagResult02>=sqlReturn.tagResult01&&sqlReturn.tagResult02>=sqlReturn.tagResult03&&sqlReturn.tagResult02>=sqlReturn.tagResult04&&sqlReturn.tagResult02>=sqlReturn.tagResult05){
                    setTabList("購物");
                }else if(sqlReturn.tagResult03>=sqlReturn.tagResult01&&sqlReturn.tagResult03>=sqlReturn.tagResult02&&sqlReturn.tagResult03>=sqlReturn.tagResult04&&sqlReturn.tagResult03>=sqlReturn.tagResult05){
                    setTabList("戀愛");
                }else if(sqlReturn.tagResult04>=sqlReturn.tagResult01&&sqlReturn.tagResult04>=sqlReturn.tagResult02&&sqlReturn.tagResult04>=sqlReturn.tagResult03&&sqlReturn.tagResult04>=sqlReturn.tagResult05){
                    setTabList("旅遊");
                }else{
                    setTabList("休閒娛樂");
                }
            }
        }else{
            setTabList("default");
        }

        tabLayout = root.findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (String s : tab_ch) tabLayout.addTab(tabLayout.newTab().setText(s));
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorTabText),getResources().getColor(R.color.colorTabTextSelected));
        // set Stepping_Stones
        TextView maps_stepping_stone = root.findViewById(R.id.map_stepping_stones);
        maps_stepping_stone.setHeight(tabLayout.getHeight());
        // set Tab Seleceted Listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                map.clear();
                // add JSONObject from google map api
                Log.d(TAG, "onTabSelected: ");
                String tab_name = (String) tab.getText();
                if (!jObject.has(tab_name) && jObject.isNull(tab_name)) {
                    if (tab.getText() != null) {
//                        InitListView((String) tab.getText());
                        //googleAPIResponse = new GoogleAPIResponse(getResources().getString(R.string.google_maps_key_web), lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                        start(tab_name);
                        //addMarker_v2();
                    }
                }else{
                    setTabText(tab_name);
                    setTabList2(null);
                    if (tab_type.size() != tab_ch.size() || tabText.isEmpty()){
                        return;
                    }
                    searchType = tab_type.get(tab_ch.indexOf(tabText));
                    if (radius == -1) radius = defaultRadius;
                    if (searchType == null) searchType = defaultSearchType;

                    mAdapter = new ListViewAdapter(getActivity(), googleAPIResponseDataInterface());
                    Log.d(TAG, mAdapter.toString());
                    mListView.setAdapter(mAdapter);
                    mListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
                    mAdapter.setMode(Attributes.Mode.Single);

                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //((SwipeLayout)(mListView.getChildAt(position - mListView.getFirstVisiblePosition()))).open(true);
                            map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(markerList.get(position).getPosition().latitude, markerList.get(position).getPosition().longitude), map.getCameraPosition().zoom, 0f, 0f)));
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            if(!bottomSheetBehavior.isDraggable())bottomSheetBehavior.setDraggable(true);
                        }
                    });
                    mListView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.e("ListView", "OnTouch");
                            return false;
                        }
                    });
                    mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            //Toast.makeText(getActivity(), "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                    mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(AbsListView view, int scrollState) {
                            Log.e("ListView", "onScrollStateChanged");
                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                        }
                    });

                    mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //Log.e("ListView", "onItemSelected:" + position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            //Log.e("ListView", "onNothingSelected:");
                        }
                    });
                    addMarker();
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mListView = root.findViewById(R.id.features_listView);
        start(tab_ch.get(tabLayout.getSelectedTabPosition()));
        return root;
    }
    public void start(String tab_name){
        setTabText(tab_name);
        setTabList2(null);
        if (tab_type.size() != tab_ch.size() || tabText.isEmpty()){
            return;
        }
        searchType = tab_type.get(tab_ch.indexOf(tabText));
        if (radius == -1) radius = defaultRadius;
        if (searchType == null) searchType = defaultSearchType;
        getSurroundingFeatures = new Thread(new GetSurroundingFeatures());
        getSurroundingFeatures.start();
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
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity(), R.raw.hiding_map_features));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        map = googleMap;
        ui = map.getUiSettings();
        ui.setZoomControlsEnabled(false);

        // Prompt the user for permission.
        getLocationPermission();
        // [END_EXCLUDE]

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
        // Get the current location of the device and set the position of the map.
        getDeviceLocation();

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (lastClickedMarker != null && lastClickedMarker instanceof Integer)
                    markerList.get((int)lastClickedMarker).setZIndex(1);
                marker.setZIndex(2);
                lastClickedMarker = marker.getTag();

                float bias=0;
                boolean ret = true, isbottomed = false;
                if (lastClickedMarker != null && lastClickedMarker instanceof Integer){
                    switch(getItemsCount()-(int)lastClickedMarker){
                        case 1:
                            isbottomed = true;
                            bottomSheetBehavior.setHalfExpandedRatio(1f);
                            break;
                        case 2:
                            isbottomed = true;
                            bias = (float)dm.heightPixels/3;
                            bottomSheetBehavior.setHalfExpandedRatio(0.8f);
                            break;
                        case 3:
                            isbottomed = true;
                            bias = (float)dm.heightPixels/6;
                            bottomSheetBehavior.setHalfExpandedRatio(0.6f);
                            break;
                        case 4:
                            isbottomed = true;
                            bias = (float)dm.heightPixels/10;
                            bottomSheetBehavior.setHalfExpandedRatio(0.4f);
                            break;
                        default:
                            ret = false;
                            bottomSheetBehavior.setHalfExpandedRatio(0.365f);
                            break;
                    }
                    mListView.smoothScrollToPositionFromTop((int)lastClickedMarker, 0, 200);
                    if (isbottomed && bottomSheetBehavior.getState()!=BottomSheetBehavior.STATE_COLLAPSED){
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                    if (bottomSheetBehavior.getHalfExpandedRatio()!=0.365f || bottomSheetBehavior.getState()!=BottomSheetBehavior.STATE_HALF_EXPANDED)
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                }
                if(!bottomSheetBehavior.isDraggable())bottomSheetBehavior.setDraggable(true);
                if (true){
                    map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(marker.getPosition().latitude,marker.getPosition().longitude),DEFAULT_ZOOM,0f, 0f)));
                    map.moveCamera(CameraUpdateFactory.scrollBy(0, bias));
                }
                return true;
            }
        });
    }
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            lastKnownLocation = task.getResult();
                            if (lastKnownLocation != null) {
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lastKnownLocation.getLatitude(),
                                                lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                                Log.d(TAG, "NiCe: "+lastKnownLocation.getLatitude()+" - "+lastKnownLocation.getLongitude());
//                                new GoogleAPIResponse(getResources().getString(R.string.google_maps_key_web), lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()).setTabText(("咖啡廳")).start();
                            }

                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            map.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                            map.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }
    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addMarker_v2(){
        markerList = new ArrayList<>();
        for (int i=0; i<getItemsCount(); i++){
            LatLng latLng = new LatLng(getLat(i), getLng(i));

            TextView text = new TextView(getActivity().getApplicationContext());
            text.setText(getPlaceName(i));
            text.setTextAppearance(R.style.PlaceNameInfoOverlayText);
            text.setMaxWidth(500);
            IconGenerator generator = new IconGenerator(getActivity().getApplicationContext());
            generator.setContentView(text);
            generator.setStyle(IconGenerator.STYLE_DEFAULT);
            generator.setBackground(new ColorDrawable(Color.TRANSPARENT));
            Bitmap textIcon = generator.makeIcon();

            Bitmap photo = ImageHelper.getBitmap(getActivity().getApplicationContext(), R.drawable.ic_pin);
            int photoWidth = photo.getWidth(), photoHeight= photo.getHeight()
                    , textWidth = textIcon.getWidth(), textHeight = textIcon.getHeight();
            Bitmap.Config conf = Bitmap.Config.ARGB_8888;
            int max_height = Math.max(textHeight, photoHeight);
            Bitmap bmp = Bitmap.createBitmap(textWidth+photoWidth+3, max_height, conf);

            Canvas canvas = new Canvas(bmp);

            // paint defines the text color, stroke width and size
            Paint color = new Paint();
            color.setTextSize(36);
            color.setColor(Color.RED);
            int k = 1, photo_bias = max_height/k;
            while(max_height - photo_bias < photoHeight){
                k++;
                photo_bias = max_height/k;
                //Log.d(TAG, "addMarker_v2: "+k);
            }
            canvas.drawBitmap(photo, 0, (float)(photo_bias), color);
            canvas.drawBitmap(textIcon, (float)photoWidth+2, 0, color);

            markerList.add(map.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                    .anchor(0.06f,0.95f)));
            markerList.get(markerList.size()-1).setTag(i);
        }
    }
    private int dp2px(float dip){
        Resources r = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
    }
    private void addMarker(){
        markerList = new ArrayList<>();
        for (int i=0; i<getItemsCount(); i++){
//            Marker marker = map.addMarker(new MarkerOptions()
//                    .position(new LatLng(googleAPIResponse.getLat(i),googleAPIResponse.getLng(i)))
//                    .title(googleAPIResponse.getPlaceName(i)));
//            marker.setTag(i);
            LatLng latLng = new LatLng(getLat(i),getLng(i));
            if(getActivity()==null)return;
            TextView text = new TextView(getActivity().getApplicationContext());
            text.setText(getPlaceName(i));
            text.setTextSize(18);
            text.setTextColor(getResources().getColor(R.color.colorTabText));
            //text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 9));
            ImageView imageView_tap = new ImageView(getActivity().getApplicationContext());
            imageView_tap.setImageResource(R.mipmap.ic_btn_tap_foreground);
            imageView_tap.setScaleX(1.65f);
            imageView_tap.setScaleY(1.65f);
            //imageView_tap.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            LinearLayout linearLayout = new LinearLayout(getActivity().getApplicationContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setWeightSum(20);
            linearLayout.setPadding(dp2px(2), dp2px(1), dp2px(2), dp2px(1));
//            TextView fooText = new TextView(getApplicationContext());
//            fooText.setText(googleAPIResponse.getPlaceName(i));
//            fooText.setTextSize(18);
//            IconGenerator ig = new IconGenerator(getApplicationContext());
//            ig.setStyle(IconGenerator.STYLE_DEFAULT);
//            ig.setContentView(fooText);
//            Bitmap b = ig.makeIcon();
            linearLayout.addView(imageView_tap, new LinearLayout.LayoutParams(dp2px(25), LinearLayout.LayoutParams.MATCH_PARENT, 1));
            linearLayout.addView(text, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 19));
            IconGenerator generator = new IconGenerator(getActivity().getApplicationContext());
//            generator.setBackground(getApplicationContext().getDrawable(R.drawable.pin));
            generator.setContentView(linearLayout);
            Bitmap textIcon = generator.makeIcon();

            //Bitmap photo = BitmapFactory.decodeResource(getApplication().getResources(),R.drawable.pin);
            Bitmap pin = ImageHelper.getBitmap(getActivity().getApplicationContext(), R.drawable.ic_pin);
            int pinWidth = pin.getWidth(), pinHeight= pin.getHeight()
                    , textWidth = textIcon.getWidth(), textHeight = textIcon.getHeight();
            Bitmap.Config conf = Bitmap.Config.ARGB_8888;
            Bitmap bmp = Bitmap.createBitmap(textWidth, textHeight+pinHeight+3, conf);

            Canvas canvas = new Canvas(bmp);

            // paint defines the text color, stroke width and size
            Paint color = new Paint();
            //color.setTextSize(36);
            color.setColor(Color.RED);

//            Rect src = new Rect(0, 0, photo.getWidth(), photo.getHeight());//创建一个指定的新矩形的坐标
//            Rect dst = new Rect(0, 0, width, height);//创建一个指定的新矩形的坐标
//            canvas.drawBitmap(photo, src, dst, color);//将photo 缩放或则扩大到 dst使用的填充区photoPaint
            //canvas.drawText(googleAPIResponse.getPlaceName(i), 0, 36, color);//绘制上去字，开始未知x,y采用那只笔绘制
            canvas.drawBitmap(textIcon, 0, 0, color);
            canvas.drawBitmap(pin, (float)((textWidth/3)), (float)textHeight+2, color);
            // modify canvas
//            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
//                    R.drawable.pin), 0,0, color);
//            canvas.drawText(googleAPIResponse.getPlaceName(i), 30, 40, color);
            markerList.add(map.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                    .anchor(0.41f,1f)));
            markerList.get(markerList.size()-1).setTag(i);
        }
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        if(!bottomSheetBehavior.isDraggable())bottomSheetBehavior.setDraggable(true);
    }
    private void setTabList(@Nullable String TAB){
        String[] tab = null;
        if (TAB != null) {
            switch (TAB){
                case "美食":
                    tab = new String[]{"restaurant", "cafe", "bar"};
                    break;
                case "購物":
                    tab = new String[]{"shopping_mall", "store", "supermarket"};
                    break;
                case "戀愛":
                    tab = new String[]{"cafe", "movie_theater"};
                    break;
                case "旅遊":
                    tab = new String[]{"amusement_park", "tourist_attraction"};
                    break;
                case "休閒":
                    tab = new String[]{"movie_theater", "park"};
                    break;
                default:
                    tab = null;
            }
        }
        if (tab == null || tab.length == 0){
            String[] a = new String[]{"餐廳", "咖啡廳", "酒吧"};
            String[] b = new String[]{"restaurant", "cafe", "bar"};
            Collections.addAll(tab_ch, a);
            Collections.addAll(tab_type, b);
        }else{
            try{
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("restaurant", "餐廳")
                        .put("cafe", "咖啡廳")
                        .put("bar", "酒吧")
                        .put("shopping_mall", "購物中心")
                        .put("store", "商店")
                        .put("supermarket", "超市")
                        .put("cafe", "咖啡廳")
                        .put("movie_theater", "電影院")
                        .put("amusement_park", "遊樂園")
                        .put("tourist_attraction", "旅遊景點")
                        .put("park", "公園");;

                String[] a = new String[tab.length];
                for (int i=0; i<tab.length; i++){
                    a[i] = jsonObject.getString(tab[i]);
                }
                Collections.addAll(tab_ch, a);
                Collections.addAll(tab_type, tab);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
    private String tabText;
    public void setTabText(String tabText){
        this.tabText = tabText;
    }
    private void setTabList2(@Nullable String[] tab){
        if (tab == null){
            String[] a = new String[]{"餐廳", "咖啡廳", "酒吧", "購物中心", "商店", "超市", "咖啡廳", "電影院", "遊樂園", "旅遊景點", "公園"};
            String[] b = new String[]{"restaurant", "cafe", "bar", "shopping_mall", "store", "supermarket", "cafe", "movie_theater", "amusement_park", "tourist_attraction", "park"};
            Collections.addAll(tab_ch, a);
            Collections.addAll(tab_type, b);
        }else{

        }
    }
    //    public void start(){
//        setTabList(null);
//        if (tab_type.size() != tab_ch.size() || tabText.isEmpty()){
//            return;
//        }
//        searchType = tab_type.get(tab_ch.indexOf(tabText));
//        if (radius == -1) radius = defaultRadius;
//        if (searchType == null) searchType = defaultSearchType;
//
//        Thread getSurroundingFeatures = new Thread(new GetSurroundingFeatures());
//        getSurroundingFeatures.start();
//        synchronized(this){
//            try{
//                this.wait();
//                Log.d("NiCe", "OK_1");
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//        Log.d("NiCe", "OK_2");
//        finished_thread_count = 0;
//        Thread getPlacePhoto = new Thread(new GetPlacePhoto(this));
//        Thread getDistanceToPlace = new Thread(new GetDistanceToPlace(this));
//        getPlacePhoto.start();
//        getDistanceToPlace.start();
//        Log.d("NiCe", "OK_3");
//        synchronized (this){
//            while(true){
//                if (finished_thread_count >= 2) break;
//                try{
//                    this.wait();
//                    Log.d("NiCe", "OK_4");
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//            }
//            Log.d("NiCe", "OK_5");
//        }
//    }
    private class GetSurroundingFeatures implements Runnable{
        private String getSurroundingFeature_api;
        GetSurroundingFeatures(){
            StringBuilder sb = new StringBuilder();
            sb.append("https://maps.googleapis.com/maps/api/place/nearbysearch/json?")
                    .append("location=").append(sqlReturn.lat).append(",").append(sqlReturn.lng).append("&")
                    .append("radius=").append(radius).append("&")
                    .append("types=").append(searchType).append("&")
                    .append("sensor=").append(true).append("&")
                    .append("key=").append(api_key);
            this.getSurroundingFeature_api = sb.toString();
        }
        @Override
        public void run() {
            //synchronized(GoogleAPIResponse.this) {
            Log.d("NiCe", this.getSurroundingFeature_api);
            HttpURLConnection connection;
            try{
                URL url = new URL(getSurroundingFeature_api);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setUseCaches(true);
                connection.setAllowUserInteraction(false);
                connection.setDoInput(true);
                connection.connect();
                int status = connection.getResponseCode();
                switch (status){
                    case 200:
                    case 201:
                        BufferedReader br =new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line).append("\n");
                        }
                        br.close();
                        jObject.put(tabText, new JSONObject(sb.toString()));
                        Log.d(TAG, sb.toString());
                        //GoogleAPIResponse.this.notify();
                        Message msg = Message.obtain();
                        msg.getData().putString("key","GetSurroundingFeatures");
                        handler.sendMessage(msg);
                        Log.d(TAG, "run: handler sendMessage");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //}
        }
    }
    public class GetPlacePhoto implements Runnable{
        private NotifyInterface mInterface = null;
        private String[] GetPlacePhoto_api;
        GetPlacePhoto() {
            //this.mInterface = iface;
            if (jObject.has(tabText) && !jObject.isNull(tabText)){
                try{
                    if (jObject.getJSONObject(tabText).getJSONArray("results").length() > 0){
                        GetPlacePhoto_api = new String[jObject.getJSONObject(tabText).getJSONArray("results").length()];
                        for (int i=0; i<jObject.getJSONObject(tabText).getJSONArray("results").length(); i++){
                            try{
                                StringBuilder sb = new StringBuilder();
                                GetPlacePhoto_api[i] =
                                        sb.append("https://maps.googleapis.com/maps/api/place/photo?")
                                                .append("photoreference=").append(jObject.getJSONObject(tabText).getJSONArray("results").getJSONObject(i).getJSONArray("photos").getJSONObject(0).getString("photo_reference")).append("&")
                                                .append("key=").append(api_key).append("&")
                                                .append("maxwidth=").append(image_max_width).toString();
                                Log.d(TAG, i+"GetPlacePhoto: "+GetPlacePhoto_api[i]);
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
        private void notifyEnd() {
            if (this.mInterface != null)
                this.mInterface.runEnd();
        }
        @Override
        public void run() {
            if (GetPlacePhoto_api != null){
                Bitmap[] bitmaps = new Bitmap[GetPlacePhoto_api.length];
                for (int i=0; i<GetPlacePhoto_api.length; i++){
                    bitmaps[i] = getBitmapFromURL(GetPlacePhoto_api[i]);
                }
                place_photos.put(tabText, bitmaps);
                //this.notifyEnd();
                Message msg = Message.obtain();
                msg.getData().putString("key", "photo_and_distance");
                handler.sendMessage(msg);
            }
        }
        private Bitmap getBitmapFromURL(String url){
            Bitmap bitmap;
            HttpURLConnection connection;
            try{
                URL u = new URL(url);
                connection = (HttpURLConnection) u.openConnection();
                // connection.setRequestMethod("GET");
                connection.setUseCaches(false);
                connection.setAllowUserInteraction(false);
                connection.setDoInput(true);
                connection.connect();
                int status = connection.getResponseCode();
                switch (status){
                    case 200:
                    case 201:
                        InputStream input = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(input);
                        return ImageHelper.getRoundedCornerBitmap(bitmap, 20);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public class GetDistanceToPlace implements Runnable{
        private NotifyInterface mInterface = null;
        private String[] GetDistanceToPlace_api;
        GetDistanceToPlace(){
            //this.mInterface = iface;
            if (jObject.has(tabText) && !jObject.isNull(tabText)) {
                try {
                    int sent_count = (int) Math.ceil(jObject.getJSONObject(tabText).getJSONArray("results").length() / 25.0);
                    GetDistanceToPlace_api = new String[sent_count];
                    Log.d(TAG, "GetDistanceToPlace: " + sent_count);
                    for (int i = 0; i < sent_count; i++) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("https://maps.googleapis.com/maps/api/distancematrix/json?")
                                .append("key=").append(api_key).append("&")
                                .append("mode=").append("walking").append("&")
                                .append("origins=").append(sqlReturn.lat).append(",").append(sqlReturn.lng).append("&")
                                .append("destinations=");
                        for (int j = i * 25; j < jObject.getJSONObject(tabText).getJSONArray("results").length(); j++) {
                            if (j == 25 + i * 25) break;
                            sb.append(jObject.getJSONObject(tabText).getJSONArray("results").getJSONObject(j).getJSONObject("geometry").getJSONObject("location").getDouble("lat"))
                                    .append(",")
                                    .append(jObject.getJSONObject(tabText).getJSONArray("results").getJSONObject(j).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
                            if (j != jObject.getJSONObject(tabText).getJSONArray("results").length()-1)
                                sb.append("|");
                        }
                        GetDistanceToPlace_api[i] = sb.toString();
                        Log.d(TAG, "GetDistanceToPlace: " + GetDistanceToPlace_api[i]);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        private void notifyEnd() {
            if (this.mInterface != null)
                this.mInterface.runEnd();
        }
        @Override
        public void run() {
            if (GetDistanceToPlace_api != null){
                ArrayList<Integer> arrayList = new ArrayList<>();;
                for (String api : GetDistanceToPlace_api) {
                    try {
                        HttpURLConnection connection;
                        URL url = new URL(api);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setUseCaches(false);
                        connection.setAllowUserInteraction(false);
                        connection.setDoInput(true);
                        connection.connect();
                        int status = connection.getResponseCode();
                        Log.d(TAG, "status: "+status);
                        switch (status) {
                            case 200:
                            case 201:
                                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                                StringBuilder sb = new StringBuilder();
                                String line;
                                while ((line = br.readLine()) != null) {
                                    sb.append(line).append("\n");
                                }
                                br.close();
                                JSONObject jsonObject = new JSONObject(sb.toString());
                                for (int i = 0; i < jsonObject.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").length(); i++) {
                                    arrayList.add(jsonObject.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("distance").getInt("value"));
                                    Log.d(TAG, "run: "+jsonObject.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("distance").getInt("value"));
                                }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                place_distance.put(tabText, arrayList);
                //this.notifyEnd();
                Message msg = Message.obtain();
                msg.getData().putString("key","photo_and_distance");
                handler.sendMessage(msg);
            }
        }
    }
    @Override
    public String getPlaceName(int position) {
        String placeName = null;
        try{
            if (jObject.has(tabText) && !jObject.isNull(tabText))
                placeName = jObject.getJSONObject(tabText).getJSONArray("results").getJSONObject(position).getString("name");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return placeName;
    }
    @Override
    public Bitmap getPlacePhoto(int position){
        return (Objects.requireNonNull(this.place_photos.get(tabText)))[position];
    }
    @Override
    public int getPlaceDistance(int position){
        return (this.place_distance.get(tabText)).get(position);
    }
    @Override
    public int getItemsCount(){
        int itemsCount = 0;
        try{
            if (jObject.has(tabText) && !jObject.isNull(tabText))
                itemsCount = jObject.getJSONObject(tabText).getJSONArray("results").length();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return itemsCount;
    }
    @Override
    public double getLng(int position){
        double lng = 0;
        try{
            lng = jObject.getJSONObject(tabText).getJSONArray("results").getJSONObject(position).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
        }catch (JSONException e){
            e.printStackTrace();
        }

        return lng;
    }
    @Override
    public double getLat(int position){
        double lat = 0;
        try{
            lat = jObject.getJSONObject(tabText).getJSONArray("results").getJSONObject(position).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
        }catch (JSONException e){
            e.printStackTrace();
        }

        return lat;
    }
    public GoogleAPIResponseDataInterface googleAPIResponseDataInterface(){
        return this;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
