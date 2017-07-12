package com.yue.yueapp;

import android.content.SyncStatusObserver;

import org.junit.Test;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * Think in java 的代码模仿
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    /***************************第8章：对象的容纳*********************8*/

    @Test
    public void ObjectTest() {
        // Arrays of objects:
        Weeble[] a; // Null handle，初始化一个句柄，编译器禁止我们对这个句柄做任何实际操作。除非已经初始化了它。
        Weeble[] b = new Weeble[5]; // Null handles，只是知道大小，但是不知道有多少合法的对象。
        Weeble[] c = new Weeble[4];
        for (int i = 0; i < c.length; i++)
            c[i] = new Weeble();
        Weeble[] d = {
                new Weeble(), new Weeble(), new Weeble()
        };
        // Compile error: variable a not initialized:
        //!System.out.println("a.length=" + a.length);
        System.out.println("b.length = " + b.length);
        // The handles inside the array are
        // automatically initialized to null:
        for (int i = 0; i < b.length; i++)
            System.out.println("b[" + i + "]=" + b[i]);
        System.out.println("c.length = " + c.length);
        System.out.println("d.length = " + d.length);
        a = d;
        System.out.println("a.length = " + a.length);
        // Java 1.1 initialization syntax:
        a = new Weeble[]{
                new Weeble(), new Weeble()
        };
        System.out.println("a.length = " + a.length);

        // Arrays of primitives:
        int[] e; // Null handle
        int[] f = new int[5];
        int[] g = new int[4];
        for (int i = 0; i < g.length; i++)
            g[i] = i * i;
        int[] h = {11, 47, 93};
        // Compile error: variable e not initialized:
        //!System.out.println("e.length=" + e.length);
        System.out.println("f.length = " + f.length);
        // The primitives inside the array are
        // automatically initialized to zero:
        for (int i = 0; i < f.length; i++)
            System.out.println("f[" + i + "]=" + f[i]);
        System.out.println("g.length = " + g.length);
        System.out.println("h.length = " + h.length);
        e = h;
        System.out.println("e.length = " + e.length);
        // Java 1.1 initialization syntax:
        e = new int[]{1, 2};
        System.out.println("e.length = " + e.length);
    }

    static String[] flav = {
            "Chocolate",
            "Strawberry",
            "Vanilla Fudge Swirl",
            "Mint Chip",
            "Mocha Almond Fudge",
            "Rum Raisin",
            "Praline Cream",
            "Mud Pie"
    };

    /**
     * 作用就是返回数组，但是数组的元素的顺序是随机的。
     *
     * @param n
     * @return
     */
    static String[] flavorSet(int n) {
        // Force it to be positive & within bounds:
        n = Math.abs(n) % (flav.length + 1);
        String[] results = new String[n];
        int[] picks = new int[n];
        for (int i = 0; i < picks.length; i++)
            picks[i] = -1;


        for (int i = 0; i < picks.length; i++) {
            retry:
            while (true) {
                int t = (int) (Math.random() * flav.length);
                for (int j = 0; j < i; j++)
                    if (picks[j] == t) continue retry;
                picks[i] = t;
                results[i] = flav[t];
                break;
            }
        }
        return results;
    }

    @Test
    public void testSet() {
        for (int i = 0; i < 20; i++) {
            System.out.println("flavorSet(" + i + ") = ");
            String[] fl = flavorSet(flav.length);
            for (int j = 0; j < fl.length; j++)
                System.out.println("\t" + fl[j]);
        }
    }


    class Cat {
        private int catNumber;

        Cat(int i) {
            catNumber = i;
        }

        void print() {
            System.out.println("Cat #" + catNumber);
        }
    }

    class Dog {
        private int dogNumber;

        Dog(int i) {
            dogNumber = i;
        }

        void print() {
            System.out.println("Dog #" + dogNumber);
        }
    }

    @Test
    public void textVector() {
        Vector cats = new Vector();
        for (int i = 0; i < 7; i++)
            cats.addElement(new Cat(i));
        // Not a problem to add a dog to cats:
//        cats.addElement(new Dog(7));
        Enumeration enumeration = cats.elements();
        while (enumeration.hasMoreElements()) {
            ((Cat) enumeration.nextElement()).print();
        }
        for (int i = 0; i < cats.size(); i++)
            ((Cat) cats.elementAt(i)).print();
        // Dog is detected only at run-time
    }

    /**
     * 注意stack属于Vector，因此上面对vector的操作，都能适用于stack
     */
    @Test
    public void textStack() {
        Stack stack = new Stack();
        for (int i = 0; i < flav.length; i++) {
            stack.push(flav[i]);
        }
        System.out.println("stack的值+" + stack);
        stack.addElement("The last line");
        System.out.println(
                "element 5 = " + stack.elementAt(5));
        System.out.println("popping elements:");
        while (!stack.empty())
            System.out.println(stack.pop());
        //pop出来值之后，就没有了值了
        System.out.println("stack的值+" + stack);
    }

    /**
     * 这个类有一种Map的感觉，其实就一种HashTable的感觉
     */
    public class AssocArray extends Dictionary {
        private Vector keys = new Vector();
        private Vector values = new Vector();

        public int size() {
            return keys.size();
        }

        public boolean isEmpty() {
            return keys.isEmpty();
        }

        public Object put(Object key, Object value) {
            keys.addElement(key);
            values.addElement(value);
            return key;
        }

        public Object get(Object key) {
            int index = keys.indexOf(key);
            // indexOf() Returns -1 if key not found:
            if (index == -1) return null;
            return values.elementAt(index);
        }

        public Object remove(Object key) {
            int index = keys.indexOf(key);
            if (index == -1) return null;
            keys.removeElementAt(index);
            Object returnval = values.elementAt(index);
            values.removeElementAt(index);
            return returnval;
        }

        public Enumeration keys() {
            return keys.elements();
        }

        public Enumeration elements() {
            return values.elements();
        }
    }

    @Test
    public void testDictionary() {
        AssocArray aa = new AssocArray();
        for (char c = 'a'; c <= 'z'; c++)
            aa.put(String.valueOf(c), String.valueOf(c).toUpperCase());
        char[] ca = {'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < ca.length; i++)
            System.out.println("Uppercase: " + aa.get(String.valueOf(ca[i])));
    }

    class InterTo {
        int i = 1;

        @Override
        public String toString() {
            return Integer.toString(i);
        }
    }

    @Test
    public void testHashTable() {
        Hashtable<Integer, InterTo> ht = new Hashtable();
        for (int i = 0; i < 10000; i++) {
            // Produce a number between 0 and 20:
            Integer r = new Integer((int) (Math.random() * 20));
            if (ht.containsKey(r))
                ((InterTo) ht.get(r)).i++;
            else
                ht.put(r, new InterTo());
        }

        System.out.println(ht);
        for (Map.Entry<Integer, InterTo> integerInterToEntry : ht.entrySet()) {
            System.out.println("得到的值+" + integerInterToEntry.getKey() + "value+" + integerInterToEntry.getValue());
        }


        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);

        Set<Integer> set = map.keySet();
        for (Integer integer : set) {
            System.out.println("" + map.get(integer));
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
        Iterator<Map.Entry<Integer, Integer>> iteratori = map.entrySet().iterator();

        while (iteratori.hasNext()) {
            Map.Entry<Integer, Integer> entry = iteratori.next();
            System.out.println("" + entry.getKey() + "kkk" + entry.getValue());
        }
    }


    class Groundhog {
        int ghNumber;

        Groundhog(int n) {
            ghNumber = n;
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof Groundhog)
                    && (ghNumber == ((Groundhog) obj).ghNumber);
        }

        @Override
        public int hashCode() {
            return ghNumber;
        }


        @Override
        public String toString() {
            return Integer.toString(ghNumber);
        }
    }

    class Prediction {
        boolean shadow = Math.random() > 0.5;

        @Override
        public String toString() {
            if (shadow)
                return "Six more weeks of Winter!";
            else
                return "Early Spring!";
        }
    }

    /**
     * key值是一个对象，处理另一个对象，，
     * 问题在于Groundhog是从通用的Object根类继承的。
     * 修改：hasCode和equals方法就可以实现了。
     */
    @Test
    public void SpringDetector() {
        Hashtable ht = new Hashtable();
        for (int i = 0; i < 10; i++)
            ht.put(new Groundhog(i), new Prediction());
        System.out.println("ht = " + ht + "\n");
        System.out.println("Looking up prediction for groundhog #3:");
        Groundhog gh = new Groundhog(3);
        if (ht.containsKey(gh)) {
            System.out.println((Prediction) ht.get(gh));
        } else {
            System.out.println("没有进入 ");
        }

        Properties properties= System.getProperties();

    }

}