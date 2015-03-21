package com.oakraw.indoorlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oakraw.indoorlog.database.UserDatabase;
import com.oakraw.indoorlog.model.User;


public class MainActivity extends Activity implements View.OnClickListener{

    private Button registerBtn;
    private Button loginBtn;
    private EditText username;
    private EditText password;
    private UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerBtn = (Button)findViewById(R.id.registerBtn);
        loginBtn = (Button)findViewById(R.id.loginBtn);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        db = new UserDatabase(this);



    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.registerBtn:
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    intent = new Intent(MainActivity.this, AdminActivity.class);
                    startActivity(intent);
                }else {
                    String pass = password.getText().toString();

                    User record = db.getUserRecord(username.getText().toString());
                    if (record == null) {
                        Toast.makeText(this, "Login fail", Toast.LENGTH_LONG).show();
                    } else {
                        if (pass.equals(record.getPassword())) {
                            intent = new Intent(MainActivity.this, ListActivity.class);
                            intent.putExtra("uid", record.getId());
                            intent.putExtra("name", record.getName());
                            startActivity(intent);
                        } else {
                            Toast.makeText(this, "Login fail", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        }

    }
}
