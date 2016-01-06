package success.liang.com.success.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import success.liang.com.success.MainActivity;
import success.liang.com.success.R;
import success.liang.com.success.adapter.viewPagerAdapter;

public class activity_howtouse extends AppCompatActivity implements ViewPager.OnPageChangeListener{


    private ViewPager vp;
    private viewPagerAdapter vpAdapter;
    private List<View> views;
    private ImageView[] dots;
    private int[] ids = {R.id.iv1_howtouse,R.id.iv2_howtouse, R.id.iv3_howtouse};

    private int person;
    private  ImageView ivEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_howtouse);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Bundle extras = getIntent().getExtras();
        person = extras.getInt("person");
        initViews();
        initDots();
    }

    private void initDots() {
        dots = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<View>();
        if(person == 0){
            views.add(inflater.inflate(R.layout.use_teacher_one, null));
            views.add(inflater.inflate(R.layout.use_teacher_two, null));
            views.add(inflater.inflate(R.layout.use_teacher_three, null));
        }else
        {
            views.add(inflater.inflate(R.layout.use_teacher_one, null));
            views.add(inflater.inflate(R.layout.use_student_two, null));
            views.add(inflater.inflate(R.layout.use_teacher_three, null));
        }


        vpAdapter = new viewPagerAdapter(views, this);
        vp = (ViewPager) findViewById(R.id.viewpager_howtouse);
        vp.setAdapter(vpAdapter);
        //start_btn = (Button) views.get(1).findViewById(R.id.start_btn);
        ivEnter=(ImageView)views.get(2).findViewById(R.id.pic_enter_teacher);
        ivEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(activity_howtouse.this, MainActivity.class);
                startActivity(i);*/
                finish();
            }
        });
        vp.setOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_howtouse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < ids.length; i++) {
            if (position == i) {
                dots[i].setImageResource(R.mipmap.login_point_selected);
            } else {
                dots[i].setImageResource(R.mipmap.login_point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
