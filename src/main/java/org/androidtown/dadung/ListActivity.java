package org.androidtown.dadung;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);
        //CustomTask task = new CustomTask;
        //task.excute("root","root");

        final ListView listview = (ListView) findViewById(R.id.listView);
        //adapter생성
       final ListViewAdapter adapter = new ListViewAdapter();

        //리스트뷰 참조 및 adapter닫기
        listview.setAdapter(adapter);

        ImageButton bmButton = (ImageButton)findViewById(R.id.buttonBreastMilk); //모유버튼
        ImageButton dryButton = (ImageButton)findViewById(R.id.buttonDryMilk); //분유버튼
        ImageButton bfButton = (ImageButton)findViewById(R.id.buttonBabyFood); // 이유식버튼
        ImageButton diaButton = (ImageButton)findViewById(R.id.buttonDiaper); //기저귀버튼
        ImageButton sleepButton = (ImageButton)findViewById(R.id.buttonsleep); //수면버튼
        ImageButton bpButton = (ImageButton)findViewById(R.id.buttonbreastPump); //유축수육버튼
        ImageButton pmButton = (ImageButton)findViewById(R.id.buttonpumpMilk); //유축버튼
        ImageButton bathButton = (ImageButton)findViewById(R.id.buttonbath); //목욕버튼
        ImageButton vacButton = (ImageButton)findViewById(R.id.buttonvaccin); //예방접종버튼
        ImageButton etcButton = (ImageButton)findViewById(R.id.buttonetc); //기타버튼

        //클릭 리스너 추가
        bmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(getResources().getDrawable(R.drawable.mini_breastmilk),getTime(),"data",getResources().getDrawable(R.drawable.next));
                adapter.notifyDataSetChanged();
                String time = Time();
                String date = getDate();
                String data = "null";
                try{
                    String result = new CustomTask().execute(date,time,data).get();

                }catch (Exception e){}
            }
        });
        dryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(getResources().getDrawable(R.drawable.mini_drymilk),getTime(),"data",getResources().getDrawable(R.drawable.next));
                adapter.notifyDataSetChanged();
                String time = Time();
                String date = getDate();
                String data = "null";
                try{
                    String result = new CustomTask().execute(date,time,data).get();

                }catch (Exception e){}
            }
        });
        bfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(getResources().getDrawable(R.drawable.mini_babyfood),getTime(),"data",getResources().getDrawable(R.drawable.next));
                adapter.notifyDataSetChanged();
                String time = Time();
                String date = getDate();
                String data = "null";
                try{
                    String result = new CustomTask().execute(date,time,data).get();

                }catch (Exception e){}
            }
        });
        diaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(getResources().getDrawable(R.drawable.mini_diaper),getTime(),"data",getResources().getDrawable(R.drawable.next));
                adapter.notifyDataSetChanged();
                String time = Time();
                String date = getDate();
                String data = "null";
                try{
                    String result = new CustomTask().execute(date,time,data).get();

                }catch (Exception e){}
            }
        });
        sleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(getResources().getDrawable(R.drawable.mini_sleep),getTime(),"data",getResources().getDrawable(R.drawable.next));
                adapter.notifyDataSetChanged();
                String time = Time();
                String date = getDate();
                String data = "null";
                try{
                    String result = new CustomTask().execute(date,time,data).get();

                }catch (Exception e){}
            }
        });
        bpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(getResources().getDrawable(R.drawable.mini_breastpump),getTime(),"data",getResources().getDrawable(R.drawable.next));
                adapter.notifyDataSetChanged();
                String time = Time();
                String date = getDate();
                String data = "null";
                try{
                    String result = new CustomTask().execute(date,time,data).get();

                }catch (Exception e){}
            }
        });
        pmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(getResources().getDrawable(R.drawable.mini_pumpmilk),getTime(),"data",getResources().getDrawable(R.drawable.next));
                adapter.notifyDataSetChanged();
                String time = Time();
                String date = getDate();
                String data = "null";
                try{
                    String result = new CustomTask().execute(date,time,data).get();

                }catch (Exception e){}
            }
        });
        bathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(getResources().getDrawable(R.drawable.mini_bath),getTime(),"data",getResources().getDrawable(R.drawable.next));
                adapter.notifyDataSetChanged();
                String time = Time();
                String date = getDate();
                String data = "null";
                try{
                    String result = new CustomTask().execute(date,time,data).get();

                }catch (Exception e){}
            }
        });
        vacButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(getResources().getDrawable(R.drawable.mini_vaccin),getTime(),"data",getResources().getDrawable(R.drawable.next));
                adapter.notifyDataSetChanged();
                String time = Time();
                String date = getDate();
                String data = "null";
                try{
                    String result = new CustomTask().execute(date,time,data).get();

                }catch (Exception e){}
            }
        });
        etcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(getResources().getDrawable(R.drawable.mini_etc),getTime(),"data",getResources().getDrawable(R.drawable.next));
                adapter.notifyDataSetChanged();
                String time = Time();
                String date = getDate();
                String data = "null";
                try{
                    String result = new CustomTask().execute(date,time,data).get();
                }catch (Exception e){}
            }
        });

    }
    class CustomTask extends AsyncTask<String, Void, String>{
        String send, receive;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                String str;
                URL url = new URL("http://10.0.2.2:8080/server/data.jsp");
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw =  new OutputStreamWriter(conn.getOutputStream());
                send = "date="+strings[0]+"&time="+strings[1]+"&data="+strings[2];
                osw.write(send);
                osw.flush();
                if(conn.getResponseCode()==conn.HTTP_OK){
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(),"UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buff = new StringBuffer();
                    while((str=reader.readLine())!=null){
                        buff.append(str);
                    }
                    receive = buff.toString();
                }else {
                    Log.i("통신 결과",conn.getResponseCode()+"에러");
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return receive;
        }
    }
// 현재 시간 받아오는 메소드
    public static String getTime(){
        long now = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("aa hh:mm");
        Date date = new Date(now);
        String strDate = dateFormat.format(date);
        return strDate;
    }
    //db 시간
    public static String Time(){
        long now = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        Date date = new Date(now);
        String strDate = dateFormat.format(date);
        return strDate;
    }

    // 디비에 저장되는 현재 날짜 구하는 메소드
    public static String getDate(){
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(d);
        return date;
    }
}


