package com.example.lymanshen.emergencyshelter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import static com.example.lymanshen.emergencyshelter.R.id.toggleButton;


public class MainActivity extends AppCompatActivity {


    // @Override
    public Shelter shlist[];
    public EditText tbox1;
    public EditText tbox2;
    public TextView  nameLoc;
    public TextView  address;
    public TextView  dist;
    public TextView phone;
    public TextView rating;
    public Button but;
    public ToggleButton toggle;
    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            location.getLatitude();
            location.getLongitude();
            Log.d(MainActivity.class.getSimpleName(), Double.toString(location.getLatitude()) );
            tbox1.setText(Double.toString(location.getLatitude()));
            tbox2.setText(Double.toString(location.getLongitude()));
            doThing();

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };


    LocationManager locationManager;

    void startLocation(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M&&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},-1);
        }else {
            Log.d(MainActivity.class.getSimpleName(), "startLocation:");
//if your a version below you use it therwise you get permission
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }


    }

    private void makeUseOfNewLocation(Location location){
        //TODO:
    }
    //LocationManager locatinoManager;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startLocation();



        // LocationListener locationListener = new MyLocationListener();
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);



// Define a listener that responds to location updates


// Register the listener with the Location Manager to receive location updates

        shlist = new Shelter[11];
        shlist[0] = new Shelter("Family Supportive Housing", 37.366089,-121.867950, "692 N King Rd, San Jose, CA 95133","(408) 926-8885", 3.9);
        shlist[1] = new Shelter("LifeMoves | Montgomery Street Inn", 37.3367718, -121.9042078, "358 N Montgomery St, San Jose, CA 95110", "(408) 271-5160", 5);
        shlist[2] = new Shelter("LifeMoves | Julian Street Inn", 37.3353373, -121.90287590000003, "546 W Julian St, San Jose, CA 95110", "(408) 271-0820", 2.5);
        shlist[3] = new Shelter("Lifemoves | Commercial Street Inn", 37.3600696, -121.90081199999997, "260 Commercial St, San Jose, CA 95112", "(408) 271-1630", 5);
        shlist[4] = new Shelter("Cityteam - Men’s Shelter", 37.364672, -121.895808, "1174 Old Bayshore Hwy, San Jose, CA 95112", "(408) 288-2153", 4.7);
        shlist[5] = new Shelter("Heritage Home", 37.344226, -121.893915, "435 N 3rd St, San Jose, CA 95112", "(408) 294-1238", 4.8);
        shlist[6] = new Shelter("The James F. Boccardo Regional Reception Center", 37.303441, -121.870300, "2011 Little Orchard St, San Jose, CA 95125", "(408) 539-2100", -1);
        shlist[7] = new Shelter("Innivision Steven’s House", 37.334047, -121.876150, "435 S 10th St, San Jose, CA 95112", "(408) 280-7019", -1);
        shlist[8] = new Shelter("HomeFirst - Sobrato House Youth Center", 37.329938, -121.882690, "496 S 3rd St, San Jose, CA 95112", "(408) 539-2180", -1);
        shlist[9] = new Shelter("Next Door Solutions to Domestic", 37.363058, -121.904079, "234 E Gish Rd #200, San Jose, CA 95112", "(408) 501-7550", 4.8);
        shlist[10] = new Shelter("Cityteam Headquarters", 37.383549, -121.918972, "2304 Zanker Rd, San Jose, CA 95131", "(408) 232-5600", 3.7);

        but = (Button)findViewById(R.id.IDmanButton);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                doThing();
            }

        });
        tbox1 = (EditText) findViewById(R.id.IDlatitude);
        tbox2 = (EditText) findViewById(R.id.IDlongtitude);
        nameLoc = (TextView) findViewById(R.id.IDname);
        address = (TextView) findViewById(R.id.IDadrress);
        dist = (TextView) findViewById(R.id.IDdistance);
        phone = (TextView) findViewById(R.id.IDphone);
        rating = (TextView) findViewById(R.id.IDrating);
        toggle = (ToggleButton) findViewById(toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startLocation();
                } else {
                    if(locationManager != null)
                    {
                        locationManager.removeUpdates(locationListener);
                        locationManager = null;
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int i =0; i< permissions.length;i++){
            if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                startLocation();


            }

        }
    }

    public void  doThing()
    {
        int i = 0;
        int j = 0;


        double currentlatitude = Double.parseDouble(tbox1.getText().toString());
        double currentlongitude = Double.parseDouble(tbox2.getText().toString());
        double dlon = currentlongitude-  shlist[i].getLongitude();
        double dlat = currentlongitude-  shlist[i].getLatitude();
        double distance = findDistance(currentlatitude,currentlongitude,dlat,dlon);
        double mindistance = distance;

        for(i=1;i<11;i++)
        {
            dlon = shlist[i].getLongitude();
            dlat = shlist[i].getLatitude();
            distance = findDistance(currentlatitude,currentlongitude,dlat,dlon);
            if(distance < mindistance)
            {
                mindistance = distance;
                j = i;
            }
        }
        String showName = shlist[j].getName();
        nameLoc.setText(showName);
        String showDistance = Double.toString(mindistance) +" miles";
        // String showDistance = Double.toString(mindistance) +" miles";
        dist.setText(showDistance);
        String showAddress = shlist[j].getAddress();
        address.setText(showAddress);
        String showPhone = shlist[j].getPhone();
        phone.setText(showPhone);
        String showRating;
        if(shlist[j].getRating() != -1)
        {
            showRating = Double.toString(shlist[j].getRating()) + " out of 5 stars";
        }
        else
        {
            showRating = "No rating availible.\n";
        }
        rating.setText(showRating);

    }

    private double findDistance(double lat1, double long1, double lat2, double long2)
    {
        lat1 = lat1*Math.PI/180;
        long1 = long1*Math.PI/180;
        lat2 = lat2*Math.PI/180;
        long2 = long2*Math.PI/180;
        double dlong = long2 - long1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat/2),2)+Math.cos(lat1)*Math.cos(lat2)*Math.pow(Math.sin(dlong/2),2);
        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        return 3961 * c;
    }
}



class Shelter {
    private String name;
    private double latitude;
    private double longitude;
    private String address;
    private String phone;
    private double rating;

    // constructor
    public Shelter (String name, double latitude, double longitude, String address, String phone, double rating) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    // getter
    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getAddress () {return address; }
    public String getPhone() {return phone;}
    public double getRating() {return rating;}
    // setter

    public void setName(String name) { this.name = name; }
    public void setLatitude(double lattitude) { this.latitude = lattitude; }
    public void setLongtitude(double longitude) {this.longitude = longitude; }
    public void setAddress (String address) {this.address = address;}
    public void setPhone (String phone) {this.phone = phone; }
    public void setRating (double rating) {this.rating = rating; }
}


// Register the listener with the Location Manager to receive location updates