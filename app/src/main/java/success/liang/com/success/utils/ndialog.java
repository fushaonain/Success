package success.liang.com.success.utils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import success.liang.com.success.R;

public class ndialog extends Activity {

    private String promt;
    private int icon;
    private int time;


    private ImageView IvIcon;
    private TextView TvPromt;
    private android.os.Handler mHandler;

    public static ndialog instance = null;
    private boolean isYaolianwang = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndialog);
        instance = ndialog.this;
        //获取传递过来的参数
        Bundle b = new Bundle();
        b = getIntent().getBundleExtra("data");
        promt = b.getString("promt");
        icon = b.getInt("icon");
        time = b.getInt("time");
        //绑定组件
        IvIcon = (ImageView) findViewById(R.id.icon);
        TvPromt = (TextView) findViewById(R.id.promt);
        //设置提示框内容，delay为提示框延迟函数
        TvPromt.setText(promt);
        switch (icon) {
            case 0:
                IvIcon.setImageResource(R.drawable.wrong1);
                break;
            case 1:
                IvIcon.setImageResource(R.drawable.loading);
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this,
                        R.anim.animation);
                IvIcon.startAnimation(hyperspaceJumpAnimation);
                isYaolianwang = true;
                setFinishOnTouchOutside(false);//点击外部不返回

                break;
            default:
                IvIcon.setImageResource(R.mipmap.about1);
        }

        delay();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ndialog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


    public void delay() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0)
                    ndialog.this.finish();
                else if(msg.what==1){
                    if(ndialog.instance!=null)
                        ndialog.this.finish();


                }
            }
        };

        Thread mThread = new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Message msg = mHandler.obtainMessage();
                    Thread.sleep(time);
                    if (isYaolianwang) {

                        msg.what=1;
                    } else {

                        msg.what = 0;
                    }

                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };
        //用message、handler的原因是，UI更改要传回主线程才安全
        mThread.start();
    }

}
