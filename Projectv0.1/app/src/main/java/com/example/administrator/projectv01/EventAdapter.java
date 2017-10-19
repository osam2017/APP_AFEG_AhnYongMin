package com.example.administrator.projectv01;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends ArrayAdapter<CustomizeEvent>
{
    private Context context;
    private int layoutResourceId;
    private ArrayList<CustomizeEvent> list;

    public EventAdapter(Context context, int layoutResourceId, ArrayList<CustomizeEvent> list)
    {
        super(context, layoutResourceId, list);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        TextView name = (TextView)row.findViewById(R.id.eventView);
        TextView date = (TextView)row.findViewById(R.id.dateView);
        TextView detail = (TextView)row.findViewById(R.id.detail);

        name.setText(list.get(position).getEventName());
        date.setText(list.get(position).getDate());
        detail.setText(list.get(position).getDetail());

        return row;
    }
}
