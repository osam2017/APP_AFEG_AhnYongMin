package com.example.administrator.projectv01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<CustomizeEvent> list;
    private Dao_CustomizedEvent dao;
    String name, date, detail;

    //정규표현식을 도입할 순 없는 걸까?
    //String test = "\w.\w.\w.\w.\w";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        new Thread(){
            public void run(){
                ProxyEvent proxy = new ProxyEvent();
                String test = proxy.getJSON();
                dao = new Dao_CustomizedEvent(getApplicationContext());
                dao.insertJsonData(test);
                getIntentInfo();

                listView();
            }
        }.start();

        //새로 만들기 버튼
        Button customizeButton = (Button)findViewById(R.id.customizeButton);
        customizeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.customizeButton:
                //새로 만들기 버튼을 눌렀을 때 반응하는 것
                Intent intent = new Intent(this, CustomizeEventActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                Log.d("d", "잘못된 접근");
                return;
        }
    }

    private void getIntentInfo()
    {
        Intent intent = getIntent();
        name = intent.getStringExtra("행사명");
        date = intent.getStringExtra("시간");
        detail = intent.getStringExtra("세부내용");
        Log.d("TEST", "인텐트에서 받아온 값:" + name + ", " + date + ", " + detail);

        //이제 받은 데이터를 보내서 proxy를 통해 db에 저장해야 한다.
        if(name != null)
        {
            String data = "[{ 'eventName': '" + name + "', 'eventDate': '" + date + "', 'detail': '" + detail + "'}]";
            dao.insertJsonData(data);
        }
    }

    public void listView()
    {
        list = dao.getEventList();
        ListView listView = (ListView)findViewById(R.id.event_view);

        //이제 CustomizeEvent 배열 객체를 만들어 db에서 끌어온 걸 모두 집어넣고, 어댑터를 조작한다.
        EventAdapter adapter = new EventAdapter(this, R.layout.event_layout, list);
        listView.setAdapter(adapter);
    }
}
