package com.example.administrator.projectv01;

/**
 * Created by Administrator on 2017-10-18.
 */

public class UserInfo
{
    private String rank, name, num;

    public UserInfo(String rank, String name, String num) {
        this.rank = rank;
        this.name = name;
        this.num = num;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
