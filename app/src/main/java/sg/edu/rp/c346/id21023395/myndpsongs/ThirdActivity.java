package sg.edu.rp.c346.id21023395.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    EditText id, title, singers, year;
    RadioGroup rgStars;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        id = findViewById(R.id.etID);
        title = findViewById(R.id.etST);
        singers = findViewById(R.id.etSN);
        year = findViewById(R.id.etYr);
        rgStars = findViewById(R.id.rgStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        rb1 = findViewById(R.id.oneStar);
        rb2 = findViewById(R.id.twoStars);
        rb3 = findViewById(R.id.threeStar);
        rb4 = findViewById(R.id.fourStars);
        rb5 = findViewById(R.id.fiveStars);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        id.setText(String.valueOf(data.getId()));
        id.setKeyListener(null);
        id.setTextColor(Color.GRAY);
        title.setText(data.getTitle());
        singers.setText(data.getSingers());
        year.setText(String.valueOf(data.getYear()));
        int starsNum = data.getStars();
        switch (starsNum){
            case 1:
                rb1.setChecked(true);
                break;
            case 2:
                rb2.setChecked(true);
                break;
            case 3:
                rb3.setChecked(true);
                break;
            case 4:
                rb4.setChecked(true);
                break;
            case 5:
                rb5.setChecked(true);
                break;
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setTitle(title.getText().toString());
                data.setSingers(singers.getText().toString());
                data.setYear(Integer.parseInt(year.getText().toString()));
                int selectedStars = rgStars.getCheckedRadioButtonId();  // get selected Radiobutton's ID
                RadioButton rbSelected = findViewById(selectedStars);  // Retrieve the actual RadioButton
                int stars = Integer.parseInt(rbSelected.getText().toString()); // Convert text value of selected radioButton into int
                data.setStars(stars);

                dbh.updateSong(data);
                dbh.close();
                finish(); // Destroys Current activity
                Toast.makeText(ThirdActivity.this, "Song updated successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.getId());
                finish(); // Destroys Current activity
                Toast.makeText(ThirdActivity.this, "Song deleted successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Destroys Current activity
            }
        });
    }
}