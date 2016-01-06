package success.liang.com.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import success.liang.com.success.R;

/**
 * Created by liangx on 2015/8/25.
 */
public class adapter_four extends BaseAdapter {

    private LayoutInflater mInflater;
    private int count = 5;
    private String userName;




    public adapter_four(Context context){
        this.mInflater = LayoutInflater.from(context);
    }

    public adapter_four(Context context, String userName){
        this.mInflater = LayoutInflater.from(context);
        this.userName = userName;
    }
    @Override
    public int getCount() {
        /*return count;*/
        return 11;
    }

    public int getItemViewType(int position) {
        return position;
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


        if (convertView == null)
            switch (position) {
                case 0:
                    convertView = mInflater.inflate(R.layout.item_me_one, null);
                    ImageView iv_four_one = (ImageView) convertView.findViewById(R.id.iv_four_one);
                    iv_four_one.setImageResource(R.mipmap.student);
                    /*iv_login.setImageResource(R.drawable.arrow);*/
                    TextView tv_login = (TextView) convertView.findViewById(R.id.tv_four_name);
                    tv_login.setText(userName);
                    if (userName != null) {
                        tv_login.setText(userName);
                    }
                    break;
                case 1:
                    convertView = mInflater.inflate(R.layout.item_null, null);
                    break;
                case 2:
                    convertView = mInflater.inflate(R.layout.item_me, null);
                    TextView tv_about = (TextView) convertView.findViewById(R.id.tv_menu);
                    tv_about.setText("学工队伍");
                    ImageView iv_about = (ImageView) convertView.findViewById(R.id.iv_menu);
                    iv_about.setImageResource(R.mipmap.tab_four_duiwu);
                    break;
                case 3:
                    convertView = mInflater.inflate(R.layout.item_me, null);
                    TextView tv_update = (TextView) convertView.findViewById(R.id.tv_menu);
                    tv_update.setText("学工制度");
                    ImageView iv_update = (ImageView) convertView.findViewById(R.id.iv_menu);
                    iv_update.setImageResource(R.mipmap.tab_four_zhidu);
                    break;
                case 4:
                    convertView = mInflater.inflate(R.layout.item_me, null);
                    TextView tv_go = (TextView) convertView.findViewById(R.id.tv_menu);
                    tv_go.setText("办事流程");
                    ImageView iv_go = (ImageView) convertView.findViewById(R.id.iv_menu);
                    iv_go.setImageResource(R.mipmap.tab_four_liucheng);
                    break;
                case 5:
                    convertView = mInflater.inflate(R.layout.item_null, null);
                    break;
                case 6:
                    convertView = mInflater.inflate(R.layout.item_me, null);
                    TextView tv_six = (TextView) convertView.findViewById(R.id.tv_menu);
                    tv_six.setText("版本更新");
                    ImageView iv_six = (ImageView) convertView.findViewById(R.id.iv_menu);
                    iv_six.setImageResource(R.mipmap.tab_four_update);
                    break;
                case 7:
                    convertView = mInflater.inflate(R.layout.item_me, null);
                    TextView tv_seven = (TextView) convertView.findViewById(R.id.tv_menu);
                    tv_seven.setText("关于");
                    ImageView iv_seven = (ImageView) convertView.findViewById(R.id.iv_menu);
                    iv_seven.setImageResource(R.mipmap.tab_four_about);
                    break;
                case 8:
                    convertView = mInflater.inflate(R.layout.item_me, null);
                    TextView tv_eight = (TextView) convertView.findViewById(R.id.tv_menu);
                    tv_eight.setText("退出");
                    ImageView iv_eight = (ImageView) convertView.findViewById(R.id.iv_menu);
                    iv_eight.setImageResource(R.mipmap.tab_four_leave);
                    break;
                case 9:
                    convertView = mInflater.inflate(R.layout.item_null, null);
                    break;
                case 10:
                    convertView = mInflater.inflate(R.layout.item_logout, null);
                    break;

            }


        return convertView;
    }
}
