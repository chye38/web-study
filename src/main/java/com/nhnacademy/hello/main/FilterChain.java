package com.nhnacademy.hello.main;

import com.nhnacademy.hello.practice.Filter;
import com.nhnacademy.hello.request.Request;
import com.nhnacademy.hello.response.AdminPageResponse;
import com.nhnacademy.hello.response.MyPageResponse;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FilterChain {
    private List<Filter> filters = new LinkedList<>();
    private Iterator iterator;

    public void addFilter(Filter filter){
        this.filters.add(filter);
        iterator = filters.iterator();
    }

    public void doFilter(Request request){

        if(iterator.hasNext()){
            Filter nextFilter = (Filter) iterator.next();
            nextFilter.doFilter(request,this);
        }else{

            //요청 결과값 출력
            if(request.getPath().equals("/mypage")){
                new MyPageResponse().doResponse(request);
            }else if(request.getPath().equals("/admin")){
                new AdminPageResponse().doResponse(request);
            }

        }
    }

}
