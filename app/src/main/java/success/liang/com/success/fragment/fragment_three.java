package success.liang.com.success.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import success.liang.com.success.R;


/**
 * Created by liangx on 2015/10/2.
 */
public class fragment_three extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.tab_three,container,false);
        return root;
    }
}
