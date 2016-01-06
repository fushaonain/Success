package success.liang.com.success.tab_four;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import success.liang.com.success.R;

public class tab_four_about extends AppCompatActivity {

    private TextView mVersion;
    private RelativeLayout mRl;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_four_about);

        actionBar = getSupportActionBar();
        actionBar.setTitle("关于");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mVersion=(TextView)findViewById(R.id.version);
//        mRl=(RelativeLayout)findViewById(R.id.rl);
//        mRl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(tab_four_about.this, "导演：徐金良\n编剧：何柱君\n制片：顾利特\n监制：温睿诚", Toast.LENGTH_SHORT).show();
//            }
//        });
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
}
