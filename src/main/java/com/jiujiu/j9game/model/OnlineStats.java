package com.jiujiu.j9game.model;

import java.util.Date;

public class OnlineStats {
    private String _id = "SSS";
    private Integer sid = 123;
    private Integer minute = 2000;
    private Integer pcount = 0;
    private Integer rcount = 0;
    private Date date = new Date();

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getPcount() {
        return pcount;
    }

    public void setPcount(Integer pcount) {
        this.pcount = pcount;
    }

    public Integer getRcount() {
        return rcount;
    }

    public void setRcount(Integer rcount) {
        this.rcount = rcount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "_id='" + _id + '\'' +
                ", sid=" + sid +
                ", minute=" + minute +
                ", pcount=" + pcount +
                ", rcount=" + rcount +
                ", date=" + date +
                '}';
    }

    public static void main(String[] args) {
        OnlineStats stats = new OnlineStats();
        System.out.println(stats.toString());
    }
}
