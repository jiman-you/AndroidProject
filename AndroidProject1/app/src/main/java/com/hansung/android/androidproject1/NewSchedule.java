package com.hansung.android.androidproject1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hansung.android.androidproject1.databinding.ActivityNewScheduleBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class NewSchedule extends AppCompatActivity implements OnMapReadyCallback {
    public ActivityNewScheduleBinding binding;// 레이아웃 내 값이 많아 바인딩 사용
    private FusedLocationProviderClient mFusedLocationClient;
    GoogleMap mGoogleMap = null;
    private Geocoder geocoder;
    private DBHelper dbHelper;
    private GoogleMap GoogleMap = null;
    EditText mId;
    EditText mTitle;
    EditText mshour;
    EditText msMin;
    EditText msMeridiem;
    EditText meHour;
    EditText meMin;
    EditText meMeridim;
    EditText mPlace;
    EditText mMemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewScheduleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //전달받은 값 가져오기
        Intent intent = getIntent();
        EditText date = findViewById(R.id.TitleText);
        date.setText(intent.getStringExtra("date"));//선택된 연월시분
        EditText location = findViewById(R.id.Location);
        EditText usertext = findViewById(R.id.UserText);
        int year = intent.getIntExtra("year", 0);
        int month = intent.getIntExtra("month", 0);
        int day = intent.getIntExtra("day", 0);
        int hour = intent.getIntExtra("hour", 0);
        int minute = intent.getIntExtra("minute", 0);


        SQLiteDatabase db;

        //시작,끝나는 시간 설정
        NumberPicker start1 = findViewById(R.id.snum1);//시
        NumberPicker start2 = findViewById(R.id.snum2);//분
        NumberPicker start3 = findViewById(R.id.snum3);//ampm
        NumberPicker end1 = findViewById(R.id.enum1);
        NumberPicker end2 = findViewById(R.id.enum2);
        NumberPicker end3 = findViewById(R.id.enum3);
        String ampm[] = {"AM","PM"};
        start1.setMinValue(1); start1.setMaxValue(12);//1~12시
        start2.setMaxValue(59);//0~59분
        start3.setMaxValue(1);start3.setDisplayedValues(ampm);//0=am 1=pm
        end1.setMinValue(1); end1.setMaxValue(12);
        end2.setMaxValue(59);
        end3.setMaxValue(1);end3.setDisplayedValues(ampm);

        //시작시간
        int data_st = intent.getIntExtra("startTime",8);
        start1.setValue(data_st%12); end1.setValue((data_st+1)%12);

        //SQLite 이용
        dbHelper = new DBHelper(this);
        Button save_btn = findViewById(R.id.Up);
        //업로드
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewSchedule.this,"저장한다", Toast.LENGTH_SHORT).show();
                dbHelper.insertMemoBySQL(//업로드
                        date.getText().toString(),
                        start1.getValue(), start2.getValue(), start3.getValue(),
                        end1.getValue(), end2.getValue(), end3.getValue(),
                        location.getText().toString(),
                        usertext.getText().toString(),
                        year,
                        month,
                        day
                );
                //텍스트 뷰로 표현 리스트뷰 실패
                viewAllToTextView();
               // viewAllToListView();
            }
        });

        //취소버튼 뒤로 돌아가기
        Button cancel_btn = findViewById(R.id.Can);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //취소 버튼 눌리면 액티비티 종료
                finish();
            }
        });

        //삭제시 다이얼 로그
        AlertDialog.Builder dlg = new AlertDialog.Builder(NewSchedule.this);
        dlg.setMessage("정말 삭제하시겠습니까?");
        dlg.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteMemoBySQL(year, month, day);//삭제 누르면 삭제실행
              viewAllToTextView();
               // viewAllToListView();
            }
        });
        dlg.setPositiveButton("취소",null);

        Button delete_btn = findViewById(R.id.del);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.show();
            }
        });

        //지도 이용
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button map_btn = findViewById(R.id.Findbtn);
        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

    }

    //텍스트뷰로 보여주기
    private void viewAllToTextView() {
        TextView result = (TextView)findViewById(R.id.result);

        Cursor cursor = dbHelper.getAllMemosBySQL();

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append(cursor.getInt(0)+" \t");
            buffer.append(cursor.getString(1)+" \t");
            buffer.append(cursor.getString(2)+" 시\t");
            buffer.append(cursor.getString(3)+" 분\t");
            buffer.append(cursor.getString(4)+" 부터\t");
            buffer.append(cursor.getString(5)+" 시\t");
            buffer.append(cursor.getString(6)+" 분\t");
            buffer.append(cursor.getString(7)+" 까지\t장소");
            buffer.append(cursor.getString(8)+" \t메모");
            buffer.append(cursor.getString(9)+" \t실행일");
            buffer.append(cursor.getString(10)+" 년\t");
            buffer.append(cursor.getString(11)+" 월\t");
            buffer.append(cursor.getString(12)+" 일\n");
        }
        result.setText(buffer);
    }
