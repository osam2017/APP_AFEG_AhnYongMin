package com.example.administrator.projectv01;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

//공군 노래 모음을 표시할 탭에서 쓸 인텐트
public class AFSongAdapter extends ArrayAdapter<String>
{
    private Context context;
    private int layoutResourceId;
    private ArrayList<String> datas;

    public AFSongAdapter(Context context, int resource, ArrayList<String> datas) {
        super(context, resource, datas);
        this.context = context;
        this.layoutResourceId = resource;
        this.datas = datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row=inflater.inflate(layoutResourceId, parent, false);
        }

        TextView songName = (TextView) row.findViewById(R.id.song_name);
        final ImageButton playButton = (ImageButton)row.findViewById(R.id.playButton);

        //노래 이름을 지정
        songName.setText(datas.get(position).toString());

        //노래이름이 없다면 플레이버튼을 x 버튼으로 설정
        if(songName ==null)
        {
            playButton.setBackgroundResource(R.drawable.xbutton);
        }
        else {
            playButton.setBackgroundResource(R.drawable.playbutton);
        }

        return row;
    }
}
