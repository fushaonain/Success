package success.liang.com.success.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by liangx on 2015/8/4.
 */
public class HttpUtils {


    public static void getNewsJSON(final String url,final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection conn;
                InputStream is;
                try {
                    conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setRequestMethod("GET");
                    is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line = "";
                    StringBuilder result = new StringBuilder();
                    while ((line = reader.readLine()) != null)
                    {
                        result.append(line);
                    }
                    Message msg = new Message();
                    //msg.obj = result;
                    msg.obj = result.toString();
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.print(e);
                }
            }
        }).start();
    }

    public static void getNewsJSON_two(final String url,final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection conn;
                InputStream is;
                try {
                    conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setRequestMethod("GET");
                    is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line = "";
                    StringBuilder result = new StringBuilder();
                    while ((line = reader.readLine()) != null)
                    {
                        result.append(line);
                    }
                    Message msg = new Message();
                    //msg.obj = result;
                    msg.obj = result.toString();
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.print(e);
                }
            }
        }).start();
    }

    public static void  setPicBitmap(final ImageView ivpic,final String pic_url){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(pic_url).openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    ivpic.setImageBitmap(bitmap);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public static void getStudentInformationByStudentID(final String userName,final String url,final Handler handler){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                try {

                    URL url = new URL(params[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();


                    String data = "studentId=" + URLEncoder.encode(userName, "UTF-8")
                            + "&enter=true";

                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    connection.setUseCaches(false);
                    /*connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Referer", "http://my.its.csu.edu.cn/Home/Default");
                    connection.setRequestProperty("Connection", "keep-alive");
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
                    connection.setRequestProperty("Cookie", "ASP.NET_SessionId=ua1najuewtoorjs5ecd2soht");*/


                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(data.getBytes());
                    outputStream.flush();

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String line = "";
                    String html = "";

                    while ((line = br.readLine()) != null) {
                        html += line;
                    }
                    br.close();
                    isr.close();
                    is.close();
                    Message msg = new Message();
                    //msg.obj = result;
                    msg.obj = html;
                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(url);

    }

    public static void getSearch(final String studentId,final String name,
                                  final String province,final String class_,
                                  final String year,final Handler handler,
                                 final String url, final String phone,
                                 final String ID, final String people,final String party){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                try {

                    URL url = new URL(params[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    String data = "enter=true";
                    if (studentId != null){
                        data += "&studentId=" + URLEncoder.encode(studentId, "UTF-8");
                    }
                    if(name != null){
                        data += "&name=" + URLEncoder.encode(name, "UTF-8");
                    }
                    if(province != null){
                        data += "&province=" + URLEncoder.encode(province, "UTF-8");
                    }
                    if(class_ != null){
                        data += "&class_=" + URLEncoder.encode(class_, "UTF-8");
                    }
                    if(year != null){
                        data += "&year=" + URLEncoder.encode(year, "UTF-8");
                    }
                    if(phone != null){
                        data += "&tel=" + URLEncoder.encode(phone, "UTF-8");
                    }
                    if(people != null){
                        data += "&nation=" + URLEncoder.encode(people, "UTF-8");
                    }
                    if(party != null){
                        data += "&political=" + URLEncoder.encode(party, "UTF-8");
                    }
                    if(ID != null){
                        data += "&idcard=" + URLEncoder.encode(ID, "UTF-8");
                    }

                    /*String data = "studentId=" + URLEncoder.encode(studentId, "UTF-8")
                            + "name=" + URLEncoder.encode(name, "UTF-8")
                            + "province=" + URLEncoder.encode(province, "UTF-8")
                            + "class_=" + URLEncoder.encode(class_, "UTF-8")
                            + "year=" + URLEncoder.encode(year, "UTF-8");*/

                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    connection.setUseCaches(false);
                    /*connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Referer", "http://my.its.csu.edu.cn/Home/Default");
                    connection.setRequestProperty("Connection", "keep-alive");
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
                    connection.setRequestProperty("Cookie", "ASP.NET_SessionId=ua1najuewtoorjs5ecd2soht");*/


                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(data.getBytes());
                    outputStream.flush();

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String line = "";
                    String html = "";

                    while ((line = br.readLine()) != null) {
                        html += line;
                    }
                    br.close();
                    isr.close();
                    is.close();
                    Message msg = new Message();
                    //msg.obj = result;
                    msg.obj = html;
                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute(url);
    }


    public static void sendPosition(final String studentId,final String position,
                                 final String time,final String url,final Handler handler){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                try {

                    URL url = new URL(params[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    String data = "studentId=" + URLEncoder.encode(studentId, "UTF-8")
                            + "&location=" + URLEncoder.encode(position, "UTF-8")
                            + "&date=" + URLEncoder.encode(time, "UTF-8");

                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    connection.setUseCaches(false);
                    /*connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Referer", "http://my.its.csu.edu.cn/Home/Default");
                    connection.setRequestProperty("Connection", "keep-alive");
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
                    connection.setRequestProperty("Cookie", "ASP.NET_SessionId=ua1najuewtoorjs5ecd2soht");*/


                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(data.getBytes());
                    outputStream.flush();

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String line = "";
                    String html = "";

                    while ((line = br.readLine()) != null) {
                        html += line;
                    }
                    br.close();
                    isr.close();
                    is.close();
                    Message msg = new Message();
                    //msg.obj = result;
                    msg.obj = html;
                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute(url);
    }

    public static void getTeacherInformation(final String userName,final String password,final String url,final Handler handler){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                try {

                    URL url = new URL(params[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();


                    String data = "account=" + URLEncoder.encode(userName, "UTF-8")
                            + "&password=" + URLEncoder.encode(password, "UTF-8")
                            + "&enter=true";

                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    connection.setUseCaches(false);
                    /*connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Referer", "http://my.its.csu.edu.cn/Home/Default");
                    connection.setRequestProperty("Connection", "keep-alive");
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
                    connection.setRequestProperty("Cookie", "ASP.NET_SessionId=ua1najuewtoorjs5ecd2soht");*/


                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(data.getBytes());
                    outputStream.flush();

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String line = "";
                    String html = "";

                    while ((line = br.readLine()) != null) {
                        html += line;
                    }
                    br.close();
                    isr.close();
                    is.close();
                    Message msg = new Message();
                    //msg.obj = result;
                    msg.obj = html;
                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(url);

    }



}
