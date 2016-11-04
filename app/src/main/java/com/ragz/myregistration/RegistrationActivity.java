package com.ragz.myregistration;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Random;

public class RegistrationActivity extends AppCompatActivity {

    private Button btnDate;
    private EditText etFullName, etEmail, etPhone, etDOB;
    private String name, email, phone, randPass;
    private static final String ALLOWED_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final int Dialog_Id = 0;
    private int bDate, bYear, bMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etFullName = (EditText) findViewById(R.id.etFullName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etDOB = (EditText) findViewById(R.id.etDOB);
        btnDate = (Button) findViewById(R.id.btnDate);

        final Calendar calendar = Calendar.getInstance();
        bYear = calendar.get(Calendar.YEAR);
        bMonth = calendar.get(Calendar.MONTH);
        bDate = calendar.get(Calendar.DAY_OF_MONTH);

        showCal();

    }


    public void showCal() {
        btnDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(Dialog_Id);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == Dialog_Id)
            return new DatePickerDialog(this, datePicker, bYear, bMonth, bDate);
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePicker =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    bYear = year;
                    bMonth = monthOfYear + 1;
                    bDate = dayOfMonth;
                    Toast.makeText(getBaseContext(), bDate + "/" + bMonth + "/" + bYear, Toast.LENGTH_SHORT).show();
                }
            };


    private static String generatePass(final int sizeOfRandomString) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for (int i = 0; i < sizeOfRandomString; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
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
        phone = etPhone.getText().toString();
        if ((Patterns.EMAIL_ADDRESS.matcher(email).matches()) &&
                (validateName(name)) && (validatePhone(phone))) {
            sendEmail();
            storeData();
        } else {
            Toast.makeText(getBaseContext(), "Not Sent", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendEmail() {
        try {
            randPass = generatePass(8);
            String to = email;
            String subject = "Successful Registration";
            String message = "Hello " + name + ",\nRegistration is Successful. The Password is " + randPass + ". Kindly use this to Login to your account";
            SendMail sendMail = new SendMail(this, to, subject, message);
            sendMail.execute();

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Email sending failed", Toast.LENGTH_SHORT).show();
        }

    }

    public void storeData() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("User_Id", email);
        editor.putString("Pass", randPass);
        editor.apply();
        Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_SHORT).show();
    }

}