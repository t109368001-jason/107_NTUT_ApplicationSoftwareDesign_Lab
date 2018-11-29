package com.example.user.lab10;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    private final static int REQUEST_PERMISSIONS = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                for(int result:grantResults){
                    if(result !=
                            PackageManager.PERMISSION_GRANTED) {
                        finish();
                    } else {
                        SupportMapFragment map =
                                (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
                        map.getMapAsync(this);
                    }
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSIONS);
        else {
            SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            map.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) return;
        googleMap.setMyLocationEnabled(true);
        MarkerOptions m1 = new MarkerOptions();
        m1.position(new LatLng(25.033611, 121.565000));
        m1.title("台北 101");
        m1.draggable(true);
        googleMap.addMarker(m1);
        MarkerOptions m2 = new MarkerOptions();
        m2.position(new LatLng(25.047924, 121.517081));
        m2.title("台北車站");
        m2.draggable(true);
        googleMap.addMarker(m2);
        PolylineOptions polylineOpt = new PolylineOptions();
        polylineOpt.add(new LatLng(25.033611, 121.565000));
        polylineOpt.add(new LatLng(25.032728, 121.565137));
        polylineOpt.add(new LatLng(25.047924, 121.517081));
        polylineOpt.color(Color.BLUE);
        Polyline polyline = googleMap.addPolyline(polylineOpt);
        polyline.setWidth(10);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(25.034, 121.545), 13));
    }

}
