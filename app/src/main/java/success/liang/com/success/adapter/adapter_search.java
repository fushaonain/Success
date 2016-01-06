package success.liang.com.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import success.liang.com.success.R;
import success.liang.com.success.bean.Student;

/**
 * Created by liangx on 2015/10/29.
 */
public class adapter_search extends BaseAdapter{
    private LayoutInflater mInflater;
    private ArrayList<Student> students;

    public adapter_search(Context context){
        this.mInflater = LayoutInflater.from(context);
    }
    public adapter_search(Context context,ArrayList<Student> students){
        this.mInflater = LayoutInflater.from(context);
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_search, null);
        }

        TextView tv_search_name = (TextView) convertView.findViewById(R.id.tv_search_name);
        TextView tv_search_class= (TextView) convertView.findViewById(R.id.tv_search_class);

        Student student = students.get(position);
        tv_search_name.setText(student.getName());
        tv_search_class.setText(student.getClass_());
        return convertView;
    }
}
