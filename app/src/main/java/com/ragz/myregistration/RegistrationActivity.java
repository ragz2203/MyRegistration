package com.ragz.myregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private Button btnSubmit;
    private EditText etFullName,etEmail,etPass1,etPass2,etPhone,etDOB;
    private String name,email,pass1,pass2,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        etFullName = (EditText) findViewById(R.id.etFullName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass1 = (EditText) findViewById(R.id.etPass1);
        etPass2 = (EditText) findViewById(R.id.etPass2);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etDOB = (EditText) findViewById(R.id.etDOB);

    }

    public boolean validateName(String name) {
        return name.matches("[A-Z][a-zA-Z A-z]*");
    }

    public boolean validatePhone(String phone) {
        return phone.matches("[0-9]{10}");
    }

    public void submit(View view) {
        name = etFullName.getText().toString();
        email = etEmail.getText().toString();
        pass1 = etPass1.getText().toString();
        pass2 = etPass2.getText().toString();
        phone = etPhone.getText().toString();

        if (validateName(name)) {
            Toast.makeText(getBaseContext(), "Valid Name", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getBaseContext(), "Name Not valid", Toast.LENGTH_SHORT).show();
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getBaseContext(), "valid email", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getBaseContext(), "Email Not valid", Toast.LENGTH_SHORT).show();
        }
        if ((pass1.length()>=6)&&(pass2.length()>=6)) {
            if (pass1.equals(pass2)) {
                Toast.makeText(getBaseContext(), "password validated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "password don't match", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "password must contain min 6 characters", Toast.LENGTH_SHORT).show();
        }
        if (validatePhone(phone)) {
            Toast.makeText(getBaseContext(), "Phone Number is Valid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), "Number not Valid", Toast.LENGTH_SHORT).show();
        }

    }
}
