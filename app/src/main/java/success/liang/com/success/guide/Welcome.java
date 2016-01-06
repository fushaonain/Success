package success.liang.com.success.guide;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import success.liang.com.success.MainActivity;
import success.liang.com.success.R;
import success.liang.com.success.activity.login;
import success.liang.com.success.bean.MyApplication;
import success.liang.com.success.bean.Student;
import success.liang.com.success.bean.Teacher;

public class Welcome extends AppCompatActivity {

    private boolean isFirstIn = false;
    private static final int TIME = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private MyApplication myApplication;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();

            }
        }

        private void goGuide() {
            Intent intent = new Intent(Welcome.this,Guide.class);
            startActivity(intent);
            finish();
        }

        private void goHome() {
            SharedPreferences preferences_first = getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
            int id = preferences_first.getInt("id", 4);

            switch (id){
                case 0:
                    login_student();
                    break;
                case 1:
                    login_teacher();
                    break;
                case 2:
                    login_secretary();
                    break;
                default:
                    Intent intent = new Intent(Welcome.this,login.class);
                    startActivity(intent);
                    break;
            }
            /*2015/11/1 15:55注释   下面已有相同代码
//            //如果不是第一次登陆
//            if(id != 4){
//                SharedPreferences preferences = getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
//                Long studentId = preferences.getLong("studentId", 0);
//                String name = preferences.getString("name", "");
//                String sex = preferences.getString("sex", "");
//                int year = preferences.getInt("year", 0);
//                String nation = preferences.getString("nation", "");
//                String political = preferences.getString("political", "");
//                String yearAndMonthOfBirth = preferences.getString("yearAndMonthOfBirth", "");
//                String class_ = preferences.getString("class_", "");
//                String tel = preferences.getString("tel", "");
//                String location = preferences.getString("location", "");
//                String idcard = preferences.getString("idcard", "");
//                String familyLocation = preferences.getString("familyLocation", "");
//                String postcode = preferences.getString("postcode", "");
//                String telOfFather = preferences.getString("telOfFather", "");
//                String telOfMother = preferences.getString("telOfMother", "");
//                String province = preferences.getString("province", "");
//                String studentStatus = preferences.getString("studentStatus", "");
//                String job = preferences.getString("job", "");
//                Boolean isInSchool = preferences.getBoolean("isInSchool",true);
//                String note = preferences.getString("note", "");
//                Student student = new Student(studentId,name,sex,year,nation,political,yearAndMonthOfBirth,class_,tel,location,idcard,familyLocation,postcode,telOfFather,
//                        telOfMother,province,studentStatus,job,isInSchool,note);
//                myApplication.setId(id);
//                myApplication.setStudent(student);
//                Intent intent = new Intent(Welcome.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//            else{
//                Intent intent = new Intent(Welcome.this,login.class);
//                startActivity(intent);
//            }
*/
            /*Intent intent = new Intent(Welcome.this,login.class);
            startActivity(intent);
            finish();*/
        }
    };

    private void login_student(){
        SharedPreferences preferences = getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        Long studentId = preferences.getLong("studentId", 0);
        String name = preferences.getString("name", "");
        String sex = preferences.getString("sex", "");
        int year = preferences.getInt("year", 0);
        String nation = preferences.getString("nation", "");
        String political = preferences.getString("political", "");
        String yearAndMonthOfBirth = preferences.getString("yearAndMonthOfBirth", "");
        String class_ = preferences.getString("class_", "");
        String tel = preferences.getString("tel", "");
        String location = preferences.getString("location", "");
        String idcard = preferences.getString("idcard", "");
        String familyLocation = preferences.getString("familyLocation", "");
        String postcode = preferences.getString("postcode", "");
        String telOfFather = preferences.getString("telOfFather", "");
        String telOfMother = preferences.getString("telOfMother", "");
        String province = preferences.getString("province", "");
        String studentStatus = preferences.getString("studentStatus", "");
        String job = preferences.getString("job", "");
        Boolean isInSchool = preferences.getBoolean("isInSchool", true);
        String note = preferences.getString("note", "");
        int score1 = preferences.getInt("score1", 0);
        int score2 = preferences.getInt("score2", 0);
        int score3 = preferences.getInt("score3", 0);
        int score4 = preferences.getInt("score4", 0);
        String reward = preferences.getString("reward", "");
        String tutor = preferences.getString("tutor", "");
        String educationLevel = preferences.getString("educationLevel", "");
        String studentPeriod = preferences.getString("studentPeriod", "");
        String studyMode = preferences.getString("studyMode", "");
        String qq = preferences.getString("qq", "");
        String email = preferences.getString("email", "");
        String picture = preferences.getString("picture", "");
        Student student = new Student(studentId,name,sex,year,nation,political,yearAndMonthOfBirth,class_,tel,location,idcard,familyLocation,postcode,telOfFather,
                telOfMother,province,studentStatus,job,isInSchool,note,score1,score2,score3,score4,reward,
                tutor, educationLevel, studentPeriod,
                studyMode, qq, email,
                picture);
        myApplication.setId(0);
        myApplication.setStudent(student);
        Intent intent = new Intent(Welcome.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void login_secretary(){
        SharedPreferences preferences_two = getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        String secretaryname = preferences_two.getString("secretaryname", "");
        myApplication.setUserName(secretaryname);
        myApplication.setId(2);
        Intent intent = new Intent(Welcome.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void login_teacher(){
        SharedPreferences preferences_one = getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        String teachername = preferences_one.getString("teacherName", "");
        Teacher teacher = new Teacher(teachername);
        myApplication.setTeacher(teacher);
        myApplication.setId(1);
        Intent intent = new Intent(Welcome.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        final ActionBar actionBar = getSupportActionBar();
        myApplication = (MyApplication) getApplication();
        actionBar.hide();
        //getActionBar().hide();
        init();
    }

    private void init() {
        SharedPreferences preferences = getSharedPreferences("liang", MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn",true);
        if (!isFirstIn)
        {
            handler.sendEmptyMessageDelayed(GO_HOME,TIME);
            //handler.sendEmptyMessage(GO_HOME);
        }
        else
        {
            handler.sendEmptyMessageDelayed(GO_GUIDE,TIME);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstIn",false);
            editor.commit();
        }
    }
}
