package com.example.administrator.projectv01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText classEdit, numEdit, nameEdit;

    //private static String[] classOfPerson = {"이병", "일병", "상병", "병장", "하사", "중사", "상사", "원사", "준위", "소위", "중위", "대위",  "소령", "중령", "대령", "준장", "소장", "중장", "대장"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        classEdit = (EditText)findViewById(R.id.classEdit);
        numEdit = (EditText)findViewById(R.id.numEdit);
        nameEdit = (EditText)findViewById(R.id.nameEdit);

        Button enterButton = (Button)findViewById(R.id.enterButton);
        enterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.enterButton:
                /*
                if(classEdit.getText().length() == 0 || numEdit.getText().length() == 0 || nameEdit.getText().length() == 0)
                {
                    //셋 중 하나라도 비면 입장 못 하도록 막음!
                    Log.d("TEST", "셋 중 하나가 비었습니다.");
                    return;
                }
                */

                /*
                String str = classEdit.getText().toString();
                for(String string : classOfPerson)
                {
                    if(str.equals(string))
                    {
                        //셋 다 입력돼있으면 전환할할 인텐트에 자료 전달
                        Intent intent = new Intent(this, ChatroomActivity.class);
                        intent.putExtra("계급", classEdit.getText().toString());
                        intent.putExtra("성명", nameEdit.getText().toString());
                        intent.putExtra("군번", numEdit.getText().toString());
                        startActivity(intent);
                        return;
                    }
                }
                Log.d("TEXT", "계급이 아닙니다.");
                */

                //셋 다 입력돼있으면 전환할할 인텐트에 자료 전달
                Intent intent = new Intent(this, ChatroomActivity.class);
                intent.putExtra("계급", classEdit.getText().toString());
                intent.putExtra("성명", nameEdit.getText().toString());
                intent.putExtra("군번", numEdit.getText().toString());
                startActivity(intent);
                finish();
                return;

            default:
                Log.d("TEST", "잘못된 접근");
                return;
        }
    }

}
