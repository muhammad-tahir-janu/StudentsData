package com.tahir70108.studentsdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName,etEmail,etPhoneNumber,etID;
    Button btnSubmit,btnShowData,btnEditData,btnDeleteData;
    StudentsDB studentsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String id = etID.getText().toString().trim();
                String phone = etPhoneNumber.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                studentsDB.open();
                studentsDB.createEntry(name,id,phone,email);
                studentsDB.close();
                Toast.makeText(getApplicationContext(), "Student Added", Toast.LENGTH_SHORT).show();
                clear();
            }
        });
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShowData.class);
                startActivity(intent);
            }
        });


    }
    private void initial() {
        etName = findViewById(R.id.etName);
        etID = findViewById(R.id.etID);
        etEmail = findViewById(R.id.etMail);
        etPhoneNumber = findViewById(R.id.etPhone);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnShowData = findViewById(R.id.btnShowData);
        btnEditData = findViewById(R.id.btnEdit);
        btnDeleteData = findViewById(R.id.btnDelete);
        studentsDB = new StudentsDB(this);

    }
    public void clear(){
        etName.setText("");
        etID.setText("");
        etPhoneNumber.setText("");
        etEmail.setText("");
    }
}