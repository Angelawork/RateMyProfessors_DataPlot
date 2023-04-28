package finalproject.personal_tester;

import finalproject.DataAnalyzer;
import finalproject.MyHashTable;
import finalproject.MyPair;

import java.util.Arrays;
import java.util.Iterator;
import java.util.*;

public class personal_table_tester {
    static public void main(String[] a) {
        MyHashTable<Integer, String> tester = new MyHashTable<>();
        //ArrayList<MyPair<Integer, String>> x=tester.getEntries();
        //tester.put(2,"key 2");
        //String y=tester.remove(4);

        tester.put(new Integer(2), "2 key");
        Iterator x = tester.iterator();
        MyPair y = (MyPair) x.next();
        System.out.println(y.getKey());
        System.out.println(y.getValue());
        System.out.println(((MyPair) x.next()));

        tester.put(new Integer(2), "2 key");
        tester.put(new Integer(4), "4 key");
        tester.put(new Integer(8), "8 key");
        tester.put(new Integer(16), "8 key");
        tester.put(new Integer(19), "19 key");
        tester.put(new Integer(25), "25 key");
        tester.put(new Integer(30), "25 key");
        tester.remove(4);
        System.out.println(tester.getKeySet().toString());
        System.out.println(tester.getValueSet().toString());
        tester.rehash();
        System.out.println(tester.getKeySet().toString());
        System.out.println(tester.getValueSet().toString());

        System.out.println("she\'s a very fair grader.".replaceAll("[^a-z’]", " "));
        System.out.println(Arrays.toString("she\'s a very fair grader.".replaceAll("[^a-z’]", " ").split(" ")));


    }
}
