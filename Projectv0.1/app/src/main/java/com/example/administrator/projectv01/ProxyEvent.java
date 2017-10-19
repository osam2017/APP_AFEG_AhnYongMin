package com.example.administrator.projectv01;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017-10-18.
 *
 *  JSON 데이터를 받고, 새로 만든 이벤트나 유저의 정보를 보내는 부분이 필요하다.
 */

public class ProxyEvent
{
    public String getJSON()
    {
        try
        {
            // uploadevent에 접속해야 한다
            URL url = new URL("http://10.53.128.134:5030/event");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setConnectTimeout(10*1000);
            conn.setConnectTimeout(10*1000);

            conn.setRequestMethod("GET");

            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Accept-Charset", "application/json");

            conn.setDoInput(true);

            //여기서 연결이 안 된다. 왜 그러지.. url은 제대로 입력돼있고, manifest도 입력했는데..
            conn.connect();

            Log.d("TEST", "1차 성공");
            int status = conn.getResponseCode();
            Log.i("TEST", "ProxyResponseCode: "+status);

            switch (status)
            {
                //정상적으로 데이터를 받은 상태
                case 200:
                    break;
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    Log.d("TEST", "2차 성공");
                    while((line = br.readLine()) !=null)
                    {
                        sb.append(line + "/n");
                    }
                    br.close();
                    Log.d("TEST", "3차 성공");

                    return sb.toString();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            Log.i("TEST", "네트워크 에러: " + e);
        }

        return null;
    }
}
