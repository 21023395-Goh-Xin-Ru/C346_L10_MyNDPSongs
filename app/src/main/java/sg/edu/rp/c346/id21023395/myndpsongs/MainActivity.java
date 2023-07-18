package sg.edu.rp.c346.id21023395.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYearRelease;
    RadioGroup rgStars;
    Button btnInsert, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.title);
        etSingers = findViewById(R.id.singer);
        etYearRelease = findViewById(R.id.year);
        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.button);
        btnShow = findViewById(R.id.button2);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                int year = Integer.parseInt(etYearRelease.getText().toString());
                int stars = getStars();
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(title, singers, year, stars);
                db.close();
                Toast.makeText(MainActivity.this, "Song inserted successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }

    private int getStars() {
        int stars = 0;
        if(rgStars.getCheckedRadioButtonId() == R.id.oneStar){
            stars = 1;
        } else if(rgStars.getCheckedRadioButtonId() == R.id.twoStars){
            stars = 2;
        } else if(rgStars.getCheckedRadioButtonId() == R.id.threeStar) {
            stars = 3;
        } else if(rgStars.getCheckedRadioButtonId() == R.id.fourStars){
            stars = 4;
        } else if(rgStars.getCheckedRadioButtonId() == R.id.fiveStars) {
            stars = 5;
        }
        return stars;
    }
}