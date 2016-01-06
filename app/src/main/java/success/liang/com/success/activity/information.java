package success.liang.com.success.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import success.liang.com.success.R;
import success.liang.com.success.bean.MyApplication;
import success.liang.com.success.bean.Student;
import success.liang.com.success.utils.NormalLoadPictrue;

public class information extends AppCompatActivity {

    private TextView name;//姓名
    private TextView sex;//性别
    private TextView birthday;//出生年月
    private TextView province;//生源省份
    private TextView people;//民族
    private TextView classname;//班级
    private TextView party;//党籍（党员、共青团员、群众）
    private TextView enterdata;//入学年份（获取数据后要加文本“年入学”）
    private TextView number;//学号
    private TextView atschool;//是否在校（若是，转化为“在校”）
    private TextView state;//注册学籍
    private EditText id;//身份证号码
    private TextView phone;//个人联系方式
    private TextView dadphone;//父亲联系方式
    private TextView momphone;//母亲联系方式
    private EditText nowaddress;//现住址
    private TextView homeaddress;//家庭住址
    private EditText youzheng;//邮政编码
    private EditText job;//职务
    private EditText beizhu;//备注


    private ImageView ivPic;//相片
    private EditText etMark1;//第一学年成绩
    private EditText etMark2;//第二学年
    private EditText etMark3;//第三学年
    private EditText etMark4;//第四学年
    private EditText etQQ;//QQ号
    private EditText etEmail;//邮箱
    private EditText etAward;//获奖情况
    private EditText etTeacher;//班导师，导师
    private EditText etPeiyangcengci;//培养层次
    private EditText etXuexinianxian;//学习年限
    private EditText etXuexixingshi;//学习形式

    private MyApplication myApplication;
    private Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("个人信息");
        actionBar.setDisplayHomeAsUpEnabled(true);

        myApplication = (MyApplication) getApplication();
        student = myApplication.getStudent();

        //绑定组件
       findViewByIdd();

        name.setText(student.getName());
        sex.setText(student.getSex());
        birthday.setText(student.getYearAndMonthOfBirth());
        province.setText(student.getProvince());
        people.setText(student.getNation());
        classname.setText(student.getClass_());
        party.setText(student.getPolitical());
        enterdata.setText(String.valueOf(student.getYear()));
        number.setText(String.valueOf(student.getStudentId()));
        if(student.getIsInSchool() == true){
            atschool.setText("在校");
        }
        else{
            atschool.setText("离校");
        }
        state.setText(student.getStudentStatus());
        id.setText(String.valueOf(student.getIdcard()));
        phone.setText(String.valueOf(student.getTel()));
        dadphone.setText(String.valueOf(student.getTelOfFather()));
        momphone.setText(String.valueOf(student.getTelOfMother()));
        nowaddress.setText(student.getLocation());
        homeaddress.setText(student.getFamilyLocation());
        youzheng.setText(student.getPostcode());
        job.setText(student.getJob());
        beizhu.setText(student.getNote());

        etMark1.setText(String.valueOf(student.getScore1()));
        etMark2.setText(String.valueOf(student.getScore2()));
        etMark3.setText(String.valueOf(student.getScore3()));
        etMark4.setText(String.valueOf(student.getScore4()));
        etAward.setText(String.valueOf(student.getReward()));
        etTeacher.setText(student.getTutor());
        etPeiyangcengci.setText(student.getEducationLevel());
        etXuexinianxian.setText(student.getStudentPeriod());
        etXuexixingshi.setText(student.getStudyMode());
        etQQ.setText(student.getQq());
        etEmail.setText(student.getEmail());

        new NormalLoadPictrue().getPicture("http://192.168.1.17:8080/studentManagement/img/" + student.getPicture(), ivPic);

        //提示
        Toast.makeText(information.this, "下拉还有内容哦\n电话号码、家庭地址点击即可复制", Toast.LENGTH_LONG).show();

        //点击即可复制信息
        setOnClick();
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
    public void findViewByIdd(){
        name=(TextView)findViewById(R.id.name);
        sex=(TextView)findViewById(R.id.sex);
        birthday=(TextView)findViewById(R.id.birthday);
        province=(TextView)findViewById(R.id.province);
        people=(TextView)findViewById(R.id.people);
        classname=(TextView)findViewById(R.id.classname);
        party=(TextView)findViewById(R.id.party);
        enterdata=(TextView)findViewById(R.id.enterdata);
        number=(TextView)findViewById(R.id.number);
        atschool=(TextView)findViewById(R.id.atschool);
        state=(TextView)findViewById(R.id.state);
        id=(EditText)findViewById(R.id.ID);
        phone=(TextView)findViewById(R.id.phone);
        dadphone=(TextView)findViewById(R.id.dadphone);
        momphone=(TextView)findViewById(R.id.momphone);
        nowaddress=(EditText)findViewById(R.id.nowaddress);
        homeaddress=(TextView)findViewById(R.id.homeaddress);
        youzheng=(EditText)findViewById(R.id.youzheng);
        job=(EditText)findViewById(R.id.job);
        beizhu=(EditText)findViewById(R.id.beizhu);

        ivPic= (ImageView) findViewById(R.id.pic);
        etMark1= (EditText) findViewById(R.id.mark1);
        etMark2= (EditText) findViewById(R.id.mark2);
        etMark3= (EditText) findViewById(R.id.mark3);
        etMark4= (EditText) findViewById(R.id.mark4);
        etQQ= (EditText) findViewById(R.id.QQ);
        etEmail= (EditText) findViewById(R.id.email);
        etAward= (EditText) findViewById(R.id.award);
        etTeacher= (EditText) findViewById(R.id.teacher);
        etPeiyangcengci= (EditText) findViewById(R.id.peiyangcengci);
        etXuexinianxian= (EditText) findViewById(R.id.xuexinianxian);
        etXuexixingshi= (EditText) findViewById(R.id.xuexixingshi);


    }
    public void setOnClick(){
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(number.getText());

                Toast.makeText(information.this, number.getText() + "  已复制到剪切板", Toast.LENGTH_SHORT).show();
            }
        });
        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(id.getText());

                Toast.makeText(information.this, id.getText() + "  已复制到剪切板", Toast.LENGTH_SHORT).show();
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(phone.getText());

                Toast.makeText(information.this, phone.getText() + "  已复制到剪切板", Toast.LENGTH_SHORT).show();
            }
        });
        dadphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(dadphone.getText());
                Toast.makeText(information.this,dadphone.getText()+"  已复制到剪切板",Toast.LENGTH_SHORT).show();
            }
        });
        momphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(momphone.getText());
                Toast.makeText(information.this,momphone.getText()+"  已复制到剪切板",Toast.LENGTH_SHORT).show();
            }
        });
        homeaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(homeaddress.getText());
                Toast.makeText(information.this,homeaddress.getText()+"  已复制到剪切板",Toast.LENGTH_SHORT).show();
            }
        });
        etQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(etQQ.getText());
                Toast.makeText(information.this,etQQ.getText()+"  已复制到剪切板",Toast.LENGTH_SHORT).show();
            }
        });
        etEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(etEmail.getText());
                Toast.makeText(information.this, etEmail.getText() + "  已复制到剪切板", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
