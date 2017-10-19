package com.example.administrator.projectv01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //행사 모음 DB에서 오늘 일자를 기준으로 nameView, detailView 에 표시를 한다.
        TextView txt1 = (TextView)findViewById(R.id.nameView);
        TextView txt2 = (TextView)findViewById(R.id.detailView);
        txt2.setText("오늘은 행사가 없습니다.");

        Button songButton = (Button)findViewById(R.id.songButton);
        songButton.setOnClickListener(this);

        Button eventButton = (Button)findViewById(R.id.eventButton);
        eventButton.setOnClickListener(this);

        Button chatButton = (Button)findViewById(R.id.chatButton);
        chatButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.songButton:
                Intent intent1 = new Intent(this, AFSongActivity.class);
                startActivity(intent1);
                break;
            case R.id.eventButton:
                Intent intent2 = new Intent(this, EventActivity.class);
                startActivity(intent2);
                break;
            case R.id.chatButton:
                Intent intent3 = new Intent(this, ChatActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
