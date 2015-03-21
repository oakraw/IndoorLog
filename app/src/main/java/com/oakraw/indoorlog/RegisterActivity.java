package com.oakraw.indoorlog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.oakraw.indoorlog.database.UserDatabase;


public class RegisterActivity extends Activity {

    private EditText name;
    private EditText surname;
    private EditText username;
    private EditText password;
    private EditText repassword;
    private UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         name = (EditText)findViewById(R.id.name);
         surname = (EditText)findViewById(R.id.surname);
         username = (EditText)findViewById(R.id.username);
         password = (EditText)findViewById(R.id.password);
         repassword = (EditText)findViewById(R.id.repassword);

        db = new UserDatabase(this);

        findViewById(R.id.submitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(repassword.getText().toString())) {
                    int result = db.addUserRecord(name.getText().toString(),
                            surname.getText().toString(),
                            username.getText().toString(),
                            password.getText().toString());
                    if (result == -1) {
                        Toast.makeText(getApplicationContext(), "Username already exist", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();

            }
        });
    }

}
