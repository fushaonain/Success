package success.liang.com.success.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import success.liang.com.success.R;
import success.liang.com.success.activity.login;
import success.liang.com.success.adapter.viewPagerAdapter;

public class Guide extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager vp;
    private viewPagerAdapter vpAdapter;
    private List<View> views;
    private ImageView[] dots;
    private int[] ids = {R.id.iv1, R.id.iv3};
    //private Button start_btn;
    private  ImageView ivEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_guide);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
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
        views.add(inflater.inflate(R.layout.guide_one, null));
        views.add(inflater.inflate(R.layout.guide_three, null));

        vpAdapter = new viewPagerAdapter(views, this);
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        //start_btn = (Button) views.get(1).findViewById(R.id.start_btn);
        ivEnter=(ImageView)views.get(1).findViewById(R.id.pic_enter);
       ivEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Guide.this, login.class);
                startActivity(i);
                finish();
            }
        });
        vp.setOnPageChangeListener(this);
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
