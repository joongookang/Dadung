package org.androidtown.dadung;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;

import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;

import java.io.FileInputStream;


import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;



public class addvw extends AppCompatActivity {
    private static int PICK_IMAGE_REQUEST = 1;
    public static Context mContext;
    public static final int REQUEST_CODE_MENU=101;
    int y,m,d;
    EditText getiname;
    String joinname;
    String joindate;
    String filna;
    String dffdf;
    String joinid = ((login) login.mContext).Setuserid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvw);
        mContext = this;
        getiname = (EditText) findViewById(R.id.name123);
        ImageButton imageButton = (ImageButton) findViewById(R.id.add2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike");
                setResult(RESULT_OK, intent);
                finish();
            }


        });

    }

    public void onCli(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK); //ACTION_PIC과 차이점?
        intent.setType("image/*"); //이미지만 보이게
        //Intent 시작 - 갤러리앱을 열어서 원하는 이미지를 선택할 수 있다.
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        // 갤러리 사용 권한 체크( 사용권한이 없을경우 -1 )
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 권한이 없을경우

            // 최초 권한 요청인지, 혹은 사용자에 의한 재요청인지 확인
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // 사용자가 임의로 권한을 취소시킨 경우
                // 권한 재요청
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);

            } else {
                // 최초로 권한을 요청하는 경우(첫실행)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        } else {
            // 사용 권한이 있음을 확인한 경우
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 권한이 없을경우

            // 최초 권한 요청인지, 혹은 사용자에 의한 재요청인지 확인
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // 사용자가 임의로 권한을 취소시킨 경우
                // 권한 재요청
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

            } else {
                // 최초로 권한을 요청하는 경우(첫실행)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        } else {
            // 사용 권한이 있음을 확인한 경우
        }









    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //이미지를 하나 골랐을때
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {

                Uri uri = data.getData();
                String str = getRealPathFromURI(uri);
                DoFileUpload(str);
                //data에서 절대경로로 이미지를 가져옴

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                //이미지가 크면 불러 오지 못하므로 사이즈를 줄여 준다.

                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 200, 200, true);


                ImageView imgView = (ImageView) findViewById(R.id.seve);
                imgView.setImageBitmap(getCircularBitmap(scaled));

            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    public void showDatePickerDialog(View v) {
        DatePickerDialog dialog = new DatePickerDialog(this, listener, 2017, 3, 1);
        dialog.show();

    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            TextView date = (TextView) findViewById(R.id.daee);
            monthOfYear++;
            y=year;m=monthOfYear;d=dayOfMonth;
            date.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
            Toast.makeText(getApplicationContext(), year + "년" + monthOfYear + "월" + dayOfMonth + "일", Toast.LENGTH_SHORT).show();
        }
    };


    public String getRealPathFromURI(Uri contentUri) {

        String res = null;
        String fileremove;
        String[] proj = {MediaStore.Images.Media.DATA};
        String date = new DateTime().toString(DateTimeFormat.forPattern("yyyyMMddHHmmss"));

        Cursor cursor = this.getContentResolver().query(contentUri, proj, null, null, null);

        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            fileremove = new File(cursor.getString(column_index)).getName();
            File filePre = new File(cursor.getString(column_index).replaceFirst(fileremove,""),fileremove);

            File fileNow = new File(cursor.getString(column_index).replaceFirst(fileremove,""),fileremove.substring(0,2)+date+".jpg");



            if(filePre.renameTo(fileNow)){

                Toast.makeText(getApplicationContext(), "변경 성공", Toast.LENGTH_SHORT).show();

            }else {

                Toast.makeText(getApplicationContext(), "변경 실패", Toast.LENGTH_SHORT).show();
            }
           filna =  new File(cursor.getString(column_index).replaceFirst(fileremove,""),fileremove.substring(0,2)+date+".jpg").getName();

            res = cursor.getString(column_index).replaceFirst(fileremove,"")+filna;
        }

        cursor.close();

        return res;

    }

    public static Bitmap getCircularBitmap(@NonNull Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


// 여기서부터가 jsp 이미지를 보내는 코드입니다.

    public void DoFileUpload(String absolutePath) {

//        HttpFileUpload("", absolutePath);

        new HttpFileUpload().execute(absolutePath);

    }

    class HttpFileUpload extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            String fileName = params[0];

            String lineEnd = "\r\n";

            String twoHyphens = "--";

            String boundary = "*****";

            try {

                File sourceFile = new File(fileName);

                DataOutputStream dos;

                if (!sourceFile.isFile()) {

                    Log.e("uploadFile", "Source File not exist :" + fileName);

                } else {

                    FileInputStream mFileInputStream = new FileInputStream(sourceFile);
                    CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
                    URL connectUrl = new URL("http://10.0.2.2:8080/test/save.jsp");

                    // open connection

                    HttpURLConnection conn = (HttpURLConnection) connectUrl.openConnection();

                    conn.setDoInput(true);

                    conn.setDoOutput(true);

                    conn.setUseCaches(false);

                    conn.setRequestProperty("Connection", "Keep-Alive");

                    conn.setRequestProperty("enctype", "multipart/form-data");

                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                    conn.setRequestProperty("uploaded_file", fileName);

                    conn.setRequestMethod("POST");

                    // write data

                    dos = new DataOutputStream(conn.getOutputStream());

                    dos.writeBytes(twoHyphens + boundary + lineEnd);

                    dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\"" + lineEnd);

                    dos.writeBytes(lineEnd);


                    int bytesAvailable = mFileInputStream.available();

                    int maxBufferSize = 1024 * 1024;

                    int bufferSize = Math.min(bytesAvailable, maxBufferSize);


                    byte[] buffer = new byte[bufferSize];

                    int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);


                    // read image

                    while (bytesRead > 0) {

                        dos.write(buffer, 0, bufferSize);

                        bytesAvailable = mFileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);

                        bytesRead = mFileInputStream.read(buffer, 0, bufferSize);

                    }


                    dos.writeBytes(lineEnd);

                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    mFileInputStream.close();

                    dos.flush(); // finish upload...

                    if (conn.getResponseCode() == 200) {

                        InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");

                        BufferedReader reader = new BufferedReader(tmp);

                        StringBuffer stringBuffer = new StringBuffer();

                        String line;

                        while ((line = reader.readLine()) != null) {

                            stringBuffer.append(line);

                        }

                    }

                    mFileInputStream.close();

                    dos.close();

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

            return null;
        }
    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://10.0.2.2:8080/test/addi.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&date="+strings[1]+"&name="+strings[2]+"&parent="+strings[3]+"&filename="+strings[4];
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


        public void addi(View view) {
            joindate = y + "-" + m + "-" + d;
            joinname = getiname.getText().toString();
            Switch sw = (Switch) findViewById(R.id.switch1);


            try {
                String result = new addvw.CustomTask().execute(joinid, joindate, joinname,"1",filna).get();
                if (sw.getText().equals("엄마")) {
                    Toast.makeText(addvw.this, "엄마입니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), addat.class);
                    startActivityForResult(intent, REQUEST_CODE_MENU);
                } else {
                    finish();
                    Toast.makeText(addvw.this, "아빠입니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), addat.class);
                    startActivityForResult(intent, REQUEST_CODE_MENU);
                }
            } catch (Exception e) {
            }


        }



}

