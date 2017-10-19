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

public class Dao_UserInfo
{
    private Context context;
    private SQLiteDatabase db;

    public Dao_UserInfo(Context context) {
        this.context = context;

        db = context.openOrCreateDatabase("OSAM.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        //실험용으로 있는거니 나중에 지우셈
        db.execSQL("DROP TABLE IF EXISTS userinfo");
        try{
            //이 db의 이름은 'info'이다.
            String sql = "CREATE TABLE IF NOT EXISTS userinfo(rank text not null," +
                    "                                     userName text not null," +
                    "                                     num text not null);";

            db.execSQL(sql);

        }catch (Exception e)
        {
            Log.d("TEST", "테이블 생성 실패");
            e.printStackTrace();
        }
    }

    public void insertJsonData(String jsonData)
    {
        String rank, name, num;

        try{
            JSONArray jArr = new JSONArray(jsonData);

            for(int i=0;i<jArr.length();i++)
            {
                JSONObject jObj = jArr.getJSONObject(i);
                rank = jObj.getString("rank");
                name = jObj.getString("userName");
                num = jObj.getString("num");

                Log.i("TEST", "rank: " + rank + " userName: " + name + " num: " + num);

                String sql = "INSERT INTO userinfo(rank, userName, num)" +
                        "VALUES('" + rank + "','" + name + "','" + num + "');";

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
        sb.append("         'Class': '병장', ");
        sb.append("         'Name': '안영민', ");
        sb.append("         'Num': 'what' ");
        sb.append("      },");

        sb.append("     {");
        sb.append("         'Class': '상사', ");
        sb.append("         'Name': '김지우', ");
        sb.append("         'Num': 'who'");
        sb.append("      },");

        sb.append("     {");
        sb.append("         'Class': '대위', ");
        sb.append("         'Name': '정가희', ");
        sb.append("         'Num': 'why'");
        sb.append("      }");
        sb.append("]");

        return sb.toString();
    }

    public ArrayList<UserInfo> getEventList()
    {
        //데이터를 그냥 쳐넣는 게 아니라 서버에서 열린 채팅방에 한해 정보를 불러온다!
        ArrayList<UserInfo> list = new ArrayList<UserInfo>();

        String rank, name, num;
        String sql = "SELECT * FROM userinfo;";
        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext())
        {
            rank = cursor.getString(0);
            name = cursor.getString(1);
            num = cursor.getString(2);

            list.add(new UserInfo(rank, name, num));
        }

        return list;
    }
}
