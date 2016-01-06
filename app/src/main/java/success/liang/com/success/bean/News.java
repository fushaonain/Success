package success.liang.com.success.bean;

public class News {
    private String pic_url;
    private String title;
    private String desc;
    private String time;
    private String content_url;

    public News(String content_url, String pic_url, String title, String desc, String time) {
        this.content_url = content_url;
        this.pic_url = pic_url;
        this.title = title;
        this.desc = desc;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {

//        int end =time.indexOf("T");
//
//        this.time = time.substring(0,end);
        this.time=time;

    }



    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }



    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }



}