//    리스트뷰로 보여주기
    private void viewAllToListView() {

        Cursor cursor = dbHelper.getAllUsersByMethod();

                SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                        R.layout.item, cursor, new String[]{
                        DBContract.Memos._ID,
                        DBContract.Memos.KEY_TITLE,
                        DBContract.Memos.KEY_S_HOUR,
                        DBContract.Memos.KEY_S_MIN,
                        DBContract.Memos.KEY_S_MERIDIEM,
                        DBContract.Memos.KEY_E_HOUR,
                        DBContract.Memos.KEY_E_MIN,
                        DBContract.Memos.KEY_E_MERIDIEM,
                        DBContract.Memos.KEY_PLACE,
                        DBContract.Memos.KEY_MEMO},
                        new int[]{R.id._id, R.id._title, R.id._sHour, R.id._sMin, R.id._sMeridiem, R.id._eHour, R.id._eMin,
                                R.id._eMeridiem, R.id._place, R.id._memo}, 0);

                ListView lv = (ListView) findViewById(R.id.listview);
                lv.setAdapter(adapter);

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Adapter adapter = adapterView.getAdapter();
//
//                mId.setText(((Cursor)adapter.getItem(i)).getString(0));
//                mTitle.setText(((Cursor)adapter.getItem(i)).getString(1));
//                mshour.setText(((Cursor)adapter.getItem(i)).getString(2));
//                msMin.setText(((Cursor)adapter.getItem(i)).getString(3));
//                msMeridiem.setText(((Cursor)adapter.getItem(i)).getString(4));
//                meHour.setText(((Cursor)adapter.getItem(i)).getString(5));
//                meMin.setText(((Cursor)adapter.getItem(i)).getString(6));
//                meMeridim.setText(((Cursor)adapter.getItem(i)).getString(7));
//                mPlace.setText(((Cursor)adapter.getItem(i)).getString(8));
//                mMemo.setText(((Cursor)adapter.getItem(i)).getString(9));
//            }
//        });
//        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }




    //지도관련
    private void getLocation() {
        EditText map_text = findViewById(R.id.Location);
        String search_address = map_text.getText().toString();
        try {
            List<Address> addresses = geocoder.getFromLocationName(search_address,5);
            if (addresses.size() >0) {
                Address bestResult = (Address) addresses.get(0);
                LatLng location = new LatLng(bestResult.getLatitude(),bestResult.getLongitude());
                mGoogleMap.addMarker(new MarkerOptions().
                        position(location).
                        title("검색 위치").
                        alpha(0.8f));
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));
            }
        } catch (IOException e) {
            Log.e(getClass().toString(),"Failed in using Geocoder.", e);
            return;
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        geocoder = new Geocoder(this, Locale.KOREA);
        //한국기준 Locale 설정

        //카메라 움직임
        mGoogleMap.setOnMarkerClickListener(new MyMarkerClickListener());
    }
    //마커표기
    class MyMarkerClickListener implements GoogleMap.OnMarkerClickListener {
        @Override
        public boolean onMarkerClick(Marker marker) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(),15));
            return false;
        }
    }
}


