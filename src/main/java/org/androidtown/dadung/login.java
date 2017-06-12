package org.androidtown.dadung;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class login extends AppCompatActivity {
    Button loginBtn;
    EditText userId, userPwd;
    public static final int REQUEST_CODE_MENU=101;
    public static Context mContext;
    int REQUEST_CODE=101;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext= this;
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(btnListener);
        userId = (EditText) findViewById(R.id.loginId);
        userPwd = (EditText) findViewById(R.id.loginPwd);
        TextView text1 = (TextView) findViewById(R.id.t1);
        TextView text2 = (TextView) findViewById(R.id.t2);
        Button button = (Button) findViewById(R.id.ga);
        Button button1 = (Button) findViewById(R.id.loginBtn);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "NanumSquareB.ttf");
        Typeface typeFace1 = Typeface.createFromAsset(getAssets(), "Typo_CrayonB.ttf");
        text1.setTypeface(typeFace);
        text2.setTypeface(typeFace1);
        button.setTypeface(typeFace);
        button1.setTypeface(typeFace);

    }
    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://10.0.2.2:8080/test/login.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pw="+strings[1]+"&type="+strings[2];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loginBtn: // 로그인 버튼 눌렀을 경우
                    String loginid = userId.getText().toString();
                    String loginpwd = userPwd.getText().toString();
                    try {
                        String result = new CustomTask().execute(loginid, loginpwd, "login").get();
                        if (result.equals("true")) {
                            Toast.makeText(login.this, "로그인", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login.this, addat.class);
                            startActivity(intent);
                            finish();
                        } else if (result.equals("false")) {
                            Toast.makeText(login.this, "아이디 or 비밀번호가 다름", Toast.LENGTH_SHORT).show();
                            userId.setText("");
                            userPwd.setText("");
                        } else if (result.equals("noId")) {
                            Toast.makeText(login.this, "존재하지 않는 아이디", Toast.LENGTH_SHORT).show();
                            userId.setText("");
                            userPwd.setText("");
                        }
                    } catch (Exception e) {
                    }
                    break;
            }
        }
    };
    public void userga(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
    public String Setuserid(){
        String loginid = userId.getText().toString();
        return loginid;

    }

}
