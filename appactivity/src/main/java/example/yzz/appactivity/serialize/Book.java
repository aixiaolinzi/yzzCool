package example.yzz.appactivity.serialize;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 描述:
 * Created by yzz on 2018/1/9.
 */

public class Book implements Parcelable {
    protected Book(Parcel in) {
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
