package com.oakraw.indoorlog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.oakraw.indoorlog.adapter.CustomSpinnerAdapter;
import com.oakraw.indoorlog.adapter.ListAdapter;
import com.oakraw.indoorlog.database.UserDatabase;
import com.oakraw.indoorlog.model.DetailTimeNode;
import com.oakraw.indoorlog.model.Profile;

import java.util.ArrayList;
import java.util.Calendar;


public class ListActivity extends Activity {

    private int uid;
    private UserDatabase database;
    private String nodeDetail = "node_detail1";
    private String node = "node";
    private Spinner mSpinner;
    private ListView listView;
    private ArrayList<Profile> profiles;
    private ArrayList<DetailTimeNode> detailRecords;
    private ListAdapter listAdapter;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        uid = getIntent().getIntExtra("uid", 0);
        name = getIntent().getStringExtra("name");

        getActionBar().setTitle(name);

        database = new UserDatabase(this);
        profiles = database.getAllProfileRecords(uid);

        mSpinner = (Spinner) findViewById(R.id.spinner);
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this,
                android.R.layout.simple_dropdown_item_1line, profiles);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pid = profiles.get(position).getPid();
                /*detailRecords.clear();
                detailRecords = database.getAllDetailRecords(pid);
                listAdapter.notifyDataSetChanged();*/
                listView.setAdapter(null);
                ListAdapter listAdapter = new ListAdapter(ListActivity.this, database.getAllDetailRecords(pid));
                listView.setAdapter(listAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView = (ListView)findViewById(R.id.listView);


        if(profiles.size() != 0) {
            detailRecords = database.getAllDetailRecords(profiles.get(0).getPid());
            listAdapter = new ListAdapter(this, detailRecords);
            listView.setAdapter(listAdapter);
        }


    }

    private void addNode(){
        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        int min = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);

        String time = hour + ":" + min + ":" + seconds;
        String date = c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);

        int pid = database.addProfileRecord(uid, date);
        if(pid == -1){
            pid = database.getProfileRecord(uid, date).getPid();
        }
        Log.d("oakTag", "pid:"+pid);
        database.addNodeRecord(node, nodeDetail);
        database.addDetailProfileRecord(pid, time, node);

        Toast.makeText(getApplicationContext(), "Add Data", Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            addNode();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
