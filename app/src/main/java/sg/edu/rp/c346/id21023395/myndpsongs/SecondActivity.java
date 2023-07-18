package sg.edu.rp.c346.id21023395.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    Spinner spn;
    Button btnShow5Stars, btnBack;
    ListView lvSongs;
    ArrayList<Song> songList;
    ArrayList<String> yearSpinnerList;
    CustomAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        spn = findViewById(R.id.spinner);
        btnShow5Stars = findViewById(R.id.show5Stars);
        btnBack = findViewById(R.id.btnBack);
        lvSongs = findViewById(R.id.lv);

        // Display listView
        DBHelper db = new DBHelper(SecondActivity.this);
        songList = db.getSongs();
        db.close();
        aa = new CustomAdapter(SecondActivity.this,
                R.layout.row, songList);
        lvSongs.setAdapter(aa);

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = songList.get(position);
                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                i.putExtra("data", data);
                startActivity(i);

            }
        });

        //SPinner
        yearSpinnerList = new ArrayList<>();
        DBHelper Spinner = new DBHelper(SecondActivity.this);
        yearSpinnerList = Spinner.getYears(); // Method in DBHelper class to retrieve the unique years
        Spinner.close();
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, yearSpinnerList);
        spn.setAdapter(yearAdapter);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedYear = parent.getItemAtPosition(position).toString();
                if (selectedYear.equalsIgnoreCase("Select a Year")){
                    DBHelper dbh = new DBHelper(SecondActivity.this);
                    spn.setSelection(0);
                    songList.clear();
                    songList.addAll(dbh.getSongs());
                    aa.notifyDataSetChanged();
                } else{
                    int year = Integer.parseInt(selectedYear);
                    DBHelper dbh = new DBHelper(SecondActivity.this);
                    songList.clear();
                    songList.addAll(dbh.getSongsByYear(year));
                    aa.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnShow5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(SecondActivity.this);
                songList = db.getSongs5Stars(5);
                db.close();
                aa = new CustomAdapter(SecondActivity.this,
                        R.layout.row, songList);
                lvSongs.setAdapter(aa);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(SecondActivity.this);
        songList.clear();
        songList.addAll(dbh.getSongs());
        yearSpinnerList.clear();
        yearSpinnerList.addAll(dbh.getYears());
        aa.notifyDataSetChanged();
    }
}