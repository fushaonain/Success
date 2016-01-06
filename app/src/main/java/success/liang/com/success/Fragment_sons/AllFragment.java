package success.liang.com.success.Fragment_sons;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import success.liang.com.success.R;

/**
 * Created by 温 睿诚 on 2015/10/29/0029.
 */
public class AllFragment extends Fragment {
    public String TAG="data";
    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.all, container, false);
        int data = getArguments().getInt(TAG);
        int title;
        int part;
        switch (data) {
            case 1:
                title = R.string.one_title;
                part = R.string.one_all;
                break;
            case 2:
                title = R.string.two_title;
                part = R.string.two_all;
                break;
            case 3:
                title = R.string.three_title;
                part = R.string.three_all;
                break;
            default:
                title = R.string.one_title;
                part = R.string.one_all;
                break;
        }
        ((TextView)root.findViewById(R.id.title)).setText(title);
        ((TextView)root.findViewById(R.id.content)).setText(part);
        root.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return root;
    }
}
