package com.oakraw.indoorlog.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.oakraw.indoorlog.R;
import com.oakraw.indoorlog.model.Profile;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Rawipol on 3/21/15 AD.
 */
public class CustomSpinnerAdapter extends ArrayAdapter<Profile> {

    List<Profile> profiles;
    public CustomSpinnerAdapter(Context context, int resource, List<Profile> objects) {
        super(context, resource, objects);
        profiles = objects;

    }



    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        TextView row = new TextView(getContext());
        row.setText(profiles.get(position).getDate());
        row.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getContext().getResources().getDimension(R.dimen.result_font));
        int dimen = (int)getContext().getResources().getDimension(R.dimen.activity_horizontal_margin);
        row.setPadding(dimen,dimen,dimen,dimen);

        return row;
    }
}
