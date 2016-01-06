package success.liang.com.success.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import success.liang.com.success.Fragment_sons.BlankFragment;
import success.liang.com.success.R;

public class xuegong_home extends AppCompatActivity {

    public String TAG="data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuegong_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("学工之家");
        actionBar.setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container);
        if(fragment==null){
            fragment = new BlankFragment();
            Bundle bundle= new Bundle();
            bundle.putInt(TAG,getIntent().getIntExtra(TAG,1));
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.container,fragment).commit();
        }
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
