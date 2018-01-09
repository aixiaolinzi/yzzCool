package example.yzz.appactivity.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 描述:
 * Created by yzz on 2018/1/9.
 */

public class StudentFactory {
    //序列化
    private void writeObect() {
        Student student=new Student("小三",12,true);
        try {
            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("caac.txt"));
            out.writeObject(student);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //反序列化
    private void readObject(){
        try {
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("caac.txt"));
            Student student = (Student) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
