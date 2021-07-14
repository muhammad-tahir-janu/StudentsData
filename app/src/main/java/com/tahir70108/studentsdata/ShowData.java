package com.tahir70108.studentsdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShowData extends AppCompatActivity {
        TextView tvShowData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        tvShowData = findViewById(R.id.tvShowData);

        StudentsDB studentsDB = new StudentsDB(this);
        studentsDB.open();
        tvShowData.setText(studentsDB.getData());
        studentsDB.close();


    }


}