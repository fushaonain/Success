package success.liang.com.success.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import success.liang.com.success.R;
import success.liang.com.success.activity.information;
import success.liang.com.success.activity.login;
import success.liang.com.success.activity.xuegong_home;
import success.liang.com.success.adapter.adapter_four;
import success.liang.com.success.bean.MyApplication;
import success.liang.com.success.tab_four.tab_four_about;

/**
 * Created by liangx on 2015/10/2.
 */
public class fragment_four extends Fragment implements AdapterView.OnItemClickListener{

    private ListView lv_login;
    private Adapter adapter_menu;
    private MyApplication myApplication;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.tab_four,container,false);

        myApplication = (MyApplication) getActivity().getApplication();

        lv_login = (ListView) root.findViewById(R.id.lv_four);
        switch(myApplication.getId()){
            case 0:
                if(myApplication.getStudent()!=null)
                    adapter_menu = new adapter_four(getActivity(),myApplication.getStudent().getName());
                else
                    Toast.makeText(getActivity(),"(myApplication.getStudent()=null",Toast.LENGTH_LONG).show();
                break;
            case 1:
                adapter_menu = new adapter_four(getActivity(),myApplication.getTeacher().getName());
                break;
            case 2:
                adapter_menu = new adapter_four(getActivity(),myApplication.getUserName());
                break;

        }
        //adapter_menu = new adapter_four(getActivity(),myApplication.getStudent().getName());
        lv_login.setAdapter((ListAdapter) adapter_menu);
        lv_login.setOnItemClickListener(this);



        return root;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                if(myApplication.getId() == 0){
                    Intent intent = new Intent(getActivity(), information.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity(), "暂无老师信息", Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                Intent intent_two = new Intent(getActivity(),xuegong_home.class);
                intent_two.putExtra("data",1);
                startActivity(intent_two);
                break;
            case 3:
                Intent intent_Three = new Intent(getActivity(),xuegong_home.class);
                intent_Three.putExtra("data", 2);
                startActivity(intent_Three);
                break;
            case 4:
                Intent intent_Four = new Intent(getActivity(),xuegong_home.class);
                intent_Four.putExtra("data", 3);
                startActivity(intent_Four);
                break;
            case 6:
                Toast.makeText(getActivity(), "暂无更新", Toast.LENGTH_LONG).show();
                break;
            case 7:
                Intent intent_seven = new Intent(getActivity(), tab_four_about.class);
                startActivity(intent_seven);
                break;
            case 8:
                showConfirmDialog();
                break;
            case 10:
                Intent intent_nine = new Intent(getActivity(), login.class);
                startActivity(intent_nine);
                /*
                2015/11/1 添加  因为login那里start了MainActivity  所以这边要finish掉
                 */
                getActivity().finish();


        }
    }

    private void showConfirmDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确认退出吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity activity =getActivity();
                activity.finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

}
