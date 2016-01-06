package success.liang.com.success.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by liangx on 2015/8/22.
 */
public class viewPagerAdapter extends PagerAdapter {


    private List<View> views;
    private Context context;

    public viewPagerAdapter(java.util.List<View> views, Context context){
        this.views = views;
        this.context = context;
    }
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(views.get(position));
        return views.get(position);
    }
}
