package success.liang.com.success.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import success.liang.com.success.MainActivity;
import success.liang.com.success.R;
import success.liang.com.success.bean.MyApplication;
import success.liang.com.success.bean.Student;
import success.liang.com.success.bean.Teacher;
import success.liang.com.success.utils.HttpUtils;
import success.liang.com.success.utils.ndialog;

public class login extends AppCompatActivity {


    private EditText mUsername;
    private EditText mPassword;
    private Button mSubmit;
    private TextView mAbout;
    private String html = "";
    private String getUserName;
    private Spinner select;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private MyApplication myApplication;
    private Student student;
    private Teacher teacher;

    public static final String GET_INFORMATION_URL = "http://192.168.1.17:8080/studentManagement/findStudentInA";
    public static final String GET_INFORMATION_URL_TEACHER = "http://192.168.1.17:8080/studentManagement/teacherlogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("登录");
        actionBar.setDisplayHomeAsUpEnabled(true);

        myApplication = (MyApplication) getApplication();

        //控件绑定
        mUsername=(EditText)findViewById(R.id.username);
        mPassword=(EditText)findViewById(R.id.password);
        mSubmit=(Button)findViewById(R.id.submit);
        mAbout=(TextView)findViewById(R.id.about);
        select=(Spinner)findViewById(R.id.select);

        data_list = new ArrayList<String>();
        data_list.add("学生");
        data_list.add("辅导员");
        data_list.add("书记");

        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        select.setAdapter(arr_adapter);




        //对添加文本框左边添加的图片控制大小以及位置
        Drawable picUser =getResources().getDrawable(R.drawable.username);
        picUser.setBounds(0, 0, 40, 40);
        mUsername.setCompoundDrawables(picUser, null, null, null);
        Drawable picPassword = getResources().getDrawable(R.drawable.password);
        picPassword.setBounds(0, 0, 40, 40);
        mPassword.setCompoundDrawables(picPassword, null, null, null);

