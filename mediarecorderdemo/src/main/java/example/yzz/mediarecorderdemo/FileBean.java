package example.yzz.mediarecorderdemo;

import java.io.File;

/**
 * 描述:
 * Created by yzz on 2018/3/27.
 */

public class FileBean {
    //文件
    private File file;
    //文件时长
    private int fileLength;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getFileLength() {
        return fileLength;
    }

    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }
}
