package success.liang.com.success.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import success.liang.com.success.NewsWebActivity;
import success.liang.com.success.R;
import success.liang.com.success.adapter.NewaAdapter_one;
import success.liang.com.success.adapter.ViewPagerAdapter_one;
import success.liang.com.success.bean.MyApplication;
import success.liang.com.success.bean.News;
import success.liang.com.success.utils.HttpUtils;
import success.liang.com.success.utils.ndialog;

/**
 * Created by liangx on 2015/10/4.
 */
public class homeFragment extends Fragment implements ViewPager.OnPageChangeListener,AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener{



    public ListView lvNews_one;
    private NewaAdapter_one adapter_one;

    private ViewPager vp;
    private ViewPagerAdapter_one vpAdapter;
    private List<View> views;
    private ImageView[] dots;
    private int[] ids = {R.id.iv1,R.id.iv2,R.id.iv3};

    private SwipeRefreshLayout swipeLayout;

    private MyApplication myApplication;

    public static final String GET_NEWS_URL ="http://192.168.1.17:8080/studentManagement/news";
    private List<News> newsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.home, container, false);


        myApplication = (MyApplication) getActivity().getApplication();

        lvNews_one = (ListView) root.findViewById(R.id.lv_one);
        newsList = new ArrayList<News>();
        adapter_one = new NewaAdapter_one(getActivity(),newsList);


        lvNews_one.setAdapter(adapter_one);
        lvNews_one.setOnItemClickListener(this);
        initViews(root);
        initDots(root);

        //myDialog("正在加载……", 1, 3000);

        HttpUtils.getNewsJSON(GET_NEWS_URL, getNewsHandler);


        swipeLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe_refresh);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorScheme(new int[]{android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light});

        //initLoading(root);

        setHasOptionsMenu(true);


        return root;
    }



    private void myDialog(String promt,int icon,int time){
        Intent i =new Intent(getActivity(),ndialog.class);
        Bundle b =new Bundle();
        b.putString("promt", promt);
        b.putInt("icon", icon);
        b.putInt("time", time);

        i.putExtra("data", b);
        startActivity(i);

    }
    private Handler getNewsHandler = new Handler(){
        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            String jsonData = (String) msg.obj;

            JSONObject jsonObj = null;


            try {
                jsonObj = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            int page = jsonObj.optInt("totalPage");
            int news = jsonObj.optInt("totalNews");
            //JSONArray jsonArray = jsonObj.optJSONArray("news");
            JSONObject jsonObject = jsonObj.optJSONObject("news");
            for (int i= 1; i <= news;i++) {
                JSONObject jsonObject_one = jsonObject.optJSONObject(i + "");
                String time = jsonObject_one.optString("date");
                int end =time.indexOf("T");
                String temp=null;
                if(end!=-1)
                    temp = time.substring(0,end);
                String content_url = jsonObject_one.optString("link");
                String title = jsonObject_one.optString("title");
                String desc = jsonObject_one.optString("subtitle");
                String pic_url = jsonObject_one.optString("picLink");
                newsList.add(new News(content_url,pic_url,title,desc,temp));
            }
            adapter_one.notifyDataSetChanged();
           if(ndialog.instance!=null)
               ndialog.instance.finish();
        }
    };
    private void initDots(View root){
        dots = new ImageView[views.size()];
        for (int i = 0;i < views.size();i++)
        {
            dots[i] = (ImageView) root.findViewById(ids[i]);
        }
    }

    private void initViews(View root) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.pic_one,null));
        views.add(inflater.inflate(R.layout.pic_two,null));
        views.add(inflater.inflate(R.layout.pic_three,null));

        vpAdapter = new ViewPagerAdapter_one(views,getActivity());
        vp = (ViewPager) root.findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        vp.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0;i < ids.length;i++)
        {
            if(position == i)
            {
                dots[i].setImageResource(R.drawable.login_point_selected);
            }
            else
            {
                dots[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        Intent intent = new Intent(getActivity(),NewsWebActivity.class);
        //intent.putExtra("content_url","http://www.baidu.com");
        intent.putExtra("content_url",news.getContent_url());
        startActivity(intent);


    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                myDialog("正在加载……", 1, 5000);
                swipeLayout.setRefreshing(false);
                newsList.clear();
                HttpUtils.getNewsJSON(GET_NEWS_URL, getNewsHandler);
                adapter_one.notifyDataSetChanged();


            }
        }, 500);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        /*menu.add("首页").setShowAsAction(
                MenuItem.SHOW_AS_ACTION_IF_ROOM
        );*/

    }
}
