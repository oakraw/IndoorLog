package com.oakraw.indoorlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.oakraw.indoorlog.adapter.AdminListAdapter;
import com.oakraw.indoorlog.database.UserDatabase;
import com.oakraw.indoorlog.model.User;

import java.util.ArrayList;


public class AdminActivity extends Activity {


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = (ListView) findViewById(R.id.listView);
        UserDatabase database = new UserDatabase(this);
        final ArrayList<User> allUsers = database.getAllUserRecords();

        AdminListAdapter adminListAdapter = new AdminListAdapter(this, allUsers);
        listView.setAdapter(adminListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminActivity.this, ListActivity.class);
                intent.putExtra("uid", allUsers.get(position).getId());
                intent.putExtra("name", allUsers.get(position).getName());
                startActivity(intent);
            }
        });
    }

}
