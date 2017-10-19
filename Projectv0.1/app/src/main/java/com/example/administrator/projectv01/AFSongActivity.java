package com.example.administrator.projectv01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AFSongActivity extends AppCompatActivity
{
    public  static String [] datas = {"Blue_Sky", "공군가", "대한의_공군", "보라매의_꿈", "성난_독수리", "신념의_조인", "필승_공군"};
    private ArrayList<String> list = new ArrayList<>();
    private MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afsong);

        for(String str: datas) list.add(str);

        ListView listView = (ListView)findViewById(R.id.song_list);
        AFSongAdapter adapter = new AFSongAdapter(this, R.layout.song_listitem ,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String)parent.getAdapter().getItem(position);
                Log.d("TEST", name);
            }
        });
    }
}
