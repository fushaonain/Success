package success.liang.com.success.Fragment_sons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import success.liang.com.success.R;
import success.liang.com.success.adapter.adapter_search;
import success.liang.com.success.bean.Student;

/**
 * Created by liangx on 2015/10/29.
 */
public class fragment_search_result extends Fragment implements AdapterView.OnItemClickListener{

    private ListView lv_search;
    private Adapter adapter_search;
    private ArrayList<Student> students;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_listview,container,false);

        students = new ArrayList<Student>();

        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            ArrayList list = bundle.getParcelableArrayList("students");
            students= (ArrayList<Student>) list.get(0);
        }
        lv_search = (ListView) root.findViewById(R.id.lv_search);
        adapter_search = new adapter_search(getActivity(),students);

        lv_search.setAdapter((ListAdapter) adapter_search);
        lv_search.setOnItemClickListener(this);
        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Student student = students.get(position);
        Fragment fragment = new fragment_information();
        Bundle bundle = new Bundle();
        bundle.putSerializable("student",student);
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contents, fragment).commit();
    }
}
