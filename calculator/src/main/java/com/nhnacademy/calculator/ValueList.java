package com.nhnacademy.calculator;

import java.time.LocalTime;

public class ValueList {
    public double value;
    public LocalTime time;

    public ValueList(double value, LocalTime time){
        this.value = value;
        this.time = time;
    }

    public double getValue(){
        return value;
    }

    public LocalTime getTime(){
        return time;
    }
}
