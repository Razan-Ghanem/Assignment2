package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String HEIGHT = "HEIGHT";
    public static final String WEIGHT = "WEIGHT";
    public static final String GENDER = "GENDER";
    public static final String FLAG = "FLAG";
    public boolean clicked = false ;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Spinner spinner;
    private EditText name;
    private EditText height;
    private EditText weight;
    private TextView txtview;
    private Button btnTimer ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spn);
        name = findViewById(R.id.edtname);
        height = findViewById(R.id.edtheight);
        weight = findViewById(R.id.edtweight);
        txtview = findViewById(R.id.txtview);
        btnTimer = (Button)findViewById(R.id.btnTimer);
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMainActivity2();
            }
        });

        setupViews();
        setupSharedPrefs();
        checkPrefs();

    }
    public void OpenMainActivity2(){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        MainActivity.this.startActivity(intent);
    }





    public void btnSaveOnClick(View view) {

        String edtn = name.getText().toString();
        String edth = height.getText().toString();
        String edtw = weight.getText().toString();
        String spngen = spinner.getSelectedItem().toString();
    if (clicked == true){
        editor.putString(NAME, edtn);
        editor.putString(HEIGHT, edth);
        editor.putString(WEIGHT, edtw);
        editor.putString(GENDER, spngen);
        editor.putBoolean(FLAG, true);
        editor.commit();
    }

    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupViews() {
        spinner = findViewById(R.id.spn);
        name = findViewById(R.id.edtname);
        height = findViewById(R.id.edtheight);
        weight = findViewById(R.id.edtweight);
    }

    private void checkPrefs() {
        boolean flag = prefs.getBoolean(FLAG, false);
        if (flag) {
            String n = prefs.getString(NAME, "");
            String h = prefs.getString(HEIGHT, "");
            String w = prefs.getString(WEIGHT, "");
//            String g = prefs.getString(GENDER, "");
            name.setText(n);
            height.setText(h);
            weight.setText(w);
            clicked = true ;
        }
    }


    public void btnBMIOnClick(View view) {
        double edth = Double.parseDouble(height.getText().toString());
        double edtw = Double.parseDouble(weight.getText().toString());
        double bmi = edtw / (edth * edth);
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(bmi);
        bmi = Double.valueOf(dx);
        txtview.setText("");
        String s = "Your BMI is -> ";
        if (bmi < 18.5) {
            String y = String.valueOf(bmi);
            txtview.append(s + y + " and it is Underweight :( ");
            txtview.showContextMenu();
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            String y = String.valueOf(bmi);
            txtview.append(s + y + " and it is Healthy Weight :D");
            txtview.showContextMenu();
        } else if (bmi >= 25 && bmi <= 29.9) {
            String y = String.valueOf(bmi);
            txtview.append(s + y + " and it is Overweight :(");
            txtview.showContextMenu();
        }
    }
}