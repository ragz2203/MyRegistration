package com.ragz.myregistration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etLoginUserID, etLoginPassword;

    String Stored_ID, Stored_Pass;

    private String LogId, LogPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginUserID = (EditText) findViewById(R.id.etLoginUserID);
        etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);

    }

    public void checkData() {
        SharedPreferences preferences = getSharedPreferences("userInfo", RegistrationActivity.MODE_PRIVATE);
        Stored_ID = preferences.getString("User_Id", "");
        Stored_Pass = preferences.getString("Pass", "");
    }

    public void toaster(){
        Toast.makeText(getBaseContext(), "hey " + Stored_ID + " " + Stored_Pass, Toast.LENGTH_SHORT).show();
    }

    private boolean logValidate() {
        LogId = etLoginUserID.getText().toString();
        LogPass = etLoginPassword.getText().toString();

        checkData();

        if (LogId.isEmpty() || LogPass.isEmpty()) {
            Toast.makeText(getBaseContext(), "Enter the Details", Toast.LENGTH_SHORT).show();
        } else {

            if (LogId.equals(Stored_ID)) {
                if (LogPass.equals(Stored_Pass)) {
                    toaster();
                    return true;
                } else {
                    Toast.makeText(getBaseContext(), "Mismatch Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getBaseContext(), "Mismatch name", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    public void userLogin(View view) {
        if (logValidate()) {
            startActivity(new Intent(this, ResultActivity.class));
        }
    }

    public void gotoSignUp(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

}
