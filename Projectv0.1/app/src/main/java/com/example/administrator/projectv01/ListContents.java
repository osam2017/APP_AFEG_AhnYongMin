package com.example.administrator.projectv01;

public class ListContents
{
    String msg;
    int type;

    ListContents(String msg,int type)
    {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public int getType() { return type; }
}
