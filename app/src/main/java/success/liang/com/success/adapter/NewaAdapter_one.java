package success.liang.com.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

import success.liang.com.success.R;
import success.liang.com.success.bean.News;
import success.liang.com.success.utils.NormalLoadPictrue;

/**
 * Created by liangx on 2015/8/13.
 */
public class NewaAdapter_one extends BaseAdapter {

    private LayoutInflater mInflater;


    private List<News> newsList;
    public NewaAdapter_one(Context context, List<News> newsList) {
        this.mInflater = LayoutInflater.from(context);
        this.newsList = newsList;
    }
    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public News getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.news_item_one, null);
        }

        TextView tv_title_one = (TextView) convertView.findViewById(R.id.tv_title_one);
        TextView tv_desc_one = (TextView) convertView.findViewById(R.id.tv_desc_one);
        TextView tv_time_one = (TextView) convertView.findViewById(R.id.tv_time_one);
        ImageView iv_pic_one = (ImageView) convertView.findViewById(R.id.iv_pic_one);


        News news = newsList.get(position);
        tv_title_one.setText(news.getTitle());
        tv_desc_one.setText(news.getDesc());
        tv_time_one.setText(news.getTime());

        String pic_url = news.getPic_url();
        new NormalLoadPictrue().getPicture(pic_url, iv_pic_one);


        return convertView;

    }
}
