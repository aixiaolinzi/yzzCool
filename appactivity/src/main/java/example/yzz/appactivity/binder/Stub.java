package example.yzz.appactivity.binder;

import android.os.Binder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;


/**
 * 描述:
 * Created by yzz on 2018/1/9.
 */

public class Stub extends Binder {


    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case 0: {
                data.enforceInterface("add two int");
                int arg0 = data.readInt();
                int arg1 = data.readInt();
                IInterface iInterface = this.queryLocalInterface("add two int");
                int result = ((IPlus) iInterface).add(arg0, arg1);
                reply.writeInt(result);
            }
        }
        return super.onTransact(code, data, reply, flags);
    }


}
