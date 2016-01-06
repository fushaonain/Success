package success.liang.com.success.Fragment_sons;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import success.liang.com.success.R;


public class BlankFragment extends Fragment {
    public String TAG = "data";


    public BlankFragment() {
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
        View root = inflater.inflate(R.layout.fragment_blank, container, false);
        int data = getArguments().getInt(TAG);
        int title;
        int part;
        switch (data) {
            case 1:
                title = R.string.one_title;
                part = R.string.one_part;
                break;
            case 2:
                title = R.string.two_title;
                part = R.string.two_part;
                break;
            case 3:
                title = R.string.three_title;
                part = R.string.three_part;
                break;
            default:
                title = R.string.one_title;
                part = R.string.one_part;
                break;
        }
        ((TextView)root.findViewById(R.id.title)).setText(title);
        ((TextView)root.findViewById(R.id.content)).setText(part);

        root.findViewById(R.id.allTitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AllFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(TAG, getArguments().getInt(TAG));
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, fragment).commit();
            }
        });
        return root;
    }


}
