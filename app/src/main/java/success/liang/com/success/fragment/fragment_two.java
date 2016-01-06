package success.liang.com.success.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import success.liang.com.success.R;
import success.liang.com.success.activity.activity_howtouse;
import success.liang.com.success.activity.search;
import success.liang.com.success.bean.MyApplication;

/**
 * Created by liangx on 2015/10/2.
 */
public class fragment_two extends Fragment {

    private LinearLayout btn_student;//右侧按钮
    private LinearLayout btn_teacher;//左侧按钮，查询信息
    private Button btn_jiaochengT;//老师使用教程
    private Button btn_jiaochengS;//学生使用教程

    private MyApplication myApplication;

    public static final String SEND_POSITION = "http://192.168.1.115:8080/studentManagement/sign";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.tab_two,container,false);

        myApplication = (MyApplication) getActivity().getApplication();

        btn_student = (LinearLayout) root.findViewById(R.id.ll_qingjia);
        btn_teacher = (LinearLayout) root.findViewById(R.id.ll_chaxun);
        btn_student.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    btn_student.setBackgroundResource(R.drawable.cornerr);
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    btn_student.setBackgroundResource(R.drawable.cornerrt);
                }
                return false;
            }
        });
        btn_teacher.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    btn_teacher.setBackgroundResource(R.drawable.cornerl);
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    btn_teacher.setBackgroundResource(R.drawable.cornerlt);
                }
                return false;
            }
        });
        btn_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = myApplication.getId();
                if (id == 1 || id == 2) {
                    Intent intent = new Intent(getActivity(), search.class);
                    startActivity(intent);
                } else
                    Toast.makeText(getActivity(), "对不起，您无此权限！", Toast.LENGTH_SHORT).show();
            }
        });
        btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                int id = myApplication.getId();
                if (id == 0){
                    String position = myApplication.getPosition();
                    if(position == null){
                        Toast.makeText(getActivity(), "请先定位！", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        long currentTime = System.currentTimeMillis();
                        HttpUtils.sendPosition(myApplication.getStudent().getStudentId().toString(), myApplication.getPosition(),
                                String.valueOf(currentTime), SEND_POSITION, sendPosition);
                    }
                }
                else
                    Toast.makeText(getActivity(), "对不起，您无此权限！", Toast.LENGTH_SHORT).show();
                *//*
                * 这里原本是签到按钮，现在改为请假/审批。尚未开通
                * */
                Toast.makeText(getActivity(), "功能未开通", Toast.LENGTH_SHORT).show();

            }
        });
        btn_jiaochengS= (Button) root.findViewById(R.id.student_howtouse);
        btn_jiaochengT= (Button) root.findViewById(R.id.teacher_howtouse);
        btn_jiaochengT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),activity_howtouse.class);
                intent.putExtra("person",0);
                startActivity(intent);
            }
        });
        btn_jiaochengS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),activity_howtouse.class);
                intent.putExtra("person",1);
                startActivity(intent);
            }
        });

        return root;
    }


}
