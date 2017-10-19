package com.example.administrator.projectv01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ChatroomActivity extends AppCompatActivity implements View.OnClickListener
{
    private ArrayList<UserInfo> list;
    private Dao_UserInfo dao;
    String rank, name, num;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        new Thread(){
            public void run(){
                ProxyUserInfo proxy = new ProxyUserInfo();
                String test = proxy.getJSON();
                dao = new Dao_UserInfo(getApplicationContext());
                dao.insertJsonData(test);

                //이건 나중에..
                //getIntentInfo();

                listView();
            }
        }.start();

        //채팅방 새로 만들기 버튼
        Button newButton = (Button)findViewById(R.id.newButton);
        newButton.setOnClickListener(this);
    }

    /*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Log.i("TEST", position + "번 리스트 선택");
        //리스트의 해당 채팅방에 입장할 수 있도록 한다.
    }
    */

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.newButton:
                //다이얼로그를 만들어서 채팅방 제목만 입력하면 새로운 챗방을 만들도록 한다.
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://10.53.128.134:5030"));
                startActivity(intent);
                break;
            default:
                Log.d("TEST", "잘못된 접근");
                return;
        }
    }
    
    public void getIntentInfo()
    {
        //계급, 성명, 군번을 전달받았다면 인텐트가 된다.
        Intent intent = getIntent();
        rank = intent.getStringExtra("계급");
        name = intent.getStringExtra("성명");
        num = intent.getStringExtra("군번");
        Log.d("TEST", "인텐트에서 받아온 값:" + rank + ", " + name + ", " + num);

        //받아온 값을 proxy를 통해 db에 저장한다.
        if(rank != null)
        {
            String data = "[{ 'rank': '" + rank + "', 'userName': '" + name + "', 'num': '" + num + "'}]";
            dao.insertJsonData(data);
        }
    }

    public void listView()
    {
        list = dao.getEventList();
        ListView listView = (ListView)findViewById(R.id.chatroomView);

        ChatroomAdapter adapter = new ChatroomAdapter(this, R.layout.chatroom_layout, list);
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);
    }
}
