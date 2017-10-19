package com.example.administrator.projectv01;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-10-17.
 */

public class ChattingAdapter extends ArrayAdapter<ListContents>
{
    private ArrayList<ListContents> m_List;
    private Context context;
    private int layoutResourcesId;


    public ChattingAdapter(Context context, int layoutResourcesId, ArrayList<ListContents> data)
    {
        super(context, layoutResourcesId, data);
        this.m_List = new ArrayList<>();
        this.layoutResourcesId = layoutResourcesId;
        this.context = context;
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(String msg,int type) {

        m_List.add(new ListContents(msg, type));
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int position) {
        m_List.remove(position);
    }

    @Override
    public int getCount() {
        return m_List.size();
    }

    @Override
    public ListContents getItem(int position) {
        return m_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( row == null )
        {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourcesId, parent, false);
        }
        else
        {
            Log.d("TEST", "3차 else 파트, 여기까진 완료됐음");
        }

        TextView text = text = (TextView)row.findViewById(R.id.middleText);
        LinearLayout layout = (LinearLayout)row.findViewById(R.id.layout);
        View viewRight = (View)row.findViewById(R.id.imageViewright);
        View viewLeft = (View)row.findViewById(R.id.imageViewleft);

        // Text 등록 이게 문제가 됐네...
        if(text == null)
        {
            Log.d("TEST", "텍스트 자체가 비어있다는 증거");
        }
        else
        {
            text.setText(m_List.get(position).getMsg());
        }


        if( m_List.get(position).getType() == 0 )
        {
            text.setBackgroundResource(R.drawable.inbox2);
            layout.setGravity(Gravity.LEFT);
            viewRight.setVisibility(View.GONE);
            viewLeft.setVisibility(View.GONE);

            Log.d("TEST", "4차 1번째 파트, 여기까진 완료됐음");
        }
        else if(m_List.get(position).type == 1)
        {
            text.setBackgroundResource(R.drawable.outbox2);
            layout.setGravity(Gravity.RIGHT);
            viewRight.setVisibility(View.GONE);
            viewLeft.setVisibility(View.GONE);

            Log.d("TEST", "4차 2번째 파트, 여기까진 완료됐음");
        }else if(m_List.get(position).type == 2)
        {
            text.setBackgroundResource(R.drawable.datebg);
            layout.setGravity(Gravity.CENTER);
            viewRight.setVisibility(View.VISIBLE);
            viewLeft.setVisibility(View.VISIBLE);

            Log.d("TEST", "4차 3번째 파트, 여기까진 완료됐음");
        }

        /*
        // 리스트 아이템을 터치 했을 때 이벤트 발생
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 터치 시 해당 아이템 이름 출력
                Toast.makeText(context, "리스트 클릭 : "+m_List.get(v.getId()), Toast.LENGTH_SHORT).show();
            }
        });



        // 리스트 아이템을 길게 터치 했을때 이벤트 발생
        convertView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // 터치 시 해당 아이템 이름 출력
                Toast.makeText(context, "리스트 롱 클릭 : "+m_List.get(v.getId()), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        */

        return row;
    }

}
