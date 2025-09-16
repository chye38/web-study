package com.nhnacademy.hello.response;

import com.nhnacademy.hello.main.Member;
import com.nhnacademy.hello.request.Request;

public class OrderPageResponse implements Response{
    @Override
    public void doResponse(Request request) {
        System.out.println("###### response:AdminPageResponse #####");
        Member member = (Member) request.get("member");
        System.out.println("아이디:" + member.getId());
        System.out.println("이름:" + member.getName());
        System.out.println("등급:" + Member.Role.ADMIN);
        System.out.println("이메일: marco@nhnacademy.com");
        System.out.println("do something ... ADMIN ...");
    }
}
