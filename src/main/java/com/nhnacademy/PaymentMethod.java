package com.nhnacademy;

public enum PaymentMethod {
    CARD("카드"),
    BANK("계좌이체"),
    CASH("현금"),
    STORE("매장");

    final String name;
    PaymentMethod(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
