package example.yzz.appactivity.serialize;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 描述:
 * Created by yzz on 2018/1/9.
 */

public class Person implements Parcelable {
    private String name;
    private int age;
    private boolean isYes;
    private Book book;


    protected Person(Parcel in) {
        name = in.readString();
        age = in.readInt();
        isYes = in.readInt() == 1;
        book = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeInt(isYes ? 1 : 0);
        dest.writeParcelable(book, 0);
    }

}
