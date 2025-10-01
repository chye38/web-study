package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // 서비스 테스트시 오류제어를 왜 안하는지?
    // 다른 테스트는 왜?
    @Override
    public User getUser(String userId){
        //todo#4-1 회원조회
        log.debug("회원을 조회합니다");
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록
        log.debug("회원을 등록합니다");
        if(userRepository.countByUserId(user.getUserId()) > 0){
            throw new UserAlreadyExistsException(user.getUserId());
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        log.debug("회원을 수정합니다");
        if(userRepository.countByUserId(user.getUserId()) == 0){
            throw new UserNotFoundException(user.getUserId());
        }
        userRepository.update(user);
    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        log.debug("회원을 삭제합니다");
        if(userRepository.countByUserId(userId) == 0){
            throw new UserNotFoundException(userId);
        }
        userRepository.deleteByUserId(userId);
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
        log.debug("로그인을 합니다");
        // userId가 존재하지않으면 exception
        // userId가 존재하는지와 비밀번호가 틀린걸 각각 오류를 던지는 의도?
        if(userRepository.countByUserId(userId) == 0){
            throw new UserNotFoundException(userId);
        }

        // password가 틀렸는지도 확인
        User user = userRepository.findByUserIdAndUserPassword(userId, userPassword)
                .orElseThrow(() -> new UserNotFoundException(userId));
        
        // 마지막 로그인 시간 업데이트
        userRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());
        return user;
    }

}
