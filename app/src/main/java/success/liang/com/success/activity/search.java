package success.liang.com.success.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import success.liang.com.success.Fragment_sons.fragment_search_result;
import success.liang.com.success.R;
import success.liang.com.success.bean.Student;
import success.liang.com.success.utils.HttpUtils;

public class search extends AppCompatActivity {

    private Spinner mSpinnerYear;
    private EditText mEditTextName;
    private EditText mEditTextNumber;
    private EditText mEditTextPhone;
    private EditText mEditTextID;
    private Spinner mSpinnerClass;
    private Spinner mSpinnerProvince;
    private Spinner mSpinnerPeople;
    private Spinner mSpinnerParty;
    private Button search;

    public String[] dataYear;
    public String[] dataClass;
    public String[] dataProvince;
    public String[] dataPeople;
    public String[] dataParty;
    private ArrayList<Student> students;


    public static final String SEARCHL = "http://192.168.1.17:8080/studentManagement/findManyStudentInA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("搜索信息");
        actionBar.setDisplayHomeAsUpEnabled(true);



        Toast.makeText(search.this, "九个筛选条件均可自由选择", Toast.LENGTH_LONG).show();
        //绑定组件
        mSpinnerYear = (Spinner) findViewById(R.id.year);
        mEditTextName = (EditText) findViewById(R.id.name);
        mEditTextNumber = (EditText) findViewById(R.id.number);
        mEditTextPhone = (EditText) findViewById(R.id.phonenumber);
        mEditTextID = (EditText) findViewById(R.id.ID);
        mSpinnerClass = (Spinner) findViewById(R.id.classname);
        mSpinnerProvince = (Spinner) findViewById(R.id.province);
        mSpinnerPeople = (Spinner) findViewById(R.id.people);
        mSpinnerParty = (Spinner) findViewById(R.id.party);
        search = (Button) findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                students = new ArrayList<Student>();
                String year = (String) mSpinnerYear.getSelectedItem();
                String class_ = (String) mSpinnerClass.getSelectedItem();
                String name = mEditTextName.getText().toString();
                String studentId = mEditTextNumber.getText().toString();
                String province = mSpinnerProvince.getSelectedItem().toString();
                String phone = mEditTextPhone.getText().toString();
                String ID = mEditTextID.getText().toString();
                String people = (String) mSpinnerPeople.getSelectedItem();
                String party = (String) mSpinnerParty.getSelectedItem();
                if (year.equals("选择入学年份")) {
                    year = null;
                }
                if (class_.equals("选择班级")) {
                    class_ = null;
                }
                if (province.equals("选择生源省份")) {
                    province = null;
                }
                if (name.equals("")) {
                    name = null;
                }
                if (studentId.equals("")) {
                    studentId = null;
                }
                if(phone.equals("")){
                    phone=null;
                }
                if(ID.equals("")){
                    ID=null;
                }
                if(people.equals("选择民族")){
                    people=null;
                }
                if(party.equals("选择政治面貌")){
                    party=null;
                }
                HttpUtils.getSearch(studentId, name, province, class_, year, getSearch, SEARCHL,phone,ID,people,party);
            }
        });
        dataYear = new String[]{"选择入学年份", "2012"};
        dataClass = new String[]{"选择班级", "英语1201", "英语1202", "英语1203", "英语1204", "英语1205", "英语1206", "英语1207", "日语1201", "法语1201", "西语1201"};
        dataProvince = new String[]{"选择生源省份", "山东", "山西", "河北", "河南", "湖北", "湖南", "广东",
                "广西", "黑龙江", "辽宁", "浙江", "安徽", "江苏", "福建", "甘肃", "江西", "云南", "贵州", "四川",
                "青海", "陕西", "吉林", "宁夏", "海南", "西藏", "内蒙古", "新疆", "台湾"};

        dataPeople = new String[]{"选择民族", "汉族","壮族","满族","回族","苗族","维吾尔族","土家族","彝族","蒙古族","藏族","布依族","侗族","瑶族","朝鲜族","白族","哈尼族","哈萨克族","黎族","傣族","畲族","傈僳族","仡佬族","东乡族","高山族","拉祜族","水族","佤族","纳西族","羌族","土族","仫佬族","锡伯族","柯尔克孜族","达斡尔族","景颇族","毛南族","撒拉族","布朗族","塔吉克族","阿昌族","普米族","鄂温克族","怒族","京族","基诺族","德昂族","保安族","俄罗斯族","裕固族","乌孜别克族","门巴族","鄂伦春族","独龙族","塔塔尔族","赫哲族","珞巴族"};
        dataParty = new String[]{"选择政治面貌","预备党员","共青团员","中共党员","群众"};
        mSpinnerYear.setAdapter(new ArrayAdapter<String>
                (this, R.layout.support_simple_spinner_dropdown_item, dataYear));
        mSpinnerClass.setAdapter(new ArrayAdapter<String>
                (this, R.layout.support_simple_spinner_dropdown_item, dataClass));
        mSpinnerProvince.setAdapter(new ArrayAdapter<String>
                (this, R.layout.support_simple_spinner_dropdown_item, dataProvince));
        mSpinnerPeople.setAdapter(new ArrayAdapter<String>
                (this, R.layout.support_simple_spinner_dropdown_item, dataPeople));
        mSpinnerParty.setAdapter(new ArrayAdapter<String>
                (this, R.layout.support_simple_spinner_dropdown_item, dataParty));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void add_fragment(ArrayList<Student> students) {
        if (students.isEmpty()) {
            Toast.makeText(search.this, "无符合此条件的学生", Toast.LENGTH_LONG).show();
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = new fragment_search_result();
            Bundle bundle = new Bundle();
            ArrayList list = new ArrayList();
            list.add(students);
            bundle.putParcelableArrayList("students", list);
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.contents, fragment).commit();
        }
    }

    private Handler getSearch = new Handler() {
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
            JSONArray jsonArray = jsonObj.optJSONArray("student");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.optJSONObject(i);
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
                Student student = new Student(studentId, name, sex, year, nation, political, yearAndMonthOfBirth, class_, tel, location, idcard, familyLocation, postcode, telOfFather,
                        telOfMother, province, studentStatus, job, isInSchool, note,score1,score2,score3,score4,reward,
                        tutor, educationLevel, studentPeriod,
                        studyMode, qq, email,
                        picture);
                students.add(student);
            }
            add_fragment(students);
        }
    };
}
