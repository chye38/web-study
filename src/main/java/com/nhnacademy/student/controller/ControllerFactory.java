package com.nhnacademy.student.controller;

import com.nhnacademy.student.util.RequestMapping;
import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ControllerFactory  {
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void init(Set<Class<?>> c){
        //todo beanMap에 key = method + servletPath, value = Controller instance
        for (Class<?> clazz : c) {
            // @RequestMapping 어노테이션이 있는지 확인
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
            String path = requestMapping.value();
            RequestMapping.Method method = requestMapping.method();

            try {
                // 기본 생성자를 사용하여 인스턴스 생성
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                Object controllerInstance = constructor.newInstance();

                // key = method + path 형태로 저장
                String key = method.name()+":"+path;
                beanMap.put(key, controllerInstance);
                log.info("RequestMapping 컨트롤러 등록: {} -> {}", key, clazz.getSimpleName());
            } catch (Exception e) {
                // 생성자 호출 실패 시 로그 출력 (실제 프로덕션에서는 적절한 로깅 사용)
                log.error("Failed to create instance for " + clazz.getName() + ": " + e.getMessage());
            }
        }
    }
}

public Object getBean(String method, String path){
    //todo beanMap 에서 method+servletPath을 key로 이용하여 Controller instance를 반환합니다.
    String key = method +":"+ path;
    Object controller = beanMap.get(key);
    if (controller == null) {
        log.error("컨트롤러를 찾을 수 없습니다: {} {}", method, path);
    }
    return controller;
}
}