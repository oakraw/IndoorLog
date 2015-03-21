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

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Rawipol on 3/21/15 AD.
 */
public class ListAdapter extends BaseAdapter {

    private List<DetailTimeNode> timeNodeList;
    private Context mContext;

    public ListAdapter(Context mContext, List<DetailTimeNode> timeNodeList) {
        this.mContext = mContext;
        this.timeNodeList = timeNodeList;
    }

    @Override
    public int getCount() {
        return timeNodeList.size();
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
        row = inflater.inflate(R.layout.list_detail, parent, false);

        TextView time = (TextView)row.findViewById(R.id.time);
        TextView nid = (TextView)row.findViewById(R.id.nid);
        TextView node = (TextView)row.findViewById(R.id.node);

        time.setText(timeNodeList.get(position).getTime());
        nid.setText(timeNodeList.get(position).getNid());
        node.setText(timeNodeList.get(position).getNode());

        return row;
    }
}
