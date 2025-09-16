package com.nhnacademy.hello.practice;

import com.nhnacademy.hello.main.FilterChain;
import com.nhnacademy.hello.main.Member;
import com.nhnacademy.hello.request.Request;

public class OrderPageFilter implements Filter{

    @Override
    public void doFilter(Request request, FilterChain filterChain) {
        if(request.getPath().equals("/order")){


            Member member = (Member) request.get("member");
            if(member.hasRole(Member.Role.USER)){
                System.out.println("path:" + request.getPath() + " member role has USER : true");
                filterChain.doFilter(request);
            }else{
                System.out.println("path:" + request.getPath() + " member role has USER : false");
            }
        }else{
            System.out.println("MyPageCheckFilter : 다음 필터로 넘김! ");

            //다음 filter로 넘김
            filterChain.doFilter(request);
        }
    }
}
