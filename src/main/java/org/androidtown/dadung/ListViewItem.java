package org.androidtown.dadung;

import android.graphics.drawable.Drawable;

/**
 * Created by INA on 2017-04-10.
 */

public class ListViewItem {
    private Drawable iconDraw;  //리스트 이미지(작은 사이즈 아이콘)
    private String timeStr;  //시간 표시
    private String dataStr;  //수정 멘트 및 정보 표시
    private Drawable nextDraw; //화살표

    public ListViewItem(Drawable icon, String time, String data, Drawable next){
        this.iconDraw = icon;
        this.timeStr=time;
        this.dataStr=data;
        this.nextDraw=next;
    }

    public void setIcon(Drawable icon){
        iconDraw=icon;
    }
    public void setTime(String time){
        timeStr = time;
    }
    public void setData(String data){
        dataStr=data;
    }
    public void setNext(Drawable next){
        nextDraw=next;
    }
    public Drawable getIcon(){
        return this.iconDraw;
    }
    public String getTimeStr(){
        return this.timeStr;
    }
    public String getDataStr(){
        return this.dataStr;
    }
    public Drawable getNext(){
        return this.nextDraw;
    }
}
