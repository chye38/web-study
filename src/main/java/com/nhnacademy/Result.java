package com.nhnacademy;

public enum Result {
    WIN("승리"),
    LOSE("패배"),
    DRAW("무승부");

    final String name;

    Result(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
