package com.example.tristen.hackathonapp;
//package com.example.lymanshen.emergencyshelter;
        import android.Manifest;
        import android.content.Context;
        import android.content.pm.PackageManager;
        import android.location.Address;
        import android.location.Geocoder;
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
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Button;
        import android.widget.Toast;

        import java.io.IOException;
        import java.util.List;
        import java.util.Locale;
import java.lang.String;
import android.location.Location;
import java.lang.Object;


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

    void startLocation(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M&&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},-1);
        }else {
            Log.d(MainActivity.class.getSimpleName(), "startLocation:");
//if your a version below you use it therwise you get permission
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
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
               //doThing();
                startLocation();


            }

        });
        tbox1 = (EditText) findViewById(R.id.IDlatitude);
        //tbox1.setText("12345");
        tbox2 = (EditText) findViewById(R.id.IDlongtitude);
        //tbox2.setText("12345");
        nameLoc = (TextView) findViewById(R.id.IDname);
        nameLoc.setText("fdfsfdsafads");
        address = (TextView) findViewById(R.id.IDadrress);
        address.setText("fsdfds");
        dist = (TextView) findViewById(R.id.IDdistance);
        dist.setText("fdsfsd");
        phone = (TextView) findViewById(R.id.IDphone);
        phone.setText("sadsa");
        rating = (TextView) findViewById(R.id.IDrating);
        rating.setText("sdsasad");
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

    void sayHI(){
        nameLoc.setText("hIIIIIIIIIIIIIIIIII");


    }

    public void  doThing()
    {
        int i = 0;
        int j = 0;


        double currentlatitude = Double.parseDouble(tbox1.getText().toString());
        double currentlongitude = Double.parseDouble(tbox2.getText().toString());
        double dlon = currentlongitude-  shlist[i].getLongtitude();
        double dlat = currentlongitude-  shlist[i].getLattitude();
        double a = Math.sin(dlat/2)*Math.sin(dlat/2) + Math.cos(currentlatitude)*Math.cos(shlist[i].getLattitude())* (Math.sin(dlon/2))*(Math.sin(dlon/2));
        double c = 2 * Math.atan2( Math.sqrt(a), Math.sqrt(1-a) );
        double distance = 3959*c;
        double mindistance = distance;

        for(i=1;i<11;i++)
        {
            dlon = currentlongitude-  shlist[i].getLongtitude();
            dlat = currentlongitude-  shlist[i].getLattitude();
            a = Math.sin(dlat/2)*Math.sin(dlat/2) + Math.cos(currentlatitude)*Math.cos(shlist[i].getLattitude())* (Math.sin(dlon/2))*(Math.sin(dlon/2));
            c = 2 * Math.atan2( Math.sqrt(a), Math.sqrt(1-a) );
            distance = 3959*c;
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
}

class Shelter {
    private String name;
    private double lattitude;
    private double longtitude;
    private String address;
    private String phone;
    private double rating;

    // constructor
    public Shelter (String name, double lattitude, double longtitude, String address, String phone, double rating) {
        this.name = name;
        this.lattitude = lattitude;
        this.longtitude = longtitude;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    // getter
    public String getName() { return name; }
    public double getLattitude() { return lattitude; }
    public double getLongtitude() { return longtitude; }
    public String getAddress () {return address; }
    public String getPhone() {return phone;}
    public double getRating() {return rating;}
    // setter

    public void setName(String name) { this.name = name; }
    public void setLattitude(double lattitude) { this.lattitude = lattitude; }
    public void setLongtitude(double longtitude) {this.longtitude = longtitude; }
    public void setAddress (String address) {this.address = address;}
    public void setPhone (String phone) {this.phone = phone; }
    public void setRating (double rating) {this.rating = rating; }
}


// Register the listener with the Location Manager to receive location updates
