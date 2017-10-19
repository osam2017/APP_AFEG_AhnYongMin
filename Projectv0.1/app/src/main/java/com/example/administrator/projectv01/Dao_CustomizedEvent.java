package com.example.administrator.projectv01;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-10-18.
 */

public class Dao_CustomizedEvent
{
    private Context context;
    private SQLiteDatabase db;

    public Dao_CustomizedEvent(Context context) {
        this.context = context;

        db = context.openOrCreateDatabase("OSAM.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        db.execSQL("DROP TABLE IF EXISTS event");
        try{
            //이 db의 이름은 'Event'이다.
            String sql = "CREATE TABLE IF NOT EXISTS event (eventName text not null," +
                    "                                       eventDate text not null," +
                    "                                       detail text not null);";

            db.execSQL(sql);

        }catch (Exception e)
        {
            Log.d("TEST", "테이블 생성 실패");
            e.printStackTrace();
        }
    }

    public void insertJsonData(String jsonData)
    {
        String eventName, date, details;

        try{
            JSONArray jArr = new JSONArray(jsonData);

            for(int i=0;i<jArr.length();i++)
            {
                JSONObject jObj = jArr.getJSONObject(i);
                eventName = jObj.getString("eventName");
                date = jObj.getString("eventDate");
                details = jObj.getString("detail");

                Log.i("TEST", "EVENT_NAME: " + eventName + " date: " + date + " detail: " + details);

                String sql = "INSERT INTO event(eventName, eventDate, detail)" +
                        "VALUES('" + eventName + "','" + date + "','" + details + "');";

                try{
                    //unique 값 관련된 오류 방지'
                    db.execSQL(sql);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }


        }catch (JSONException e)
        {
            Log.e("TEST", "JSON Error: " + e);
            e.printStackTrace();
        }
    }

    public String getJsonTestData()
    {
        //여기에서 Json 데이터를 불러와야 한다.
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("     {");
        sb.append("         'EVENT_NAME': 'event1', ");
        sb.append("         'DATE': '2017/9/6-12:30', ");
        sb.append("         'DETAILS': 'what' ");
        sb.append("      },");

        sb.append("     {");
        sb.append("         'EVENT_NAME': 'event2', ");
        sb.append("         'DATE': '2017/10/6-11:30', ");
        sb.append("         'DETAILS': 'who'");
        sb.append("      },");

        sb.append("     {");
        sb.append("         'EVENT_NAME': 'event3', ");
        sb.append("         'DATE': '2017/12/9-8:0', ");
        sb.append("         'DETAILS': 'why'");
        sb.append("      }");
        sb.append("]");

        return sb.toString();
    }

    public ArrayList<CustomizeEvent> getEventList()
    {
        Log.d("TEST", "함수로 진입함");
        ArrayList<CustomizeEvent> list = new ArrayList<>();

        String name, date, detail;
        String sql = "SELECT * FROM event;";
        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext())
        {
            name = cursor.getString(0);
            Log.d("TEST", "name: " + name);
            date = cursor.getString(1);
            detail = cursor.getString(2);

            list.add(new CustomizeEvent(name, date, detail));
        }
        Log.d("TEST", "함수 끝남");
        return list;
    }
}
