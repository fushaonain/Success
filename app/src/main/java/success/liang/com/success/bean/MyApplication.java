package success.liang.com.success.bean;

import android.app.Application;

/**
 * Created by liangx on 2015/10/13.
 */
public class MyApplication extends Application {

    private String userName;
    private Student student;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    private Teacher teacher;
    private int id;
    private String position = null;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }



    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }





}
