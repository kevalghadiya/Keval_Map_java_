package com.example.keval;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.keval.HotelDBMSModule.HotelDao;
import com.example.keval.HotelDBMSModule.HotelDataBase;
import com.example.keval.RoomDataBase.UserDao;
import com.example.keval.adapter.HotelListAdapter;
import com.example.keval.adapter.HotelMapListAdapter;
import com.example.keval.api.WebApiClient;
import com.example.keval.models.HotelModel;
import com.example.keval.models.HotelResponse;
import com.example.keval.models.LocationModel;
import com.example.keval.models.PlaceModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "MainActivity";
    private GoogleMap mMap;

    private ArrayList<HotelModel> hotelList = new ArrayList<>();
    private ArrayList<LatLng> locationArrayList = new ArrayList<>();

    HotelListAdapter adapter;
    HotelMapListAdapter mapAdapter;
//    AIzaSyDb_5NFQ96pWq7uANGrTOQ-eEmYCbpLxiI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout llList = (LinearLayout) findViewById(R.id.llList);
        LinearLayout llMap = (LinearLayout) findViewById(R.id.llMap);
        LinearLayout llMainList = (LinearLayout) findViewById(R.id.llMainList);
        RelativeLayout rlMainMap = (RelativeLayout) findViewById(R.id.rlMainMap);
        RecyclerView rvList = (RecyclerView) findViewById(R.id.rvList);
        RecyclerView rvMapList = (RecyclerView) findViewById(R.id.rvMapList);

        adapter = new HotelListAdapter(hotelList, new HotelListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick() {
                Toast.makeText(MainActivity.this, "On Click", Toast.LENGTH_SHORT).show();
            }
        });

        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);


        mapAdapter = new HotelMapListAdapter(hotelList, new HotelMapListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, HotelModel hotelModel) {
//                Toast.makeText(MainActivity.this, "On Click", Toast.LENGTH_SHORT).show();
                if(mMap != null){
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(position)));
                }
            }
        });
        rvMapList.setHasFixedSize(true);
        rvMapList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvMapList.setAdapter(mapAdapter);

        llList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llList.setBackgroundResource(R.drawable.rec_bg);
                llMap.setBackgroundResource(R.drawable.rec_bg_white);

                llMainList.setVisibility(View.VISIBLE);
                rlMainMap.setVisibility(View.GONE);
            }
        });

        llMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llList.setBackgroundResource(R.drawable.rec_bg_white);
                llMap.setBackgroundResource(R.drawable.rec_bg);

                llMainList.setVisibility(View.GONE);
                rlMainMap.setVisibility(View.VISIBLE);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);

        getHotelList();
       // getDBMSData();
    }

    private void getDBMSData() {
        HotelDataBase db= Room.databaseBuilder(getApplicationContext(),HotelDataBase.class,"RV DATA").allowMainThreadQueries().build();
        HotelDao hotelDao =db.hotelDao();

        for (int i = 0; i <hotelList.size() ; i++) {
            hotelDao.insertAllData(new PlaceModel( hotelList.get(i).getPlace().getName(),
                    hotelList.get(i).getPlace().getName_suffix(),
                    hotelList.get(i).getPlace().getStar_rating(),
                    hotelList.get(i).getPlace().getThumbnail_url(),
                    hotelList.get(i).getPlace().getMarker()));

        }
       /* hotelDao.insertAllData(new PlaceModel(
                hotelList.get(0).getPlace().getName(),
                hotelList.get(0).getPlace().getName_suffix(),
                hotelList.get(0).getPlace().getStar_rating(),
                hotelList.get(0).getPlace().getThumbnail_url(),
                hotelList.get(0).getPlace().getMarker())
        );*/

     /*   hotelDao.insertAllData(new PlaceModel(
                hotelList.get(0).getPlace().getName(),
                hotelList.get(0).getPlace().getName_suffix(),
                hotelList.get(0).getPlace().getStar_rating(),
                hotelList.get(0).getPlace().getThumbnail_url(),
                hotelList.get(0).getPlace().getMarker(),
                hotelList.get(0).getPlace().getLocation()));
*/
    }

    private void getHotelList() {
//
//         https://api.sygictraveldata.com/v2.5/en/hotels/list/?adults=2
//         &check_in=2022-12-25&
//         check_out=2022-12-26&
//         currency=USD&
//         limit=50&
//         bounds=22.965515078282593,72.51908888477644,23.077732321717406,72.64032471522356
        HashMap<String, String> param = new HashMap<>();
        param.put("adults", "2");
        param.put("check_in", "2023-01-01");
        param.put("check_out", "2023-01-02");
        param.put("currency", "USD");
        param.put("limit", "50");
        param.put("bounds", "22.965515078282593,72.51908888477644,23.077732321717406,72.64032471522356");

        WebApiClient.getInstance().getHotelList(param).enqueue(new Callback<HotelResponse>() {
            @Override
            public void onResponse(Call<HotelResponse> call, Response<HotelResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    HotelResponse hotelResponse = response.body();
                    if (hotelResponse.getStatus_code() == 200) {
                        hotelList.addAll(hotelResponse.getData().getHotels());
                        adapter.notifyDataSetChanged();
                        mapAdapter.notifyDataSetChanged();

                        for (int i = 0; i < hotelList.size(); i++) {
                          //  LocationModel locationModel = hotelList.get(i).getPlace().getLocation();
                          //  locationArrayList.add(new LatLng(locationModel.getLat(), locationModel.getLng()));

                           /* if (mMap != null) {
                                mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title(hotelList.get(i).getPlace().getName()));
//                                mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
                            }
*/                        }

                        if (mMap != null && !locationArrayList.isEmpty()) {
                            mMap.animateCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(0)));
                        }

                    } else
                        Toast.makeText(MainActivity.this, "Something want wrong", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Something want wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<HotelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14.0f));
    }
}
