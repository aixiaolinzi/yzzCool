package example.yzz.mediarecorderdemo;

import android.content.Context;

import java.util.List;

import example.yzz.mediarecorderdemo.base.CommonAdapter;
import example.yzz.mediarecorderdemo.base.ViewHolder;

/**
 * 描述:
 * Created by yzz on 2018/3/27.
 */

public class AudioAdapter extends CommonAdapter<FileBean> {

    public AudioAdapter(Context mContext, List<FileBean> mDatas, int itemResId) {
        super(mContext, mDatas, itemResId);
    }

    @Override
    public void convert(ViewHolder holder, FileBean fileBean) {
        holder.setTextView(R.id.item_tv, "录音文件：" + fileBean.getFile().getAbsolutePath() + "\n录音时长：" + fileBean.getFileLength() + "s");
    }
}
