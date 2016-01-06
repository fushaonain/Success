package success.liang.com.success;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import success.liang.com.success.bean.MyApplication;
import success.liang.com.success.bean.Tab;
import success.liang.com.success.fragment.fragment_four;
import success.liang.com.success.fragment.fragment_two;
import success.liang.com.success.fragment.homeFragment;
import success.liang.com.success.utils.HttpUtils;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<>(4);
    private MyApplication myApplication;
    ActionBar actionBar;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private long temp = 0;//记录按下返回键时间
    public static final String SEND_POSITION = "http://192.168.1.17:8080/studentManagement/sign";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocation();

            }
        });

        initTab();

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar = getSupportActionBar();
        myApplication = (MyApplication) getApplication();
        actionBar.setTitle("外语院app");

    }


    private void initTab() {
        Tab home = new Tab(R.string.tab_one, R.drawable.selector_icon_tab_one, homeFragment.class);
        Tab two = new Tab(R.string.tab_two, R.drawable.selector_icon_tab_two, fragment_two.class);
        //Tab three = new Tab(R.string.tab_three, R.drawable.selector_icon_tab_three, fragment_three.class);
        Tab four = new Tab(R.string.tab_four, R.drawable.selector_icon_tab_four, fragment_four.class);

        mTabs.add(home);
        mTabs.add(two);
        //mTabs.add(three);
        mTabs.add(four);

        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);

        }
        /**
         * 去掉分割线
         */
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);
    }


    private View buildIndicator(Tab tab) {

        View view = mInflater.inflate(R.layout.tab_indicator, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.icon_tab);
        TextView textView = (TextView) view.findViewById(R.id.text_indicator);
        imageView.setBackgroundResource(tab.getIcon());
        textView.setText(tab.getTitle());

        return view;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    //初始化定位设置
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        mLocationClient.setLocOption(option);
    }

    //定位监听
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.delete(0, sb.length());
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.delete(0, sb.length());
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.delete(0, sb.length());
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            Log.i("BaiduLocationApiDem", sb.toString());

            finishLocation(location.getAddrStr(), location.getLocType());

        }
    }

    //开始定位函数
    public void startLocation() {

        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        initLocation();
        mLocationClient.start();
    }

    //定位成功做什么写在这里，参数是定位得到的地址,失败则是错误信息
    public void finishLocation(String address, int locType) {
        if (locType == 161) {
            myApplication.setPosition(address);
            //Toast.makeText(this, "定位成功：" + address, Toast.LENGTH_SHORT).show();

            int id = myApplication.getId();
            if (id == 0) {
                String position = myApplication.getPosition();

                long currentTime = System.currentTimeMillis();
                HttpUtils.sendPosition(myApplication.getStudent().getStudentId().toString(), myApplication.getPosition(),
                        String.valueOf(currentTime), SEND_POSITION, sendPosition);
                Toast.makeText(MainActivity.this, "签到成功！", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(MainActivity.this, "对不起，您无此权限！", Toast.LENGTH_SHORT).show();

        } else if (locType == 505)
            //Toast.makeText(this,"定位过于频繁" , Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "签到过于频繁", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "签到失败，请检查网络", Toast.LENGTH_SHORT).show();
        mLocationClient.stop();
    }

    //按下返回键退出询问
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && (event.getAction() == KeyEvent.ACTION_DOWN)) {

            if (System.currentTimeMillis() - temp > 2000) {//两次按下超过两秒则提示
                Toast.makeText(MainActivity.this, "再次按下返回键退出", Toast.LENGTH_SHORT).show();
                temp = System.currentTimeMillis();

            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Handler sendPosition = new Handler() {
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
                Toast.makeText(MainActivity.this, "签到失败", Toast.LENGTH_SHORT).show();
            }


            String success = jsonObj.optString("success");
            //get_sendPosition(success);

        }
    };
    /*
    * 下面这个函数已经不需要用到了
    * */
    private void get_sendPosition(String success) {
        if (success.equals("true"))
            Toast.makeText(MainActivity.this, "签到成功", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "签到失败", Toast.LENGTH_SHORT).show();

    }
}
