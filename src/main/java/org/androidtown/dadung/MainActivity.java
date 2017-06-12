package org.androidtown.dadung;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.*;
import java.net.*;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    EditText userId, userPwd, userEmail, Conform;
    Button  joinBtn;
    public static final int REQUEST_CODE_MENU=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton = (ImageButton) findViewById(R.id.facebutton);
        imageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name","mike");
                setResult(RESULT_OK, intent);

                finish();
            }

        });


        userId = (EditText) findViewById(R.id.userId);
        userEmail = (EditText) findViewById(R.id.userEmail);
        userPwd = (EditText) findViewById(R.id.userPwd);
        Conform = (EditText) findViewById(R.id.Conform);
        joinBtn = (Button) findViewById(R.id.joinBtn);
        joinBtn.setOnClickListener(btnListener);

        TextView text1 = (TextView) findViewById(R.id.ti);
        EditText text3 = (EditText) findViewById(R.id.userId);
        EditText text4 = (EditText) findViewById(R.id.userEmail);
        Button text7 = (Button) findViewById(R.id.joinBtn);
        TextView text2 = (TextView) findViewById(R.id.ti1);

       Typeface typeFace = Typeface.createFromAsset(getAssets(), "NanumSquareB.ttf");
        Typeface typeFace1 = Typeface.createFromAsset(getAssets(), "Typo_CrayonB.ttf");
        text1.setTypeface(typeFace);
        text3.setTypeface(typeFace);
        text4.setTypeface(typeFace);
        text7.setTypeface(typeFace);
        text2.setTypeface(typeFace1);


    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://10.0.2.2:8080/test/data.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pw="+strings[1]+"&email="+strings[2]+"&type="+strings[3];
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
                case R.id.joinBtn: // 회원가입
                    String joinid = userId.getText().toString();
                    String joinEmail = userEmail.getText().toString();
                    String joinpwd = userPwd.getText().toString();
                    String joinConf = Conform.getText().toString();

                    if(joinpwd.equals(joinConf)) {
                    try {
                        String result = new CustomTask().execute(joinid, joinpwd, joinEmail, "join").get();
                        if (result.equals("id")) {
                            Toast.makeText(MainActivity.this, "존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                            userId.setText("");
                            userPwd.setText("");
                        } else if (result.equals("ok")) {
                            userId.setText("");
                            userPwd.setText("");
                            finish();
                            Toast.makeText(MainActivity.this, "회원가입을 축하합니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivityForResult(intent, REQUEST_CODE_MENU);
                        }
                    } catch (Exception e) {
                    }
                    break;
                }
                else{
                        userId.setText("");
                        userEmail.setText("");
                        userPwd.setText("");
                        Conform.setText("");
                        Toast.makeText(MainActivity.this, "비밀번호가 같지않습니다.", Toast.LENGTH_SHORT).show();

                    }

            }

        }

    };
}
