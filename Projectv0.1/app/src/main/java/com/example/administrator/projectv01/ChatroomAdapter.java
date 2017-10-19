package com.example.administrator.projectv01;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatroomAdapter extends ArrayAdapter<UserInfo>
{
    private Context context;
    private int layoutResourceId;
    private ArrayList<UserInfo> list;

    public ChatroomAdapter(Context context, int layoutResourceId, ArrayList<UserInfo> list)
    {
        super(context, layoutResourceId, list);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Log.d("TEST", "겟뷰로 진입은 했다.");
        View row = convertView;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        TextView titleText = (TextView)row.findViewById(R.id.titleText);
        TextView nameText = (TextView)row.findViewById(R.id.nameText);

        titleText.setText(list.get(position).getRank() + " - " +list.get(position).getNum());
        nameText.setText(list.get(position).getName());

        ImageView imageView = (ImageView)row.findViewById(R.id.picture);
        imageView.setBackgroundResource(R.drawable.user);

        return row;
    }
}
