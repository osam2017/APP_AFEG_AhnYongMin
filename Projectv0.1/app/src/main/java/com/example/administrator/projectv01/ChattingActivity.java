package com.example.administrator.projectv01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ChattingActivity extends AppCompatActivity {
    private ListView listView;
    private ChattingAdapter m_adapter;
    private ArrayList<ListContents> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        listData = new ArrayList<ListContents>();

        listData.add(new ListContents("이건 뭐지",1));
        listData.add(new ListContents("쿨쿨",1));
        listData.add(new ListContents("쿨쿨쿨쿨",0));
        listData.add(new ListContents("재미있게",1));
        listData.add(new ListContents("놀자라구나힐힐 감사합니다. 동해물과 백두산이 마르고 닳도록 놀자 놀자 우리 놀자",1));
        listData.add(new ListContents("재미있게",1));
        listData.add(new ListContents("재미있게",0));
        listData.add(new ListContents("2015/11/20",2));
        listData.add(new ListContents("재미있게",1));
        listData.add(new ListContents("재미있게",1));

        m_adapter = new ChattingAdapter(this, R.layout.chatting_layout, listData);
        listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(m_adapter);

        Button sendButton = (Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });;
    }
}
