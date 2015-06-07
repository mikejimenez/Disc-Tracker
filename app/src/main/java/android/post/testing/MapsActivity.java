package android.post.testing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapsActivity extends Activity implements LocationListener {

    private GoogleMap googleMap;
    private LocationManager locationManager;
    private String provider;
//    private double lat;
//    private double lng;
    private static final String TAG = "MapsActivity";
    double latitude; // latitude
    double longitude; // longitude

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        button_listen();
        try {
            initilizeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }

 }
public void button_listen() {
    final Button button = (Button) findViewById(R.id.button);


//        mButton.setOnClickListener(
//                new View.OnClickListener() {
//                    public void onClick(View view) {
//                        submitResponse();
//                    }
//                }
//        );
    button.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View v) {
            Log.d("MAPS", "Clicked");
        }
    });
}
    /**
     * function to load map. If map is not created it will create it for you
     */
    private void initilizeMap() {

        if (googleMap == null) {
            GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            getLocation();

            map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
            map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition arg0) {
                    getLocation();

                }
            });

            if (googleMap == null) {
                // Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
            }
        }

    }

    protected void getLocation() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            Log.d(TAG, "Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            Toast.makeText(getApplicationContext(), "Location not available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    public void onLocationChanged(Location location) {

        GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
//        lat = location.getLatitude();
//        lng = location.getLongitude();
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);

        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(18));
        Polyline line = map.addPolyline(new PolylineOptions()
                .add(new LatLng(latitude, longitude), new LatLng(latitude, longitude))
                .width(5)
                .color(Color.RED));
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }
}