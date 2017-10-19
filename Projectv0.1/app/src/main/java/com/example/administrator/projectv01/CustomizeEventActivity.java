package com.example.administrator.projectv01;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class CustomizeEventActivity extends AppCompatActivity implements View.OnClickListener{
    int year, month, day, hour, minute;
    private TextView datePick, timePick, orderText;
    private EditText eventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_event);

        //날짜와 시간을 고르기 위한 다이얼로그
        datePick = (TextView)findViewById(R.id.date_pick);
        timePick = (TextView)findViewById(R.id.time_pick);
        eventName = (EditText)findViewById(R.id.event_name);
        orderText = (TextView)findViewById(R.id.order_text);

        Calendar cal = new GregorianCalendar();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);

        UpdateNow();

        Button setButton = (Button)findViewById(R.id.pickButton);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(CustomizeEventActivity.this, dateSetListener, year, month, day).show();

                new TimePickerDialog(CustomizeEventActivity.this, timeSetListener, hour, minute, false).show();
            }
        });

        Button makeButton = (Button)findViewById(R.id.makeButton);
        makeButton.setOnClickListener(this);

        Button songButton = (Button)findViewById(R.id.addSong);
        songButton.setOnClickListener(this);

        Button actionButton = (Button)findViewById(R.id.addAction);
        actionButton.setOnClickListener(this);
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int _year, int _month, int dayOfMonth) {
            year = _year;
            month = _month;
            day = dayOfMonth;
            UpdateNow();
        }
    };

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int _minute) {
            hour=hourOfDay;
            minute=_minute;
            UpdateNow();
        }
    };

    void UpdateNow()
    {
        datePick.setText(String.format("%d/%d/%d", year, month+1, day));
        timePick.setText(String.format("-%d:%d", hour, minute));
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.makeButton:
                String name = eventName.getText().toString();
                String time = datePick.getText().toString() + timePick.getText().toString();
                String detail = orderText.getText().toString();

                //다시 eventactivity로 돌아가면서 자료 전달
                Intent intent = new Intent(this, EventActivity.class);
                intent.putExtra("행사명", name);
                intent.putExtra("시간", time);
                intent.putExtra("세부내용", detail);
                startActivity(intent);
                finish();
                break;

            case R.id.addSong:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("새로운 행사곡 추가하기");
                alert.setMessage("어떤 행사곡을 추가하시겠습니까?");

                //얘는 행사곡 모음으로 바꿔야 할듯
                final EditText songName = new EditText(this);
                alert.setView(songName);

                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String song = songName.getText().toString();
                        orderText.append(song + "\n");
                    }
                });

                alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                });

                alert.show();
                break;

            case R.id.addAction:
                AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
                alert2.setTitle("새로운 행동 추가하기");
                alert2.setMessage("어떤 행동을 추가하시겠습니까?");

                final EditText action = new EditText(this);
                alert2.setView(action);

                alert2.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String act = action.getText().toString();
                        orderText.append(act + "\n");
                    }
                });

                alert2.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                });

                alert2.show();
                break;

            default:
                Log.d("TEST", "잘못된 접근입니다.");
                return;
        }
    }
}
