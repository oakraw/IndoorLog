package com.oakraw.indoorlog.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oakraw.indoorlog.R;
import com.oakraw.indoorlog.model.DetailTimeNode;
import com.oakraw.indoorlog.model.User;

import java.util.List;

/**
 * Created by Rawipol on 3/21/15 AD.
 */
public class AdminListAdapter extends BaseAdapter {

    private List<User> users;
    private Context mContext;

    public AdminListAdapter(Context mContext, List<User> users) {
        this.mContext = mContext;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        View row;
        row = inflater.inflate(R.layout.list_all_user, parent, false);

        TextView username = (TextView)row.findViewById(R.id.username);


        username.setText(users.get(position).getUsername());

        return row;
    }
}
