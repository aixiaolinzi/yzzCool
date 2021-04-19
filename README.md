# yzzCool
处理自定义视图。效果如下：
![image](https://github.com/aixiaolinzi/YueApp/blob/master/picture/1.png)

下面是贝塞尔曲线的使用：
![](https://github.com/aixiaolinzi/YueApp/blob/master/picture/Bessel.gif)


###appactivity
这个是讲解 `a21.Binder机制及底层实现` 这篇文章的。
这篇文章的讲解在`IMyAidlInterface`这个里面都能找到。注意查找build生成文件。

### recyclerviewdemo
#### RecyclerViewActivity
跳转到RecyclerViewActivity。RecyclerView的吸顶效果。在这里有。

#### HttpActivity
跳转到HttpActivity，OkHttpClient的使用，加载百度页面，加载的数值，使用WebView加载出来。

#### SocketTestActivity
跳转到SocketTestActivity，Socket通信的验证，现在这个地址不能访问了。无法验证。

#### VolleyActivity
跳转到VolleyActivity，Volley的使用，直至简单的使用，细节还需要继续优化。

#### WebViewActivity
跳转到WebViewActivity，最简单的WebView的使用。WebView的加载。😀

#### ShapeActivity
跳转到ShapeActivity,Shape属相的理解，空间添加Shape属性后控件的模样。


### MediaRecorderDemo
MediaRecorder实现录音功能。
MediaPlayer实现录音播放功能。
```java
        //创建MediaRecorder对象
        mMediaRecorder = new MediaRecorder();
        //创建录音文件,.m4a为MPEG-4音频标准的文件的扩展名
        mAudioFile = new File(mFilePath + System.currentTimeMillis() + ".m4a");
        //创建父文件夹
        mAudioFile.getParentFile().mkdirs();
        try {
            //创建文件
            mAudioFile.createNewFile();
            //配置mMediaRecorder相应参数
            //从麦克风采集声音数据
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置保存文件格式为MP4
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            //设置采样频率,44100是所有安卓设备都支持的频率,频率越高，音质越好，当然文件越大
            mMediaRecorder.setAudioSamplingRate(44100);
            //设置声音数据编码格式,音频通用格式是AAC
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            //设置编码频率
            mMediaRecorder.setAudioEncodingBitRate(96000);
            //设置录音保存的文件
            mMediaRecorder.setOutputFile(mAudioFile.getAbsolutePath());
            //开始录音
            mMediaRecorder.prepare();
            mMediaRecorder.start();
            //记录开始录音时间
            startTime = System.currentTimeMillis();
            Log.e(TAG, "开始录音+" + startTime);
            updateMicStatus();
        } catch (Exception e) {
            e.printStackTrace();
            recordFail();
        }
```