package com.nhnacademy.calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Storage {
    private static final Queue<ValueList> list = new ArrayDeque<>();

    public static Queue<ValueList> getStorage(){
        return list;
    }

    public static void addStorage(ValueList value){
        if(list.size() == 5){
            list.poll();
        }
        list.add(value);
    }
}
