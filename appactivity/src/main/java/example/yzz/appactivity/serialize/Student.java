package example.yzz.appactivity.serialize;

import java.io.Serializable;

/**
 * 描述:Serializable的使用，
 * 其实就没了，剩下的就是存储了。
 * Created by yzz on 2018/1/9.
 */

public class Student implements Serializable {
    private static final long serialVersionUID = 8829975621220483374L;
    private String name;
    private int age;
    private boolean isYes;


    public Student(String name, int age, boolean isYes) {
        this.name = name;
        this.age = age;
        this.isYes = isYes;
    }
}
