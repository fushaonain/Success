package success.liang.com.success.fourFragment_sons;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import success.liang.com.success.MainActivity;
import success.liang.com.success.R;

/**
 * Created by liangx on 2015/10/21.
 */
public class duiwu extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_duiwu, container, false);
        /*ActionBar actionBar =
        if (actionBar != null) {
            actionBar.setTitle("duiwu");
        }*/
        return root;
    }
}
