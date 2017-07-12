package com.yue.uilibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * <p>
 * 自己写的测试的代码就走这里
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    public static String BASEINFO = "Mr.Y";
    public static final int COUNT = 2000000;

    @Test
    public void stringTest() {
        doStringTest();
        doStringBufferTest();
        doStringBuilderTest();
        List<String> list = new ArrayList<String>();
        list.add(" I ");
        list.add(" like ");
        list.add(" BeiJing ");
        list.add(" tian ");
        list.add(" an ");
        list.add(" men ");
        list.add(" . ");


        doStringBufferListTest(list);
        doStringBuilderListTest(list);


        StringBuffer stringBuffer = new StringBuffer("1234567890");
        StringBuffer buffer = stringBuffer.reverse();
        stringBuffer.ensureCapacity(30);
        for (int i = 0; i <= 10; i++) {
            stringBuffer.append("k");
        }
//        stringBuffer.setLength(20);

        int kk = stringBuffer.capacity();
        System.out.println("自己新建的值" + stringBuffer.toString());
        System.out.println("做了处理的值" + buffer.toString());
        System.out.println("做了处理的值kk的值" + kk);
        char[] vv = new char[20];
        buffer.getChars(2, 3, vv, 0);
        for (char v : vv) {
            System.out.println("结果" + v);
        }
        System.out.println("做了处理的值" + buffer.toString());
    }

    public void doStringTest() {
        String str = new String(BASEINFO);
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < COUNT / 100; i++) {
            str = str + "miss";
        }
        long endtime = System.currentTimeMillis();
        System.out.println((endtime - starttime)
                + " millis has costed when used String.+++++");
    }

    /**
     * 执行一项StringBuffer赋值测试
     */
    public static void doStringBufferTest() {

        StringBuffer sb = new StringBuffer(BASEINFO);
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            sb = sb.append("miss");
        }
        long endtime = System.currentTimeMillis();
        System.out.println((endtime - starttime)
                + " millis has costed when used StringBuffer.++++");
    }

    /**
     * 执行一项StringBuilder赋值测试
     */
    public static void doStringBuilderTest() {

        StringBuilder sb = new StringBuilder(BASEINFO);
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            sb = sb.append("miss");
        }
        long endtime = System.currentTimeMillis();
        System.out.println((endtime - starttime)
                + " millis has costed when used StringBuilder.");
    }

    /**
     * 测试StringBuffer遍历赋值结果
     *
     * @param mlist
     */
    public static void doStringBufferListTest(List<String> mlist) {
        StringBuffer sb = new StringBuffer();
        long starttime = System.currentTimeMillis();
        for (String string : mlist) {
            sb.append(string);
        }
        long endtime = System.currentTimeMillis();
        System.out.println(sb.toString() + "buffer cost:"
                + (endtime - starttime) + " millis");
    }

    /**
     * 测试StringBuilder迭代赋值结果
     *
     * @param mlist
     */
    public static void doStringBuilderListTest(List<String> mlist) {
        StringBuilder sb = new StringBuilder();
        long starttime = System.currentTimeMillis();
        for (Iterator<String> iterator = mlist.iterator(); iterator.hasNext(); ) {
            sb.append(iterator.next());
        }

        long endtime = System.currentTimeMillis();
        System.out.println(sb.toString() + "builder cost:"
                + (endtime - starttime) + " millis");
    }
}