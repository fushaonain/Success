package success.liang.com.success.bean;

/**
 * Created by liangx on 2015/10/2.
 */
public class Tab {

    private int title;
    private int icon;
    private Class fragment;


    public Tab(int title, int icon, Class fragment) {
        this.fragment = fragment;
        this.icon = icon;
        this.title = title;
    }

    public int getTitle() {

        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