        //响应事件
        mSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch ((String) select.getSelectedItem()) {
                    case "学生":
                        login_student();
                        break;
                    case "辅导员":
                        login_teacher();
                        break;
                    case "书记":
                        login_secretary();
                        break;
                }
            }


        });


        mAbout.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                myDialog("By:软件学院", 2, 1500);
            }
        });
    }

    private void login_student(){
        if(0 == mUsername.length()){
            myDialog("请输入学号", 0, 1200);
            return;
        }
        else if(10 > mUsername.length()){
            myDialog("学号应该是10个数字", 0, 1200);
            return;
        }
        else if(0 == mPassword.length()){
            myDialog("请输入密码", 0, 1200);
            return;
        }else{

            final String userName = mUsername.getText().toString();
            final String userPassword = mPassword.getText().toString();
            myDialog("正在登录，请稍等……", 1, 1000000);


            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... params) {

                    try {
                        URL url = new URL(params[0]);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();


                        String data = "userName=" + URLEncoder.encode(userName, "UTF-8")
                                + "&passWord=" + URLEncoder.encode(userPassword, "UTF-8")
                                + "&enter=true";

                        connection.setConnectTimeout(5000);
                        connection.setReadTimeout(5000);
                        connection.setDoInput(true);
                        connection.setDoOutput(true);
                        connection.setRequestMethod("POST");
                        connection.setUseCaches(false);
                        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        connection.setRequestProperty("Referer", "http://my.its.csu.edu.cn/Home/Default");
                        connection.setRequestProperty("Connection", "keep-alive");
                        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
                        connection.setRequestProperty("Cookie", "ASP.NET_SessionId=ua1najuewtoorjs5ecd2soht");


                        OutputStream outputStream = connection.getOutputStream();
                        outputStream.write(data.getBytes());
                        outputStream.flush();


                        int response = connection.getResponseCode();
                        InputStream is = connection.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is, "utf-8");
                        BufferedReader br = new BufferedReader(isr);
                        String line = "";

                        while ((line = br.readLine()) != null) {
                            html += line;
                        }
                        br.close();
                        isr.close();
                        is.close();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return html;
                }

                @Override
                protected void onPostExecute(String s) {
                    try {
                        Document doc = Jsoup.parse(s);
                        Element units = doc.getElementsByClass("cBlue").get(0);
                        getUserName = units.text();
                        if (getUserName.length() == 2 || getUserName.length() == 3) {
                            //ndialog.instance.finish();

                            myApplication.setUserName(userName);
                            HttpUtils.getStudentInformationByStudentID(userName, GET_INFORMATION_URL, getNewsHandler_student);
                                        /*Toast.makeText(login.this, "登陆成功", Toast.LENGTH_LONG).show();*/
                                        /*Intent intent = new Intent(login.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();*/
                        } else {
                            ndialog.instance.finish();
                            Toast.makeText(login.this, "登录失败，请重新登录", Toast.LENGTH_LONG).show();
                            mUsername.setText("");
                            mPassword.setText("");
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                        ndialog.instance.finish();
                        Toast.makeText(login.this, "登录失败，请重新登录", Toast.LENGTH_LONG).show();
                        mUsername.setText("");
                        mPassword.setText("");
                    }
                }
            }.execute("http://my.its.csu.edu.cn/");
        }
    }

    private void login_teacher(){
        if(0 == mUsername.length()){
            myDialog("请输入账号", 0, 1200);
            return;
        }
        else if(0 == mPassword.length()){
            myDialog("请输入密码", 0, 1200);
            return;
        }else {
            final String userName = mUsername.getText().toString();
            final String userPassword = mPassword.getText().toString();
            myDialog("正在登录，请稍等……", 1, 1000000);
            /*if(userName.equals("root") && userPassword.equals("123456")){
                myApplication.setId(1);
                myApplication.setUserName("辅导员");
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id", 1);
                editor.putString("teachername","辅导员");
                editor.commit();
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                ndialog.instance.finish();
                myDialog("账号或者密码错误", 0, 1200);
                return;
            }*/

            HttpUtils.getTeacherInformation(userName,userPassword,GET_INFORMATION_URL_TEACHER,getNewsHandler_teacher);


        }

    }
    private void login_secretary(){
        if(0 == mUsername.length()){
            myDialog("请输入账号", 0, 1200);
            return;
        }
        else if(6 > mUsername.length()){
            myDialog("账号错误", 0, 1200);
            return;
        }
        else if(0 == mPassword.length()){
            myDialog("请输入密码", 0, 1200);
            return;
        }else {
            final String userName = mUsername.getText().toString();
            final String userPassword = mPassword.getText().toString();
            if(userName.equals("wyyxgb") && userPassword.equals("w88836004")){
                myApplication.setId(2);
                myApplication.setUserName("书记");
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id", 2);
                editor.putString("secretaryname", "书记");
                editor.commit();
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                ndialog.instance.finish();
                myDialog("账号或者密码错误", 0, 1200);
                return;
            }
        }
    }

    private void myDialog(String promt,int icon,int time){
        Intent i =new Intent(login.this,ndialog.class);
        Bundle b =new Bundle();
        b.putString("promt", promt);
        b.putInt("icon", icon);
        b.putInt("time", time);
        i.putExtra("data", b);
        startActivity(i);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private Handler getNewsHandler_student = new Handler(){
        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            String jsonData = (String) msg.obj;

            JSONObject jsonObj = null;


            try {
                jsonObj = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONObject jsonObject = jsonObj.optJSONObject("student");
            Long studentId = jsonObject.optLong("studentId");
            String name = jsonObject.optString("name");
            String sex = jsonObject.optString("sex");
            Integer year = jsonObject.optInt("year");
            String nation = jsonObject.optString("nation");
            String political = jsonObject.optString("political");
            String yearAndMonthOfBirth = jsonObject.optString("yearAndMonthOfBirth");
            String class_ = jsonObject.optString("class_");
            String tel = jsonObject.optString("tel");
            String location = jsonObject.optString("location");
            String idcard = jsonObject.optString("idcard");
            String familyLocation = jsonObject.optString("familyLocation");
            String postcode = jsonObject.optString("postcode");
            String telOfFather = jsonObject.optString("telOfFather");
            String telOfMother = jsonObject.optString("telOfMother");
            String province = jsonObject.optString("province");
            String studentStatus = jsonObject.optString("studentStatus");
            String job = jsonObject.optString("job");
            Boolean isInSchool = jsonObject.optBoolean("isInSchool");
            String note = jsonObject.optString("note");
            int score1 = jsonObject.optInt("score1");
            int score2 = jsonObject.optInt("score2");
            int score3 = jsonObject.optInt("score3");
            int score4 = jsonObject.optInt("score4");
            String reward = jsonObject.optString("reward");
            String tutor = jsonObject.optString("tutor");
            String educationLevel = jsonObject.optString("educationLevel");
            String studentPeriod = jsonObject.optString("studentPeriod");
            String studyMode = jsonObject.optString("studyMode");
            String picture = jsonObject.optString("picture");
            String qq = jsonObject.optString("qq");
            String email = jsonObject.optString("email");
            student = new Student(studentId,name,sex,year,nation,political,yearAndMonthOfBirth,class_,tel,location,idcard,familyLocation,postcode,telOfFather,
                    telOfMother,province,studentStatus,job,isInSchool,note,score1,score2,score3,score4,reward,
                    tutor, educationLevel, studentPeriod,
                    studyMode, qq, email,
                    picture);
            myApplication.setStudent(student);
            myApplication.setId(0);
            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //0为学生
            editor.putInt("id", 0);
            editor.putLong("studentId", studentId);
            editor.putString("name", name);
            editor.putString("sex", sex);
            editor.putInt("year", year);
            editor.putString("nation", nation);
            editor.putString("political", political);
            editor.putString("yearAndMonthOfBirth",yearAndMonthOfBirth);
            editor.putString("class_",class_);
            editor.putString("tel",tel);
            editor.putString("location",location);
            editor.putString("idcard",idcard);
            editor.putString("familyLocation",familyLocation);
            editor.putString("telOfFather",telOfFather);
            editor.putString("telOfMother",telOfMother);
            editor.putString("province",province);
            editor.putString("studentStatus",studentStatus);
            editor.putString("job",job);
            editor.putBoolean("isInSchool", isInSchool);
            editor.putString("note", note);
            editor.putInt("score1", score1);
            editor.putInt("score2",score2);
            editor.putInt("score3",score3);
            editor.putInt("score4",score4);
            editor.putString("reward", reward);
            editor.putString("tutor", tutor);
            editor.putString("educationLevel", educationLevel);
            editor.putString("studentPeriod", studentPeriod);
            editor.putString("studyMode", studyMode);
            editor.putString("qq", qq);
            editor.putString("email", email);
            editor.putString("picture", picture);

            editor.commit();
            ndialog.instance.finish();
            Intent intent = new Intent(login.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
    private Handler getNewsHandler_teacher = new Handler(){
        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            String jsonData = (String) msg.obj;

            JSONObject jsonObj = null;


            try {
                jsonObj = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONObject jsonObject = jsonObj.optJSONObject("teacher");
            Long id = jsonObject.optLong("id");
            String account = jsonObject.optString("account");
            String name = jsonObject.optString("name");
            String password = jsonObject.optString("password");

            teacher = new Teacher(id,account,name,password);
            myApplication.setTeacher(teacher);
            myApplication.setId(1);
            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //0为学生
            editor.putInt("id", 1);
            editor.putString("teacherName", name);
            editor.putString("account", account);
            editor.commit();
            ndialog.instance.finish();
            Intent intent = new Intent(login.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
